package com.example.abhinabera.jainmarketing_order;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.os.Build.ID;

/**
 * Created by Abhi Nabera on 7/12/2017.
 */

public class ItemDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Items.db";

    public static final String TABLE_NAME = "item_details";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ITEM_NAME";
    public static final String COL_3 = "SELLING_RATE";



    public ItemDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, ITEM_NAME TEXT, SELLING_RATE REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String item_name, double selling_rate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, item_name);
        contentValues.put(COL_3, selling_rate);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }
        else
            return true;
    }

    public ArrayList<String> getAllItems(){

        ArrayList<String> allItemNames = new ArrayList<String>();

        String uniqueItems = "select DISTINCT " + COL_2 + " from " + TABLE_NAME;

        Cursor cursor = getReadableDatabase().rawQuery(uniqueItems, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                allItemNames.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }

        cursor.close();

        return allItemNames;
    }

    public ArrayList<Double> getSellingRate(String item_name){
        ArrayList<Double> sellingRate = new ArrayList<Double>();

        String selling_rate = "select SELLING_RATE from item_details where ITEM_NAME = " + "\"" +  item_name + "\"";
        Cursor cursor = getReadableDatabase().rawQuery(selling_rate, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){
                sellingRate.add(cursor.getDouble(0));
                cursor.moveToNext();
            }
        }

        cursor.close();

        return  sellingRate;
    }

    public boolean updateData(String item_name, double selling_rate){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, item_name);
        contentValues.put(COL_3, selling_rate);
        db.update(TABLE_NAME, contentValues, "ITEM_NAME = ?", new String[] {item_name});
        return true;
    }

    public Integer deleteData(String item_name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ITEM_NAME = ?", new String[] {item_name});
    }
}
