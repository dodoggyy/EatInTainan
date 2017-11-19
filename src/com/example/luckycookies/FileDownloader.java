package com.example.luckycookies;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;

import android.os.Environment;
import android.util.Log;

/* 
 * http://www.androidsnippets.com/download-an-http-file-to-sdcard-with-progress-notification
 */

public class FileDownloader {
	
	public static void downloadFile(String fileName, String sourceURL) {
		try {
			Log.i("TAG-Download", "Set the path where we want to save the file.");
			File SDCardRoot = Environment.getExternalStorageDirectory();
			File directory = new File(SDCardRoot, "Download");
			File file = new File(directory, fileName);
			
			Log.i("TAG-Download", "Set up URL connection.");
			URL url = new URL(sourceURL);
			Log.i("TAG-Download", "Set up URL connection. - urlConnection");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			Log.i("TAG-Download", "Set up URL connection. - urlConnection (GET)");
			urlConnection.setRequestMethod("GET");
			Log.i("TAG-Download", "Set up URL connection. - urlConnection (true)");
			urlConnection.setDoOutput(true);
			Log.i("TAG-Download", "Set up URL connection. - urlConnection (permission)");
	        Permission permission = urlConnection.getPermission();
	        Log.i("TAG-Download", permission.toString());
			Log.i("TAG-Download", "Set up URL connection. - urlConnection (connect)");
	        urlConnection.connect();
			Log.i("TAG-Download", "Set up URL connection. - inputStream");
			Log.i("TAG-Download", "Set up URL connection. - " + urlConnection.getURL());
			InputStream inputStream = urlConnection.getInputStream();
			
			Log.i("TAG-Download", "Load streaming.");
			FileOutputStream fileOutput = new FileOutputStream(file);
			byte[] buffer = new byte[10];
			int bufferLength = 0; //used to store a temporary size of the buffer
			while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
				fileOutput.write(buffer, 0, bufferLength);
			}
			fileOutput.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i("TAG-Download", "Download finished.");
	}

}
