package com.example.luckycookies;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Information extends Activity{
	private GoogleMap map;
	private LatLng shoplocation;
	private String name="";
	private String time="";
	private Marker marker_shoploc;
	
	private LocationManager locationManager;
	private Location myLocation;
	private Criteria criteria;
	
	private double myLat=0.0;
	private double myLon=0.0;
	private double locationY;
	private double locationX;
	
	private double distance;
	@Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.info);
	    Intent intent = getIntent();
	    Bundle bundle = intent.getExtras();
	    name = bundle.getString("name");
	    String desc = bundle.getString("desc");
	    String locx = bundle.getString("locx");
	    String locy = bundle.getString("locy");
	    String phone = bundle.getString("phone");
	    String address = bundle.getString("address");
	    time = bundle.getString("time");
	    
	   
	    
	    TextView textName = (TextView)findViewById(R.id.info_ViewShopName);
	    TextView textPhone = (TextView)findViewById(R.id.info_TextPhone);
	    TextView textAddress = (TextView)findViewById(R.id.info_TextAddress);
	    TextView textTime = (TextView)findViewById(R.id.info_TextTime);
	    TextView textDesc = (TextView)findViewById(R.id.info_TextDesc);
	    TextView textDist = (TextView)findViewById(R.id.info_TextDist);
	    textName.setText(name);
	    textAddress.setText(address);
	    textPhone.setText(phone);
	    textTime.setText(time);
	    textDesc.setText(desc);
	    
	    
	    locationX = Double.parseDouble(locx);
	    locationY = Double.parseDouble(locy);
	    
	    
	    //Toast.makeText(Information.this,locationX +"  , "  +locationY, Toast.LENGTH_LONG).show();
	    //LatLng ShopLocation = new LatLng(locationX, locationY);
	    //GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapShop)).getMap();
	    //Marker shopMarker = map.addMarker(new MarkerOptions().position(ShopLocation).title(name).snippet(time));
	    
	 // Move the camera instantly to Shop with a zoom of 16.
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(ShopLocation, 16));
	    shoplocation = new LatLng(locationY, locationX);
	    initialMap();
	    
	    
	    addMarkersToMap();
	    
	    locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
	    
	    criteria = new Criteria();
	    criteria.setAccuracy(Criteria.ACCURACY_COARSE);
	    
	    String provider = locationManager.getBestProvider(criteria, true);
	    
	    Location myLocation = locationManager.getLastKnownLocation(provider);
	    //this.myLocation = myLocation;
	    myLat = myLocation.getLatitude();
	    myLon = myLocation.getLongitude();
	    Toast.makeText(Information.this,myLat + " , " + myLon, Toast.LENGTH_LONG).show();
	    Button navi = (Button)findViewById(R.id.info_btnNavigate);
	    distance = locationDistance(myLat,myLon);
	    textDist.setText(Double.toString(distance)+"¤½¨½");
	    navi.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				direct(myLat,myLon,locationY,locationX);
				
			}
		});
	    //updateMyLocationInfo(myLocation);
	    //locationDistance();
	}
	
	private void initialMap() {
		if(map == null) {
			
			map = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapShop)).getMap();
			if(map != null) {
				setUpMap();
			}
		}
	}
	
	private void setUpMap() {
		map.setMyLocationEnabled(true);
		
		CameraPosition cameraPosition = new CameraPosition.Builder()
										.target(shoplocation)
										.zoom(17)
										.build();
		
		CameraUpdate cameraupdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
		
		map.animateCamera(cameraupdate);
		
		addMarkersToMap();
				
	}
	
	private void addMarkersToMap() {
		marker_shoploc = map.addMarker(new MarkerOptions()
				.position(shoplocation)
				.title(name)
				.snippet(time));
	}
	
	/*private String updateMyLocationInfo(Location mylLocation) {
		StringBuilder msg = new StringBuilder();
		msg.append(b)
	}*/
	
	private void direct(double fromLat, double fromLng, double toLat, double toLng) {
		String uriStr = String.format(
				"http://maps.google.com/maps?saddr=%f,%f&daddr=%f,%f", fromLat,
				fromLng, toLat, toLng);
		
		Intent intent = new Intent();
		intent.setClassName("com.google.android.apps.maps",
				"com.google.android.maps.MapsActivity");
		intent.setAction(android.content.Intent.ACTION_VIEW);
		
		intent.setData(Uri.parse(uriStr));
		
		startActivity(intent);
	}
	
	public double locationDistance(double fromLat, double fromLng) {
		//double distance = 0;
		double radLatitude1 = fromLat* Math.PI / 180;;
		double radLatitude2 =locationY * Math.PI / 180;
		double l = radLatitude1 - radLatitude2;
		double p = fromLng * Math.PI / 180 - locationX * Math.PI / 180;
		double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(l / 2), 2)
                + Math.cos(radLatitude1) * Math.cos(radLatitude2)
                * Math.pow(Math.sin(p / 2), 2)));
		distance = distance * 6378137.0;
		distance = Math.round(distance * 10000) / 10000;
		//float[] results = new float[1];
		//Location.distanceBetween(myLocation.getLatitude(), myLocation.getLongitude(), locationY, locationX, results);
		//String str_distance = NumberFormat.getInstance().format(results[0]);
		//Toast.makeText(Information.this,str_distance, Toast.LENGTH_LONG).show();
		//distance = Double.parseDouble(str_distance);
		return (distance/1000);
	}
	
}
