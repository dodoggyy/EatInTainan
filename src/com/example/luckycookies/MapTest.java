package com.example.luckycookies;


import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MapTest extends Activity{
	 static final LatLng NCKU = new LatLng(22.996661, 120.215666);
		private GoogleMap map;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.mapview);
			Intent intent = getIntent();
		    Bundle bundle = intent.getExtras();
		    ArrayList<String>name = bundle.getStringArrayList("shop_name");
		    //ArrayList<String>time = bundle.getStringArrayList("shop_time");
		    ArrayList<String>locx = bundle.getStringArrayList("locx");
		    ArrayList<String>locy = bundle.getStringArrayList("locy");
			map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			for(int i = 0; i < name.size();i++) {
				
				String mShopName="";
				String mTime="";
				String mLocx="";
				String mLocy="";
				mShopName = name.get(i);
				//mTime = time.get(i);
				mLocx = locx.get(i);
				mLocy = locy.get(i);		
				LatLng lat = new LatLng(Double.parseDouble(mLocy), Double.parseDouble(mLocx));
	        map.addMarker(new MarkerOptions().position(lat).title(mShopName));
			}
	        // Move the camera instantly to NKUT with a zoom of 16.
	        map.moveCamera(CameraUpdateFactory.newLatLngZoom(NCKU, 16));
	        //MarkListener markListener =new MarkListener();
	        map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

	            @Override
	            public void onInfoWindowClick(Marker marker) {
	            	Intent intent = new Intent(MapTest.this, Information.class);
					String name = marker.getTitle();
					Bundle bundle = FileHandler.getDiningBundle(name);
					intent.putExtras(bundle);
		    		   startActivity(intent);
	                //Log.d("", marker.getTitle());   
	            }
	        });
		}
		
}
