package com.example.luckycookies;

public class Dining {
	//structure
	
	public static final String ID = "id";
	public static final String SHOP_NAME = "shop_name";
	public static final String SHOP_ID = "shop_id";
	public static final String SHOP_NO = "shop_number";
	public static final String DESCRIPTION = "shop_description";
	public static final String ADDRESS = "address";
	public static final String TELEPHONE = "telephone";
	public static final String SHOP_TIME = "shop_time";
	public static final String LOCX = "shop_locx";
	public static final String LOCY = "shop_locy";
	public static final String CATEGORY = "category"; 
	public static final String CHANGE_TIME = "change_time";
	
	public String id;
	public String shop_name;
	public String shop_id;
	public String shop_number;
	public String shop_description;
	public String address;
	public String telephone;
	public String shop_time;
	public String shop_locx;
	public String shop_locy;
	public String category;
	public String change_time;
	public Double distance;
	
	public Dining(){
		
	}
	
	public Dining(String [] strings){
		this.id = strings[0];
		this.shop_id = strings[0];
		this.shop_number = strings[1];
		this.shop_name = strings[2];
		this.shop_description = strings[3];
		this.address = strings[4];
		this.telephone = strings[5];
		this.shop_time = strings[6];
		this.shop_locx = strings[7];
		this.shop_locy = strings[8];
		this.category = strings[9];
		this.change_time = strings[10];
		this.distance = null;
	}
	
	public String toString() {
		String line = null;
		line = this.shop_name + "," + this.shop_description + "," +  this.address;
		return line;
	}
	
}
