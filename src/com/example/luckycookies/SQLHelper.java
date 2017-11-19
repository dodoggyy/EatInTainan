package com.example.luckycookies;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SQLHelper extends SQLiteOpenHelper{
	public SQLHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public static final String DB_NAME = "Dining.info";
	public static final String USER_REC_TABLE = "user_rec_table";
	
	public static final String INFO_TABLE = "Dining";
	
	
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//手機資料庫的建立
		db.execSQL("CREATE TABLE IF NOT EXISTS " +
		    INFO_TABLE + "(" +
				Dining.ID + " integer primary key," +
				Dining.SHOP_NAME + " varchar," +
				Dining.SHOP_ID + " varchar," +
				Dining.SHOP_NO + " varchar," +
				Dining.DESCRIPTION + " varchar," +
				Dining.ADDRESS + " varchar," +
				Dining.TELEPHONE + " varchar," +
				Dining.SHOP_TIME + " varchar," +
				Dining.LOCX + " varchar," +
				Dining.LOCY + " varchar," +
				Dining.CATEGORY + " varchar," +
				Dining.CHANGE_TIME + " varchar" +
				");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersio, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + USER_REC_TABLE);
		onCreate(db);
	}
	public void updateColumn(SQLiteDatabase db, String oldColumn, String newColumn, String typeColumn){
		try{
			db.execSQL("ALTER TABLE " +
			    USER_REC_TABLE + " CHANGE " +
					oldColumn + " "+ newColumn +
					" " + typeColumn
			);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void insertAll(String databaseName,
            ArrayList<ContentValues> valuesArr) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        for (ContentValues val : valuesArr) {
            db.insert(databaseName, null, val);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }
	
	public void insertOrUpdateDateBatch(List<String> sqls) {   
        SQLiteDatabase db = getWritableDatabase();   
        db.beginTransaction();   
        try {   
            for (String sql : sqls) {   
                db.execSQL(sql);   
            }   
            // 设置事务标志为成功，当结束事务时就会提交事务   
            db.setTransactionSuccessful();   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            // 结束事务   
            db.endTransaction();   
            db.close();   
        }   
    } 
	 
}
