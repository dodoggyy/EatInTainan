package com.example.luckycookies;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;



import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Build;
import android.provider.MediaStore;



public class SQLHandler extends Activity{
	private static ArrayList<Dining> ndata = null;
	
	public static SQLHelper helper;
	public static SQLiteDatabase db;
	
	String picturePath = "";
	OnClickListener listener_add = null;
	OnClickListener listener_update = null;
	OnClickListener listener_delete = null;
	OnClickListener listener_clear = null;
	
	Button button_add;
	Button button_update;
	Button button_delete;
	Button button_clear;
	//DBConnection helper;
	
	private final static String CACHE = "/css";
	private static int RESULT_LOAD_IMAGE = 1;
	private EditText mEdtTxtCardName,
	 mEdtTxtCardNickName,
	 mEdtTxtJob,
	 mEdtTxtHomeNum,
	 mEdtTxtCompanyNum,
	 mEdtTxtFAXNum,
	 mEdtTxtCompanyName,
	 mEdtTxtCompanyAddress,
	 mEdtTxtEmail;
	public static int id_this; 
	/*public interface UserSchema {
		String TABLE_NAME = "DB_TABLE";
		String ID = "_id";
		String NAME = "name";
		String NICKNAME = "niickname";
		String JOB = "job";
		String BDATE = "bdate";
		String HOME_NUM = "home_num";
		String COMPANY_NUM = "company_num";
		String FAX_NUM = "fax_num";
		String COMPANY_ADDERSS = "company_address";
		String EMAIL = "email";
		String COMPANY_NAME = "company_name";
	}*/
	/*public interface UserSchema {
		String TABLE_NAME = "Users";          //Table Name
		String ID = "_id";                    //ID
		String USER_NAME = "user_name";       //User Name
		String ADDRESS = "address";           //Address
		String TELEPHONE = "telephone";       //Phone Number
		String MAIL_ADDRESS = "mail_address"; //Mail Address
		String NICKNAME = "nickname";		//
		String JOB = "job";
		String COMPANY = "company";
		String FAX = "FAX";
		String PICTURE = "picture";
	}*/
	/*public interface UserSchema {
		String TABLE_NAME = "Dining";          //Table Name
		String ID = "_id";                    //ID
		String SHOP_NAME = "shop_name";       //Shop Name
		String SHOP_ID = "shop_id";            //Shop ID
		String SHOP_NO = "shop_number";       //Shop Number
		String DESCRIPTION = "shop_description";   //Shop description 
		String ADDRESS = "address";           //Shop Address
		String TELEPHONE = "telephone";       //Phone Number
		String SHOP_TIME = "shop_time"; //Mail Address
		String LOCX = "shop_locx";		//x
		String LOCY = "shop_locy";		//y
		String CATEGORY = "category";  
		String CHANGE_TIME = "change_time";
		
	}*/
	
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_handler);
        Context context = this;
        /////////////
      
       /* Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				
				Intent i = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				
				startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		});*/
        ////////////
     
		
		/*ContentValues values = new ContentValues();
		values.put(UserSchema.USER_NAME, mEditText01.getText().toString());
		values.put(UserSchema.TELEPHONE, mEditText02.getText().toString());
		values.put(UserSchema.ADDRESS, mEditText03.getText().toString());
		values.put(UserSchema.MAIL_ADDRESS, mEditText04.getText().toString());
		values.put(UserSchema.NICKNAME, mEditText05.getText().toString());
		values.put(UserSchema.JOB, mEditText06.getText().toString());
		values.put(UserSchema.COMPANY, mEditText07.getText().toString());
		values.put(UserSchema.FAX, mEditText08.getText().toString());
		values.put(UserSchema.PICTURE,picturePath.toString() );
		SQLiteDatabase db = helper.getWritableDatabase();
		db.insert(UserSchema.TABLE_NAME, null, values);
		db.close();
		onCreate(savedInstanceState);*/
		/*
		//////TEST/////////
		ImageView imageView_save = (ImageView)findViewById(R.id.imgView);
		imageView_save.buildDrawingCache();
		Bitmap bmp=imageView_save.getDrawingCache();
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		int newWidth = 480;
		int newHeight = 525;
		float scaleWidth = ((float)newWidth) /width;
		float scaleHeight = ((float)newHeight) / height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, newHeight);
		Bitmap newbm = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix,true);
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		newbm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte bytes[] = stream.toByteArray();
		
		String base64 = Base64.encodeToString(bytes, Base64.DEFAULT);
		
		ContentValues cv = new ContentValues();
		cv.put("image", base64);
		
		long long1 = db.insert(UserSchema.TABLE_NAME, null, cv);
		*/
        /***/
        /*String uid = "";
        String shop_name="1";
        String shop_id="2";
        String shop_number="3";
        String shop_description="4";
        String address="5";
        String telephone="6";
        String shop_time="7";
        String shop_locx="8";
        String shop_locy="9";
        String category="10";
        String change_time="11";
        SQLHandler.insertSong(this, shop_name, shop_id ,shop_number, shop_description, address, telephone, shop_time, shop_locx,shop_locy,category,change_time);
        dining_data = SQLHandler.getAllsong(this);*/
        Toast.makeText(SQLHandler.this, "儲存成功", Toast.LENGTH_LONG).show();
        
        
    	ImageView imageView = (ImageView) findViewById(R.id.imgView);
        final EditText mEditTextId = (EditText)findViewById(R.id.EditTextID);
		final EditText mEditTextNumber = (EditText)findViewById(R.id.EditTextNumber);
		final EditText mEditTextName = (EditText)findViewById(R.id.EditTextShopName);
		final EditText mEditTextAddress = (EditText)findViewById(R.id.EditTextAddress);
		final EditText mEditTextPhone = (EditText)findViewById(R.id.EditTextPhone);
		final EditText mEditTextShopTime = (EditText)findViewById(R.id.EditTextTime);
		final EditText mEditTextLocX = (EditText)findViewById(R.id.EditTextLocX);
		final EditText mEditTextLocY = (EditText)findViewById(R.id.EditTextLocY);
		final EditText mEditTextCate = (EditText)findViewById(R.id.EditTextCategory);
		final EditText mEditTextChange = (EditText)findViewById(R.id.EditTextChangTime);
		final EditText mEditTextDes = (EditText)findViewById(R.id.EditTextDescription);
		//建立資料庫PhoneBookDB和表單Table:Users
		/*helper = new DBConnection(this);
        final SQLiteDatabase db = helper.getWritableDatabase();	
        final String[] FROM = 
		{   
        	//UserSchema.ID,
        	UserSchema.SHOP_ID,
        	UserSchema.SHOP_NAME,
        	UserSchema.DESCRIPTION, 
        	UserSchema.ADDRESS,
        	UserSchema.TELEPHONE,
        	UserSchema.SHOP_TIME,
        	UserSchema.LOCX,
        	UserSchema.LOCY,
        	UserSchema.CATEGORY,
        	UserSchema.CHANGE_TIME
		};
        Cursor c = db.query(UserSchema.TABLE_NAME, new String[] {UserSchema.SHOP_ID}, null, null, null, null, null, null);
		c.moveToFirst();
		CharSequence[] list = new CharSequence[c.getCount()];
		for (int i = 0; i < list.length; i++) {
				list[i] = c.getString(0);
				c.moveToNext();
		}
		c.close();*/
		
		 final ArrayList<Dining> dining_data;
		 dining_data = SQLHandler.getAllsong(this);
		//顯示USER_NAME在Spinner表單-spinner上
		Spinner spinner = (Spinner)findViewById(R.id.Spinner01);
		spinner.setAdapter(new ArrayAdapter<Dining>(this, android.R.layout.simple_spinner_item, dining_data));
		//在Spinner表單-spinner上選定查詢資料，顯示所有資料在畫面上
		spinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String shop_name = ((Spinner)parent).getSelectedItem().toString();
				/*Cursor c = db.query("Dining", FROM , "shop_name='" + shop_name + "'", null, null, null, null, null);
				c.moveToFirst();*/
				//id_this = Integer.parseInt(c.getString(0));
				String shop_name_this = dining_data.get(position).shop_name;
				String shop_id_this = dining_data.get(position).shop_id;
				String shop_no_this = dining_data.get(position).shop_number;
				String desc_this = dining_data.get(position).shop_description;
				String address_this = dining_data.get(position).address;
				String phone_this = dining_data.get(position).telephone;
				String time_this = dining_data.get(position).shop_time;
				String locx_this = dining_data.get(position).shop_locx;
				String locy_this = dining_data.get(position).shop_locy;
				String cate_this = dining_data.get(position).category;
				String change_this = dining_data.get(position).change_time;
				
				//c.close();
				mEditTextId.setText(shop_id_this);
				mEditTextNumber.setText(shop_no_this);
				mEditTextName.setText(shop_name_this);
				mEditTextDes.setText(desc_this);
				mEditTextAddress.setText(address_this);/**/
				mEditTextPhone.setText(phone_this);
				mEditTextShopTime.setText(time_this);
				mEditTextLocX.setText(locx_this);
				mEditTextLocY.setText(locy_this);
				mEditTextCate.setText(cate_this);
				mEditTextChange.setText(change_this);
				//if(!picture_this.isEmpty())
				ImageView imageView = (ImageView)findViewById(R.id.imgView);
				 
				 
				 
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});    
		//按下[Add]按鈕時，新增一筆資料
        listener_add = new OnClickListener() {	
			public void onClick(View v) {
				
			}
		};
		//按下[Update]按鈕時，更新一筆資料
		listener_update = new OnClickListener() {
			public void onClick(View v) {
				/*ContentValues values = new ContentValues();
				values.put(UserSchema.USER_NAME, mEditText01.getText().toString());
				values.put(UserSchema.TELEPHONE, mEditText02.getText().toString());
				values.put(UserSchema.ADDRESS, mEditText03.getText().toString());
				values.put(UserSchema.MAIL_ADDRESS, mEditText04.getText().toString());
				values.put(UserSchema.NICKNAME, mEditText05.getText().toString());
				values.put(UserSchema.JOB, mEditText06.getText().toString());
				values.put(UserSchema.COMPANY, mEditText07.getText().toString());
				values.put(UserSchema.FAX, mEditText08.getText().toString());
				values.put(UserSchema.PICTURE,picturePath.toString() );
				String where = UserSchema.SHOP_ID + " = " + id_this;
				SQLiteDatabase db = helper.getWritableDatabase();
				db.update(UserSchema.TABLE_NAME, values, where ,null);
				db.close();*/
				onCreate(savedInstanceState);
				Toast.makeText(SQLHandler.this, "更新成功", Toast.LENGTH_LONG).show();
			}
		};
		//按下[Delete]按鈕時，刪除一筆資料
		listener_delete = new OnClickListener() {
			public void onClick(View v) {
				/*String where = UserSchema.SHOP_ID + " = " + id_this;
				SQLiteDatabase db = helper.getWritableDatabase();
				db.delete(UserSchema.TABLE_NAME, where ,null);
				db.close();*/
				onCreate(savedInstanceState);
				Toast.makeText(SQLHandler.this, "刪除成功", Toast.LENGTH_LONG).show();
			}
		};
		//按下[Clear]按鈕時，清空輸入欄位
		listener_clear = new OnClickListener() {
			public void onClick(View v) {
				mEditTextName.setText("");
				mEditTextPhone.setText("");
				mEditTextAddress.setText("");
				mEditTextShopTime.setText("");
				mEditTextLocX.setText("");/**/
				mEditTextLocY.setText("");
				mEditTextCate.setText("");
				mEditTextChange.setText("");
				mEditTextId.setText("");
				mEditTextNumber.setText("");
				mEditTextDes.setText("");
				
			}
		};
		//設定BUTTON0i,i=1,2,3,4的OnClickListener
		button_add = (Button)findViewById(R.id.Button01);
		button_add.setOnClickListener(listener_add);
		button_update = (Button)findViewById(R.id.Button02);
		button_update.setOnClickListener(listener_update);
		button_delete = (Button)findViewById(R.id.Button03);
		button_delete.setOnClickListener(listener_delete);	
		button_clear = (Button)findViewById(R.id.Button04);
		button_clear.setOnClickListener(listener_clear);	
    }
    //SQLiteOpenHelper-建立資料庫PhoneBookDB和Table:Users
    /*public static class DBConnection extends SQLiteOpenHelper {
		private static final String DATABASE_NAME = "PhoneBookDB";
		private static final int DATABASE_VERSION = 1;
		public DBConnection(Context ctx) {
			super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public void onCreate(SQLiteDatabase db) {
			String sql = "CREATE TABLE " + UserSchema.TABLE_NAME + " (" 
			+ UserSchema.ID  + " INTEGER primary key autoincrement, " 
			+ UserSchema.SHOP_ID + " text not null, " 
			+ UserSchema.SHOP_NO + " text not null, " 
			+ UserSchema.DESCRIPTION + " text not null, "
			+ UserSchema.ADDRESS + " text not null, "
			+ UserSchema.TELEPHONE + " text not null, "
			+ UserSchema.SHOP_TIME + " text not null, "
			+ UserSchema.LOCX + " text not null, "
			+ UserSchema.LOCY + " text not null, "
			+ UserSchema.CATEGORY + " text not null, "
			+ UserSchema.CHANGE_TIME + " text not null "+ ");";
			//Log.i("haiyang:createDB=", sql);
			db.execSQL(sql);	
		}
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub	
			db.execSQL("DROP TABLE IF EXISTS newMemorandum"); //刪除舊有的資料表
			   onCreate(db);
		}
	}*/
    //////////////////////////
   /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			picturePath = cursor.getString(columnIndex);
			
			Bitmap bitmap = BitmapFactory.decodeFile(picturePath);//resize
			//set rotate
			int w = bitmap.getWidth();
			int h = bitmap.getHeight();

			//set final size
			int destWidth = 400;
			int destHeigth = 400;
			
			float scaleWidth = ((float) destWidth) / w;
			float scaleHeight = ((float) destHeigth) / h;
			
			Matrix mtx = new Matrix();
			mtx.postScale(scaleWidth, scaleHeight);

			// Rotating Bitmap
			Bitmap rotatedBMP = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
			BitmapDrawable bmd = new BitmapDrawable(rotatedBMP);//如果要在畫面中顯示才需要
			cursor.close();
			
			ImageView imageView = (ImageView)findViewById(R.id.imgView);
			imageView .setImageDrawable(bmd);
			Toast.makeText(SQLHandler.this, picturePath, Toast.LENGTH_LONG).show();
			
			
		}
		
   }*/
   public static void insertSong(Context context, String shop_name, String shop_id, String shop_number, String shop_description, String address, String telephone, String shop_time, String shop_locx, String shop_locy, String category, String change_time){
		SQLHelper helper = new SQLHelper(context, SQLHelper.DB_NAME, null, 1);
		db = helper.getWritableDatabase();
		
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd");
		String mdate = dateFormat.format(new Date());
		
		java.text.SimpleDateFormat dateFormat2 = new java.text.SimpleDateFormat("HH:mm");
		String mtime = dateFormat2.format(new Date());
		String sql = "insert into "+ SQLHelper.INFO_TABLE + "(" +
			 Dining.SHOP_NAME+", "+ Dining.SHOP_ID+ ", "  + Dining.SHOP_NO + ", " + Dining.DESCRIPTION + ", " + Dining.ADDRESS + ", " + Dining.TELEPHONE + ", " + Dining.SHOP_TIME + ", " + Dining.LOCX + ", " + Dining.LOCY + ", " + Dining.CATEGORY + ", " + Dining.CHANGE_TIME +")" +
			"values('" + shop_name + "', '" + shop_id + "', '" + shop_number + "', '" + shop_description +"', '" + address +"', '"+ telephone +"', '" + shop_time +"', '" + shop_locx +"', '"+ shop_locy +"', '" + category +"', '" + change_time + "');";
		//db.beginTransaction();
		db.execSQL(sql);
		
		db.close();
		//Toast.makeText(context, "", Toast.LENGTH_LONG).show();
	}
   
   
   public static String insertSong2(Context context, String shop_name, String shop_id, String shop_number, String shop_description, String address, String telephone, String shop_time, String shop_locx, String shop_locy, String category, String change_time){
		//SQLHelper helper = new SQLHelper(context, SQLHelper.DB_NAME, null, 1);
		//db = helper.getWritableDatabase();
		
		//java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd");
		//String mdate = dateFormat.format(new Date());
		
		//java.text.SimpleDateFormat dateFormat2 = new java.text.SimpleDateFormat("HH:mm");
		//String mtime = dateFormat2.format(new Date());
		String sql = "insert into "+ SQLHelper.INFO_TABLE + "(" +
			 Dining.SHOP_NAME+", "+ Dining.SHOP_ID+ ", "  + Dining.SHOP_NO + ", " + Dining.DESCRIPTION + ", " + Dining.ADDRESS + ", " + Dining.TELEPHONE + ", " + Dining.SHOP_TIME + ", " + Dining.LOCX + ", " + Dining.LOCY + ", " + Dining.CATEGORY + ", " + Dining.CHANGE_TIME +")" +
			"values('" + shop_name + "', '" + shop_id + "', '" + shop_number + "', '" + shop_description +"', '" + address +"', '"+ telephone +"', '" + shop_time +"', '" + shop_locx +"', '"+ shop_locy +"', '" + category +"', '" + change_time + "');";
		//db.beginTransaction();
		//db.execSQL(sql);
		return sql;
		
		//db.close();
		//Toast.makeText(context, "", Toast.LENGTH_LONG).show();
	}
   
   public static void insertSong3(Context context,List<String> sqls){
	   SQLHelper helper = new SQLHelper(context, SQLHelper.DB_NAME, null, 1);
	   helper.insertOrUpdateDateBatch(sqls);
	   
		
		
	}
   
 //取得資料庫所有歌曲
 	public static ArrayList<Dining> getAllsong(Context context)
 	{
 	
      helper = new SQLHelper(context, SQLHelper.DB_NAME, null, 1);
     SQLiteDatabase db = helper.getWritableDatabase();
     java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd");
     String mdate = dateFormat.format(new Date());
     
     java.text.SimpleDateFormat dateFormat2 = new java.text.SimpleDateFormat("HH:mm");
     String mtime = dateFormat2.format(new Date());
    // Cursor c = db.rawQuery("select id, "+SQLiteHelper.D_TITLE+","+SQLiteHelper.D_TIME+" from "+SQLiteHelper.DIARY_TABLE+" order by id desc", null);

     ndata = new ArrayList<Dining>();
    
     try{
       //取所有歌的資料庫
       //Cursor cursor = SQLHandler.getdata(context, SQLiteHelper.INFO_TABLE, null);
       Cursor cursor = db.rawQuery("select * from "+SQLHelper.INFO_TABLE, null);
       cursor.moveToFirst();
       
       //no data
       if (cursor.isAfterLast())
       {
         return null;
       }
       
         
         while(!cursor.isAfterLast())
         {
           Dining newfile = new Dining();
           
           newfile.id= cursor.getString(0);
           newfile.shop_name= cursor.getString(1);
           newfile.shop_id= cursor.getString(2);
           newfile.shop_number= cursor.getString(3);
           newfile.shop_description= cursor.getString(4);
           newfile.address= cursor.getString(5);
           newfile.telephone= cursor.getString(6);
           newfile.shop_time= cursor.getString(7);
           newfile.shop_locx= cursor.getString(8);
           newfile.shop_locy= cursor.getString(9);
           newfile.category= cursor.getString(10);
           newfile.change_time= cursor.getString(11);
           
           ndata.add(newfile);

           cursor.moveToNext();
         }
         cursor.close();
   }catch(IllegalArgumentException e){
       e.printStackTrace();
   }    //db.close();
     //Toast.makeText(context, "", Toast.LENGTH_LONG).show();
     db.close();
     return ndata;
   }
}
