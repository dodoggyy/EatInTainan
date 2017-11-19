package com.example.luckycookies;

import java.util.ArrayList;
import java.util.Map;


import com.example.luckycookies.CircularArrayAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

public class Wheel extends Activity{
	private ArrayList<String>name;
	private ArrayList<String>time;
	private ArrayList<String>locx;
	private ArrayList<String>locy;
	private ListView ListView01;
	  private TextView TextView01;
	  private TextView TextView02;
	  private ArrayList<Map<String, Object>> items;
	  private Button mbuttonOKPlay;
	  //private Button mbuttonWheel;
	  private int index = 0;
	  private GoogleMap map;
	  private LatLng NCKU;
	  
	  public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.wheel);
			Intent intent = getIntent();
		    Bundle bundle = intent.getExtras();
		    final ArrayList<String>name = bundle.getStringArrayList("shop_name");
		    final ArrayList<String>time = bundle.getStringArrayList("shop_time");
		    final ArrayList<String>desc = bundle.getStringArrayList("description");
		    final ArrayList<String>address = bundle.getStringArrayList("address");
		    final ArrayList<String>phone = bundle.getStringArrayList("telephone");
		    locx = bundle.getStringArrayList("locx");
		    locy = bundle.getStringArrayList("locy");
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
		   // mbuttonOKPlay = (Button)findViewById(R.id.buttonOKPlay);
			//mbuttonOKPlay.setOnClickListener(OKPlayListner);
		//	mbuttonWheel = (Button)findViewById(R.id.buttonWheel);
			
			//mbuttonWheel.setOnClickListener(WheelRandListner); 
//			String category = FileHandler.getCategoryArray()[position];
//			items= FileHandler.getItemsByCategory(category);
			final String[] s2 = new String[name.size()];
//			//s2[0]="";
//			//s2[1]="";
//			//s2[songdata.size()+1]="";
//			//s2[songdata.size()+2]="";
			for(int i = 0; i < name.size();i++) {
				s2[i] = name.get(i);
//				//s2[i+2] = songdata.get(i).name;
			}
			ListView01 = (ListView)findViewById(R.id.ListViewWheel);
		    TextView01 = (TextView)findViewById(R.id.TextViewSonginfo);
		    TextView02 = (TextView)findViewById(R.id.TextViewSingerinfo);
		
		    /* 將字串陣列放至ArrayAdapter */
		    //ArrayAdapter<String> list1 = new ArrayAdapter<String>(this,R.layout.file_row, s2);
		    CircularArrayAdapter list1 = new CircularArrayAdapter(this,R.layout.file_row, s2);
		    /* 設定ListView的Adapter */
		    ListView01.setAdapter(list1);
		    /* 捲動時透明化 */
		    ListView01.setCacheColorHint(00000000);
		    ListView01.setFastScrollEnabled(true);
		    /* 霧化邊緣 */
		    ListView01.setFadingEdgeLength(100);
//
		    ListView01.setOnScrollListener(new ListView.OnScrollListener()
		    {
		      @Override
		      public void onScroll(AbsListView view, int firstVisibleItem,
		          int visibleItemCount, int totalItemCount)
		      {
		        // TODO Auto-generated method stub
		        /* 取得第一個顯示的下兩格的值 */
		    	//if(firstVisibleItem < songdata.size() ) {
		    	  if (firstVisibleItem <= 2)   
		    		  ListView01.setSelection(name.size());
		    	TextView01.setText("店名:"+name.get((firstVisibleItem+1)%name.size()));
		        TextView02.setText("營業時間:"+time.get((firstVisibleItem+1)%name.size()));
		        index = (firstVisibleItem+1)%name.size();
		        String mShopName = name.get((firstVisibleItem+1)%name.size());
				//mTime = time.get(i);
				String mLocx = locx.get((firstVisibleItem+1)%name.size());
				String mLocy = locy.get((firstVisibleItem+1)%name.size());		
				LatLng lat = new LatLng(Double.parseDouble(mLocy), Double.parseDouble(mLocx));
	       
	       
		        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lat, 16));
		    	//}
		      }
		      @Override
		      public void onScrollStateChanged(AbsListView view, int scrollState)
		      {
		        // TODO Auto-generated method stub
		      }
		    });

		    ListView01.setOnItemClickListener(new ListView.OnItemClickListener()
		    {
		      @Override
		      public void onItemClick(AdapterView<?> parent, View v, int id, long arg3)
		      {
		        // TODO Auto-generated method stub
		    	  
		        /* 設定點選的上兩格為開始 */
		        //ListView01.setSelectionFromTop((id - 2)%songdata.size(), 0);
		        //TextView01.setText(s2[id%songdata.size()]);
		        
		      }
		    });

		    ListView01.setOnItemSelectedListener(new ListView.OnItemSelectedListener()
		    {
		      @Override
		      public void onItemSelected(AdapterView<?> parent, View v, int id,
		          long arg3)
		      {
		        // TODO Auto-generated method stub
		        TextView01.setText(name.get(id));
		      }

		      @Override
		      public void onNothingSelected(AdapterView<?> arg0)
		      {
		        // TODO Auto-generated method stub
		      }
		    });
		}
		
//		private Button.OnClickListener OKPlayListner = new Button.OnClickListener() {
//
//			@Override
////			public void onClick(View arg0) {
////				// TODO Auto-generated method stub
////				//Intent app = new Intent(Wheel.this, SongPlayer.class);
////	 		   //MainActivity.this.finish();
////	 		   Bundle bundle = new Bundle();
////	 		  
////	 		   
////	 		   bundle.putString("Data_eurl", songdata.get(index).sid);
////	 		   bundle.putString("Song_name", songdata.get(index).name);
////	 		   bundle.putString("Singer", songdata.get(index).singer);
//	 		   //bundle.putString("Data_eurl", (String) ((TextView) arg1).getText());
//	 		   //bundle.putString("Data_eurl", "6XSabEWWFYI");
//	 		   app.putExtras(bundle);
//	 		   startActivity(app);
//			}
//			
//		};
		
		private Button.OnClickListener WheelRandListner = new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				

				int i = 0;
				i = (int)(Math.random()*name.size());
				for(int j = 0; j < i; j++){
				
				ListView01.setSelectionFromTop(j, 0);}
				index = i;
				
			}
			
		};
}
