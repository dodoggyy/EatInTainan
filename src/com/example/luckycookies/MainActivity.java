package com.example.luckycookies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLXML;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import android.R.anim;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.app.ListActivity;




public class MainActivity extends Activity {
	
	 ArrayList<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
	//ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	private SimpleAdapter adapter;
	private ListView listView1;
	private int getPosition=0;
	private static final int MENU_SETTING = Menu.FIRST,
			MENU_ABOUT = Menu.FIRST + 1, MENU_ModifySQL = Menu.FIRST + 2,
			MENU_HELP = Menu.FIRST + 3,MENU_search_YouTube = Menu.FIRST + 4,
			MENU_MARKER = Menu.FIRST + 5,MENU_Wheel = Menu.FIRST + 6,
					MENU_REVEAL = Menu.FIRST + 7;
	/** CONTEXT MENU CONSTANTS **/
//	public final static int MODIFY_context_menu = Menu.FIRST;
//	public final static int REMOVE_context_menu = Menu.FIRST + 1;
//	public final static int ADD_JOB_context_menu = Menu.FIRST + 2;
	
	
	

	public List<String> sqls = new  ArrayList<String>();
	
   
	private File vSDCard = null;
	//private ArrayList<Dining> dining_data;
	
	public ArrayList<Dining> diningInfo;
	
	private Button btnSearch;
	private EditText editTxtSearch;
	private String searchKeyword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         listView1 = (ListView) findViewById(R.id.listView1);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new Button.OnClickListener(){ 
        
            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub
            	
                

            }         

        }); 
        Spinner spnCategory = (Spinner)findViewById(R.id.spnCategory);
        String[] test = FileHandler.getCategoryArray();
        ArrayAdapter<String> adapterSpinner;
        adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, test);
        spnCategory.setAdapter(adapterSpinner);
        
        spnCategory.setOnItemSelectedListener(new OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String shop_name = ((Spinner)parent).getSelectedItem().toString();
				String category = FileHandler.getCategoryArray()[position];
				ArrayList<Map<String, Object>> items= FileHandler.getItemsByCategory(category);
				reload(items,category);
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});   
        
        editTxtSearch = (EditText)findViewById(R.id.edtTxtSearch);
        searchKeyword = editTxtSearch.getText().toString();
     // 判斷 SD Card 有無插入
        if( Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED) )
        {
          
          //Toast.makeText(MainActivity.this, "SD card error", Toast.LENGTH_LONG).show();
          return;
        }
        else
        {
           // 取得 SD Card 位置
           vSDCard = Environment.getExternalStorageDirectory();
           //Toast.makeText(MainActivity.this, "SD位置:"+vSDCard, Toast.LENGTH_LONG).show();
        }
        
        String filePath = vSDCard.getParent() + "/" + vSDCard.getName()  +"/Download/dining.csv";
        diningInfo = FileHandler.getDiningInfo(filePath);
        items = FileHandler.getItems(diningInfo);
        
        //////////////////////////////////////////////////////////////////
        
        
//        dining_data = SQLHandler.getAllsong(this);
//      //如果找不到歌單,代表必須將歌單匯入資料庫
//        if (dining_data == null)
//        {
//        	 /*String[] mShopName =  new String[]{
//        		"歡迎來到Lucky Cookie~"	
//        	};
//        	 String[] mShopAddress = new String[] {
//					 "正再建立資料!!!"
//				 };
//        	
//        	for(int i=0; i<mShopName.length; i++){
//        		HashMap<String,String> item = new HashMap<String,String>();
//				 item.put( "name", mShopName[i]);
//				 item.put( "address",mShopAddress[i] );
//				 list.add( item );
//        		
//        	}
//        	
//        	adapter = new SimpleAdapter( 
//        			 this, 
//					 list,
//					 R.layout.list,
//					 new String[] { "name","address" },
//					 new int[] { R.id.list_shopname_textView, R.id.list_address_textView } );
//        	*/
//        	//ListActivity設定adapter
//			 //setListAdapter( adapter );
//			 
//			 //啟用按鍵過濾功能，這兩行資料都會進行過濾
//			 //getListView().setTextFilterEnabled(true);
//        	
//        	//loading file
//        	File vPath = new File( vSDCard.getParent() + vSDCard.getName() + "/" );
//            if( !vPath.exists() )
//               vPath.mkdirs();
//            
//            int i=0;
//            int j=0;
//            //int k=0;
//            FileReader rFile2;
//            try{
//            	rFile2 = new FileReader( vSDCard.getParent() + "/" + vSDCard.getName()  +"/Download/dining.csv" );
//            	BufferedReader br = new BufferedReader(rFile2);
//            	String line=null;
//            	while((line = br.readLine()) !=null){
//            	  j++;
//            	}
//            }catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            int max=150;
//            Toast.makeText(MainActivity.this, "總行數:"+j, Toast.LENGTH_LONG).show();
//            //開檔讀取SONG.txt
//            FileReader rFile;
//            
//            //if(i <= max && max <=j) {
//			try {
//				
//				rFile = new FileReader( vSDCard.getParent() + "/" + vSDCard.getName()  +"/Download/dining.csv" );				
//				//將token用,來讀取，放在資料庫中
//				BufferedReader br = new BufferedReader(rFile);
//                String line = null ;
//                
//               
//                while((line = br.readLine()) !=null)
//                {
//                	
//                  i++;
//                  if(i < 150){
//                 //if(i<max){
//                  /*String[]RowData = line.split(",");
//                  //String uid = RowData[0];
//                  String shop_name=RowData[2];
//                  String shop_id=RowData[0];
//                  String shop_number=RowData[1];
//                  String shop_description=RowData[3];
//                  String address=RowData[4];
//                  String telephone=RowData[5];
//                  String shop_time=RowData[6];
//                  String shop_locx=RowData[7];
//                  String shop_locy=RowData[8];
//                  String category=RowData[9];
//                  String change_time=RowData[10];
//                	*/
//                  
//                  //一個token一個toek看
//                  StringTokenizer stoken = new StringTokenizer( line, "," );
//                  Log.i("TAG", line);
//                  //String uid = "";
//                  String shop_name="";
//                  String shop_id="";
//                  String shop_number="";
//                  String shop_description="";
//                  String address="";
//                  String telephone="";
//                  String shop_time="";
//                  String shop_locx="";
//                  String shop_locy="";
//                  String category="";
//                  String change_time="";
//                  
//                  //然後加入到對應的欄位
//                  int count=0;
//                  while( stoken.hasMoreTokens() )
//                  {
//                    switch (count)
//                    {
//                      case 0:
//                        shop_id = stoken.nextToken();
//                        break;
//                      case 1:
//                        shop_number = stoken.nextToken();
//                        break;
//                      case 2:
//                      	shop_name = stoken.nextToken();
//                          break;  
//                      case 3:
//                    	shop_description = stoken.nextToken();
//                    	  //shop_description ="123";
//                        break;
//                      case 4:
//                    	  address = stoken.nextToken();
//                    	  //address = "456";
//                    	  break;
//                      case 5:
//                    	  telephone = stoken.nextToken();
//                    	  break;
//                      case 6:
//                    	  shop_time = stoken.nextToken();
//                    	  break;
//                      case 7:
//                    	  shop_locx = stoken.nextToken();
//                    	  break;
//                      case 8:
//                    	  shop_locy = stoken.nextToken();
//                    	  break;
//                      case 9:
//                    	  category = stoken.nextToken();
//                    	  break;
//                      case 10:
//                    	  change_time = stoken.nextToken();
//                    	  break;
//                      
//                    }
//                    count++;
//                  }               
//                 // Log.i("TAG", sid + "->" + name + "->" + singer);
//                  //寫入資料庫，編號，歌手，歌名
//                  //db.beginTransaction();
//                  //SQLHandler.insertSong(this, shop_name, shop_id ,shop_number, shop_description, address, telephone, shop_time, shop_locx,shop_locy,category,change_time);
//                  sqls.add(SQLHandler.insertSong2(this, shop_name, shop_id ,shop_number, shop_description, address, telephone, shop_time, shop_locx,shop_locy,category,change_time));
//                  }
//                //}
//                }
//                
//                //db.setTransactionSuccessful();
//                //db.endTransaction();
//                //db.close();
//                rFile.close();
//                SQLHandler.insertSong3(this, sqls);
//                Toast.makeText(MainActivity.this, "寫入完成", Toast.LENGTH_LONG).show();
//                //max+=150;
//                
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//            //}
//            
//	        //dining_data = SQLHandler.getAllsong(this);
//        }

        
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        
//         String[] mShopName =  new String[dining_data.size()];
//         String[] mShopDesc =  new String[dining_data.size()];
//         String[] mShopAddress =  new String[dining_data.size()];
//         String[] mShopPhone =  new String[dining_data.size()];
//         String[] mShopTime =  new String[dining_data.size()];
//         String[] mShopLocX =  new String[dining_data.size()];
//         String[] mShopLocY =  new String[dining_data.size()];
//         String[] mShopCate =  new String[dining_data.size()];
//        //final String[] mShopChange =  new String[dining_data.size()];
//        
//        for(int i=0; i<dining_data.size();i++){
//        	mShopName[i] = dining_data.get(i).shop_name;
//        	mShopDesc[i] = dining_data.get(i).shop_description;
//        	mShopAddress[i] = dining_data.get(i).address;
//        	mShopPhone[i] =dining_data.get(i).telephone;
//        	mShopTime[i] = dining_data.get(i).shop_time;
//        	mShopLocX[i] = dining_data.get(i).shop_locx;
//        	mShopLocY[i] = dining_data.get(i).shop_locy;
//        	mShopCate[i] = dining_data.get(i).category;
//        }
//        
//        //Toast.makeText(MainActivity.this, dining_data.size(), Toast.LENGTH_LONG).show();
//        /*for(int i=0; i< dining_data.size(); i++){
//        	HashMap<String,String> item = new HashMap<String,String>();
//        	//if(mShopName[i] != null && mShopTime[i]!= null && mShopAddress[i]!= null){
//        	 item.put( "name", mShopName[i]);
//        	 item.put( "time", mShopTime[i]);
//        	 item.put( "address", mShopAddress[i]);
//        	 item.put( "description", mShopDesc[i]);
//        	 list.add( item );
//        	//}
//        }*/
//        
//        for(int i=0; i< dining_data.size(); i++){
//        	 Map<String, Object> item = new HashMap<String,Object>();
//        	//if(mShopName[i] != null && mShopTime[i]!= null && mShopAddress[i]!= null){
//        	 item.put( "name", mShopName[i]);
//        	 item.put( "time", mShopTime[i]);
//        	 item.put( "address", mShopAddress[i]);
//        	 item.put( "description", mShopDesc[i]);
//        	 items.add( item );
//        }
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        
      //新增SimpleAdapter
//		 adapter = new SimpleAdapter( 
//		 this, 
//		 items,
//		 R.layout.list,
//		 new String[] { "name","address","time","description" },
//		 new int[] { R.id.list_shopname_textView, R.id.list_address_textView, R.id.list_shoptime_textView,R.id.list_desc_textView } );
//		 
		//ListActivity設定adapter
		 //setListAdapter(adapter);
		 
		 //啟用按鍵過濾功能，這兩行資料都會進行過濾
		 //getListView().setTextFilterEnabled(true);
		 
        /***/
        /*ListViewItemAdapter simpleAdapter = new ListViewItemAdapter(this, 
                items, R.layout.list, new String[] { "name","address","time","description" },
                new int[] { R.id.list_shopname_textView, R.id.list_address_textView, R.id.list_shoptime_textView,R.id.list_desc_textView } );*/
//        listView1.setAdapter(adapter);
//        
//        listView1.setOnItemClickListener(new OnItemClickListener() 
//	    {          
//	    	   @Override  
//	    	   public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
//	    	     long arg3) 
//	    	   {
//	    		   //Toast.makeText(MainActivity.this, "arg2:"+ arg2 + " arg3:" + arg3, Toast.LENGTH_LONG).show();
//	    		   //arg2 arg3 means select item x
//	    		   
//	    		   Intent app = new Intent(MainActivity.this, Information.class);
//	    		   //MainActivity.this.finish();
//	    		   Bundle bundle = new Bundle();
//	    		   bundle.putString("name", diningInfo.get(arg2).shop_name);
//	    		   bundle.putString("desc", diningInfo.get(arg2).shop_description);
//	    		   bundle.putString("locx", diningInfo.get(arg2).shop_locx);
//	    		   bundle.putString("locy", diningInfo.get(arg2).shop_locy);
//	    		   bundle.putString("phone", diningInfo.get(arg2).telephone);
//	    		   bundle.putString("address", diningInfo.get(arg2).address);
//	    		   bundle.putString("time", diningInfo.get(arg2).shop_time);
//	    		   //bundle.putString("Data_eurl", (String) ((TextView) arg1).getText());
//	    		   //bundle.putString("Data_eurl", "6XSabEWWFYI");
//	    		   app.putExtras(bundle);
//	    		   startActivity(app);
//	    	   }  
//	    });
		 
    }
   /* protected void onListItemClick(ListView l, View v, int position, long id) {
    	//dining_data = SQLHandler.getAllsong(this);
    	Intent intent = new Intent();
		intent.setClass(MainActivity.this, Information.class);
		//Bundle bundle = new Bundle();
		   //bundle.putString("name", dining_data.get(position).shop_name);
		  // bundle.putString("desc", dining_data.get(position).shop_description);
		  // bundle.putString("locx", dining_data.get(position).shop_locx);
		  // bundle.putString("locy", dining_data.get(position).shop_locy);
		  // bundle.putString("phone", dining_data.get(position).telephone);
		  // bundle.putString("address", dining_data.get(position).address);
		  // bundle.putString("time", dining_data.get(position).shop_time);
		   
		startActivity(intent);
    	//Toast.makeText(MainActivity.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
    	//ContactGroup selectedGroup = new ContactGroup();
        //selectedGroup = groups.get(position);
        //Toast.makeText(getBaseContext(), selectedGroup.Name + " ID #" + selectedGroup.Id, 1).show();
    }*/
    public void reload(ArrayList<Map<String, Object>> items, final 
    		String category){
    	
    	adapter = new SimpleAdapter( 
          		 this, 
          		 items,
          		 R.layout.list,
          		 new String[] { "name","address","time","description" },
          		 new int[] { R.id.list_shopname_textView, R.id.list_address_textView, R.id.list_shoptime_textView,R.id.list_desc_textView } );
    	
listView1.setAdapter(adapter);
		final ArrayList<Map<String, Object>> items2 = items;
        listView1.setOnItemClickListener(new OnItemClickListener() 
	    {          
	    	   @Override  
	    	   public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
	    	     long arg3) 
	    	   {
	    		   //Toast.makeText(MainActivity.this, "arg2:"+ arg2 + " arg3:" + arg3, Toast.LENGTH_LONG).show();
	    		   //arg2 arg3 means select item x
	    		   String  shop_name = (String) items2.get(arg2).get("name");
	    		   Intent app = new Intent(MainActivity.this, Information.class);
	    		   //MainActivity.this.finish();
	    		   ArrayList<Dining>dininginfo = FileHandler.getDiningsByCategory(category); 
	    		   Dining dining = FileHandler.getTargetDining(dininginfo, shop_name);
	    		   Bundle bundle = new Bundle();
	    		   bundle.putString("name", dining.shop_name);
	    		   bundle.putString("desc", dining.shop_description);
	    		   bundle.putString("locx", dining.shop_locx);
	    		   bundle.putString("locy", dining.shop_locy);
	    		   bundle.putString("phone", dining.telephone);
	    		   bundle.putString("address", dining.address);
	    		   bundle.putString("time", dining.shop_time);
	    		   //bundle.putString("Data_eurl", (String) ((TextView) arg1).getText());
	    		   //bundle.putString("Data_eurl", "6XSabEWWFYI");
	    		   app.putExtras(bundle);
	    		   startActivity(app);
	    	   }  
	    });
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
		SubMenu submenu = menu.addSubMenu(0, MENU_SETTING, 0, "設定").setIcon(
				android.R.drawable.ic_menu_preferences);

		//submenu.add(0, MENU_PRIVATE_MES, 0, "私人訊息");
		//submenu.add(0, MENU_GROUP, 1, "群組");
		submenu.add(0, MENU_HELP, 2, "幫助");
		submenu.add(0, MENU_REVEAL, 3, "美食標籤");
		//submenu.add(0, MENU_PRIVACY, 4, "隱私選項");
		menu.add(0, MENU_ABOUT, 1, "關於").setIcon(
				android.R.drawable.ic_dialog_info);
		menu.add(0, MENU_ModifySQL,Menu.NONE, "修改資料");
		//menu.add(0, MENU_search_YouTube,Menu.NONE, "搜尋影片");
		menu.add(0, MENU_MARKER, 2, "美食標籤").setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);
		menu.add(0, MENU_Wheel,Menu.NONE, "幸運轉盤");
		//menu.add(0,MENU_CARD_MANAGE, Menu.NONE, "名片管理");
		//menu.add(0,Menu_PERSONAL_CARD, Menu.NONE, "個人名片");

		return super.onCreateOptionsMenu(menu);

	}
    
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case MENU_ABOUT:
				new AlertDialog.Builder(MainActivity.this)
				.setTitle("關於此程式")
				.setMessage(R.string.about)
				.setIcon(android.R.drawable.star_big_on)
				.show();
				break;
				
			case MENU_MARKER:
				Bundle bundle = FileHandler.getMapBundle(diningInfo);
				Intent intent6 = new Intent();
				intent6.putExtras(bundle);
				intent6.setClass(MainActivity.this, MapTest.class);
				startActivity(intent6);
				break;
			
			case MENU_Wheel:
					Bundle bundle3 = FileHandler.getMapBundle(diningInfo);
					Intent intent3 = new Intent();
					intent3.putExtras(bundle3);
//		    		   bundle.putString("desc", diningInfo.get(arg2).shop_description);
//		    		   bundle.putString("locx", diningInfo.get(arg2).shop_locx);
//		    		   bundle.putString("locy", diningInfo.get(arg2).shop_locy);
//		    		   bundle.putString("phone", diningInfo.get(arg2).telephone);
//		    		   bundle.putString("address", diningInfo.get(arg2).address);
//		    		   bundle.putString("time", diningInfo.get(arg2).shop_time);   
		    		   
				
				intent3.setClass(MainActivity.this, Wheel.class);
				startActivity(intent3);
				break;	
				
			case MENU_HELP:
				new AlertDialog.Builder(MainActivity.this)
				.setTitle("幫助")
				.setMessage(R.string.help)
				.setIcon(android.R.drawable.star_big_on)
				.setPositiveButton("確定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				})
				.show();
				break;
			case MENU_ModifySQL:
				Intent intent4 = new Intent();
				intent4.setClass(MainActivity.this, SQLHandler.class);
				startActivity(intent4);
				break;
			/*case MENU_PRIVATE_MES:
				Intent intent3 = new Intent();
				intent3.setClass(MainActivity.this, Gallery.class);
				startActivity(intent3);
				break;*/
			/*case MENU_search_YouTube: 
				Intent intent4 = new Intent();
				intent4.setClass(MainActivity.this, YoutubeSearch.class);
				startActivity(intent4);
				break;*/
			/*case MENU_REVEAL:
				Bundle bundle = FileHandler.getMapBundle(diningInfo);
				Intent intent6 = new Intent();
				intent6.putExtras(bundle);
				intent6.setClass(MainActivity.this, MapTest.class);
				startActivity(intent6);
				
		        break;*/
			/*case MENU_CONTACTS:
				Intent intent5 = new Intent();
				intent5.setClass(Main.this, contact.class);
				startActivity(intent5);
				break;*/
			/*case MENU_CARD_MANAGE:
				Intent intent7 = new Intent();
				intent7.setClass(Main.this, cardmanage.class);
				startActivity(intent7);
				break;*/
			/*case Menu_PERSONAL_CARD:
				Intent intent = new Intent();
				intent.setClass(Main.this, PersonalCard.class);
				startActivity(intent);
				break;*/
			/*case MENU_PRIVACY:
				Intent intent8 = new Intent();
				intent8.setClass(Main.this, privatedec.class);
				startActivity(intent8);
				break;*/	
				
				
		}
		return super.onOptionsItemSelected(item);
		
		
	}
    
    

   

    
}