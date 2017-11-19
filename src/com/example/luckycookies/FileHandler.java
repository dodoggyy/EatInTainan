package com.example.luckycookies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import android.os.Bundle;

public class FileHandler {

	public static String[] categoryArray = { "场摸", "繺芔", "", "都并翴",
			"︸も搂" };
	public static ArrayList<Dining> diningList = null;
	public static HashMap<String, ArrayList<Dining>> diningGroupHashMapByCategory = null;

	public static void downloadDinigCSV() {
		String fileName = "dining.csv";
		String sourceURL = "http://data.tainan.gov.tw/dataset/34e6decf-dd31-4208-a46c-4173279af5fc/resource/7343d994-0378-4714-a72c-89c9ea375794/download/dining.csv";
		sourceURL = "http://210.61.12.3:8090/storage/f/2013-11-11T23%3A08%3A03.585Z/2013info.csv";
		FileDownloader.downloadFile(fileName, sourceURL);
	}

	public static StringTokenizer getFormatedContents(String filePath) {
		/* format content by removing newlines in restaurant descriptions */
		StringBuffer stringBuffer = new StringBuffer("");
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			line = bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {
				if (line.subSequence(0, 2).equals("f_")) {
					stringBuffer.append("\n");
				}
				stringBuffer.append(line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* split formated content by '\n' */
		StringTokenizer stringTokenizer = new StringTokenizer(
				stringBuffer.toString(), "\n");
		return stringTokenizer;
	}

	private static String decodeCategory(String num) {
		String category = null;
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("01", "繺芔");
		hashMap.put("03", "");
		hashMap.put("04", "繺芔");
		hashMap.put("05", "都并翴");
		hashMap.put("06", "︸も搂");
		hashMap.put("07", "");
		hashMap.put("09", "皊");
		return category;
	}

	public static String[] getShopName(ArrayList<Dining> diningInfo) {
		String[] mShopNames = new String[diningInfo.size()];
		for (int i = 0; i < diningInfo.size(); i++) {
			mShopNames[i] = diningInfo.get(i).shop_name;
		}
		return mShopNames;
	}

	public static ArrayList<Dining> getDiningInfo(String filePath) {
		if (diningList == null) {
			diningList = new ArrayList<Dining>();
		}
		try {
			StringTokenizer stringTokenizer = getFormatedContents(filePath);
			while (stringTokenizer.hasMoreTokens()) {
				String line = stringTokenizer.nextToken();
				line.replace(",,", ",戈ア,");
				String[] strings = line.split(",");
				Dining dining = new Dining(strings);
				if (dining.shop_locx.contains("120.")) {
					diningList.add(dining);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diningList;
	}

	private static ArrayList<String> getShopNameArrayList(
			ArrayList<Dining> diningInfo) {
		ArrayList<String> shopNameArrayList = new ArrayList<String>();
		for (int i = 0; i < diningInfo.size(); i++) {
			String shop_name = diningInfo.get(i).shop_name;
			shopNameArrayList.add(shop_name);
		}
		return shopNameArrayList;
	}
	
	private static ArrayList<String> getShopDescriptionArrayList(
			ArrayList<Dining> diningInfo) {
		ArrayList<String> shop_descriptionArrayList = new ArrayList<String>();
		for (int i = 0; i < diningInfo.size(); i++) {
			String shop_name = diningInfo.get(i).shop_description;
			shop_descriptionArrayList.add(shop_name);
		}
		return shop_descriptionArrayList;
	}
	
	private static ArrayList<String> getTelephoneArrayList(
			ArrayList<Dining> diningInfo) {
		ArrayList<String> telephoneArrayList = new ArrayList<String>();
		for (int i = 0; i < diningInfo.size(); i++) {
			String shop_name = diningInfo.get(i).telephone;
			telephoneArrayList.add(shop_name);
		}
		return telephoneArrayList;
	}
	
	private static ArrayList<String> getAddressArrayList(
			ArrayList<Dining> diningInfo) {
		ArrayList<String> addressArrayList = new ArrayList<String>();
		for (int i = 0; i < diningInfo.size(); i++) {
			String shop_name = diningInfo.get(i).address;
			addressArrayList.add(shop_name);
		}
		return addressArrayList;
	}

	private static ArrayList<String> getShopTimeArrayList(
			ArrayList<Dining> diningInfo) {
		ArrayList<String> shopTimeArrayList = new ArrayList<String>();
		for (int i = 0; i < diningInfo.size(); i++) {
			String shop_time = diningInfo.get(i).shop_time;
			shopTimeArrayList.add(shop_time);
		}
		return shopTimeArrayList;
	}

	private static ArrayList<String> getShopLocXArrayList(
			ArrayList<Dining> diningInfo) {
		ArrayList<String> shopLocXArrayList = new ArrayList<String>();
		for (int i = 0; i < diningInfo.size(); i++) {
			String shop_locx = diningInfo.get(i).shop_locx;
			shopLocXArrayList.add(shop_locx);
		}
		return shopLocXArrayList;
	}

	private static ArrayList<String> getShopLocYArrayList(
			ArrayList<Dining> diningInfo) {
		ArrayList<String> shopLocYArrayList = new ArrayList<String>();
		for (int i = 0; i < diningInfo.size(); i++) {
			String shop_locy = diningInfo.get(i).shop_locy;
			shopLocYArrayList.add(shop_locy);
		}
		return shopLocYArrayList;
	}

	private static HashMap<String, Dining> getDiningHashMapByShopName(
			ArrayList<Dining> dininginfo) {
		HashMap<String, Dining> diningHashMap = new HashMap<String, Dining>();
		for (int i = 0; i < dininginfo.size(); i++) {
			diningHashMap.put(dininginfo.get(i).shop_name, dininginfo.get(i));
		}
		return diningHashMap;
	}

	public static Dining getTargetDining(ArrayList<Dining> dininginfo,
			String shopName) {
		Dining targetDining = new Dining();
		HashMap<String, Dining> diningHashMap = getDiningHashMapByShopName(dininginfo);
		targetDining = diningHashMap.get(shopName);
		return targetDining;
	}

	public static Bundle getDiningPageBundle(Dining dining) {
		Bundle bundle = new Bundle();
		bundle.putString("name", dining.shop_name);
		bundle.putString("desc", dining.shop_description);
		bundle.putString("locx", dining.shop_locx);
		bundle.putString("locy", dining.shop_locy);
		bundle.putString("phone", dining.telephone);
		bundle.putString("address", dining.address);
		bundle.putString("time", dining.shop_time);
		return bundle;
	}

	public static Bundle getDiningBundle(ArrayList<Dining> dininginfo,
			String shopName) {
		Dining dining = getTargetDining(dininginfo, shopName);
		Bundle bundle = getDiningPageBundle(dining);
		return bundle;
	}

	public static Bundle getDiningBundle(String shopName) {
		return getDiningBundle(diningList, shopName);
	}

	public static Bundle getMapBundle(ArrayList<Dining> diningInfo) {
		Bundle mapBundle = new Bundle();
		mapBundle.putStringArrayList("shop_name",
				getShopNameArrayList(diningInfo));
		mapBundle.putStringArrayList("shop_description",
				getShopDescriptionArrayList(diningInfo));
		mapBundle.putStringArrayList("shop_address",
				getAddressArrayList(diningInfo));
		mapBundle.putStringArrayList("telephone",
				getTelephoneArrayList(diningInfo));
		mapBundle.putStringArrayList("shop_time",
				getShopTimeArrayList(diningInfo));
		mapBundle.putStringArrayList("locx", getShopLocXArrayList(diningInfo));
		mapBundle.putStringArrayList("locy", getShopLocYArrayList(diningInfo));
		return mapBundle;
	}

	public static ArrayList<Map<String, Object>> getItems(
			ArrayList<Dining> diningInfo) {
		ArrayList<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		/* initial String arrays */
		String[] mShopName = new String[diningInfo.size()];
		String[] mShopDesc = new String[diningInfo.size()];
		String[] mShopAddress = new String[diningInfo.size()];
		String[] mShopPhone = new String[diningInfo.size()];
		String[] mShopTime = new String[diningInfo.size()];
		String[] mShopLocX = new String[diningInfo.size()];
		String[] mShopLocY = new String[diningInfo.size()];
		String[] mShopCate = new String[diningInfo.size()];
		/* put contents to String arrays */
		for (int i = 0; i < diningInfo.size(); i++) {
			mShopName[i] = diningInfo.get(i).shop_name;
			mShopDesc[i] = diningInfo.get(i).shop_description;
			mShopAddress[i] = diningInfo.get(i).address;
			mShopPhone[i] = diningInfo.get(i).telephone;
			mShopTime[i] = diningInfo.get(i).shop_time;
			mShopLocX[i] = diningInfo.get(i).shop_locx;
			mShopLocY[i] = diningInfo.get(i).shop_locy;
			mShopCate[i] = diningInfo.get(i).category;
		}
		/* make items */
		for (int i = 0; i < diningInfo.size(); i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			// if(mShopName[i] != null && mShopTime[i]!= null &&
			// mShopAddress[i]!= null){
			item.put("name", mShopName[i]);
			item.put("time", mShopTime[i]);
			item.put("address", mShopAddress[i]);
			item.put("description", mShopDesc[i]);
			items.add(item);
		}
		return items;
	}
	
	public static String [] getCategoryArray() {
		return categoryArray;
	}

	private static HashMap<String, String> getCategoryHashMap() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("00", "场摸");
		hashMap.put("01", "繺芔");
		hashMap.put("03", "");
		hashMap.put("04", "繺芔");
		hashMap.put("05", "都并翴");
		hashMap.put("06", "︸も搂");
		hashMap.put("07", "");
		hashMap.put("09", "都并翴");
		return hashMap;
	}

	public static HashMap<String, ArrayList<Dining>> getdiningGroupHashMapByCategory(
			ArrayList<Dining> diningInfo) {
		if (diningGroupHashMapByCategory == null) {
			diningGroupHashMapByCategory = new HashMap<String, ArrayList<Dining>>();
		}
		for (int i = 0; i < categoryArray.length; i++) {
			diningGroupHashMapByCategory.put(categoryArray[i],
					new ArrayList<Dining>());
		}
		HashMap<String, String> categoryHashMap = getCategoryHashMap();
		for (int i = 0; i < diningInfo.size(); i++) {
			String category = categoryHashMap.get(diningInfo.get(i).category);
			ArrayList<Dining> diningList = diningGroupHashMapByCategory
					.get(category);
			diningList.add(diningInfo.get(i));
			diningGroupHashMapByCategory.put(category, diningList);
		}
		return diningGroupHashMapByCategory;
	}
	
	public static ArrayList<Dining> getDiningsByCategory(
			String category) {
		if (category == "场摸") {
			return diningList;
		}
		return diningGroupHashMapByCategory.get(category);
	}

	public static ArrayList<Map<String, Object>> getItemsByCategory(
			String category) {
		if (category == "场摸") {
			return getItems(diningList);
		}
		if (diningGroupHashMapByCategory == null) {
			diningGroupHashMapByCategory = getdiningGroupHashMapByCategory(diningList);
		}
		ArrayList<Dining> dining = diningGroupHashMapByCategory.get(category);
		ArrayList<Map<String, Object>> items = getItems(dining);
		return items;
	}

	public static ArrayList<Dining> filterDiningInfo(
			ArrayList<Dining> diningInfo, String keyword) {
		ArrayList<Dining> filteredDiningInfo = new ArrayList<Dining>();
		for (int i = 0; i < diningInfo.size(); i++) {
			if (diningInfo.get(i).toString().contains("keyword")) {
				filteredDiningInfo.add(diningInfo.get(i));
			}
		}
		return filteredDiningInfo;
	}

	public static ArrayList<Dining> sortDiningInfo(ArrayList<Dining> diningInfo) {
		ArrayList<Dining> sortedDiningInfo = new ArrayList<Dining>();
		sortedDiningInfo.addAll(diningInfo);
		Collections.sort(sortedDiningInfo, new DiningSorterByDistance());
		return sortedDiningInfo;
	}

}
