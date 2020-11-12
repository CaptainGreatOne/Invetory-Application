package com.example.menutest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_ITEMS = "ITEMS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_DEPARTMENT = "DEPARTMENT";
    public static final String COLUMN_PRICE = "PRICE";
    public static final String COLUMN_QUANTITY = "QUANTITY";
    public static final String COLUMN_BARCODE = "BARCODE";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "SenProj.db", null, 1);
    }

    //called during first time access to DB. Includes code to create new DB.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_ITEMS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL UNIQUE, " + COLUMN_NAME + " TEXT NOT NULL UNIQUE, " + COLUMN_DEPARTMENT + " TEXT NOT NULL, " + COLUMN_PRICE + " REAL NOT NULL, " + COLUMN_QUANTITY + " INT, " + COLUMN_BARCODE + " TEXT NOT NULL UNIQUE)";

        db.execSQL(createTable);
    }

    //called whenever DB version changes. Provides forward/backward compatibility.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addOne(ItemModel itemModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, itemModel.getId());
        cv.put(COLUMN_NAME, itemModel.getName());
        cv.put(COLUMN_DEPARTMENT, itemModel.getDepartment());
        cv.put(COLUMN_PRICE, itemModel.getPrice());
        cv.put(COLUMN_QUANTITY, itemModel.getQuantity());
        cv.put(COLUMN_BARCODE, itemModel.getBarcode());

        db.insert(TABLE_ITEMS, null, cv);

        db.close();
    }

    public boolean deleteOne(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();

        long remove = db.delete(TABLE_ITEMS, "ID=?", new String[]{id.toString()});

        db.close();
        if (remove == 1)
            return true;
        else
            return false;
    }

    public boolean updateQuantity(Integer id, Integer quant){
        boolean success = false;
        SQLiteDatabase db = this.getWritableDatabase();
       // String strSQL = "UPDATE " + TABLE_ITEMS + " " + SET + " " + COLUMN_QUANTITY + "=" + quant + " " + WHERE + " " + COLUMN_ID + "=" + id;
        String test = "UPDATE " + TABLE_ITEMS + " SET " + COLUMN_QUANTITY + "=" + quant +  " WHERE " + COLUMN_ID + "=" + id;
        try {
            db.execSQL(test);
            success = true;
        }
        catch (Exception e){

        }
        //Integer result = db.update(TABLE_ITEMS, quant, "QUANTITY=?", new int[](quant));
        db.close();
        return success;
    }

    public List<ItemModel> searchItems(String x){
        List<ItemModel> returnList = new ArrayList<>();

        //get data from database     SELECT * FROM docs WHERE docs MATCH 'linux'

        SQLiteDatabase db = this.getReadableDatabase();
        String queryString;
        Cursor cursor;
        //if x is an int, it searches both id and name columns, else it just searches name column.
        //SELECT * FROM ITEMS WHERE (ID LIKE "2") or (NAME LIKE "%E%")
        try{
            int y = Integer.parseInt(x);
            x = x.toUpperCase();
            queryString = "SELECT * FROM " + TABLE_ITEMS + " WHERE (" + COLUMN_ID + " LIKE '%" + y + "%') or (" + COLUMN_NAME + " LIKE '%" + x + "%')";
            cursor = db.rawQuery(queryString, null);

        }
        catch (Exception e){
            x = x.toUpperCase();
            queryString = "SELECT * FROM " + TABLE_ITEMS + " WHERE (" + COLUMN_NAME + " LIKE '%" + x + "%')";
            cursor = db.rawQuery(queryString, null);
        }

        //checks if cursor returns any info from the db
        if (cursor.moveToFirst()){
            //loops through cursor (quesry result set) amd create new customer obj and return list
            do {
                int itemID = cursor.getInt(0);
                String itemName = cursor.getString(1);
                String itemDept = cursor.getString(2);
                Double itemPrice = cursor.getDouble(3);
                int itemQuant = cursor.getInt(4);
                String itemBarcode = cursor.getString(5);

                ItemModel itemModel = new ItemModel(itemID, itemName, itemDept, itemPrice, itemQuant, itemBarcode);
                returnList.add(itemModel);

            } while (cursor.moveToNext());
        }
        else{
            //failure. Don't add to list.
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<ItemModel> getAllItems(){
        List<ItemModel> returnList = new ArrayList<>();

        //get data from database
        String queryString = "SELECT * FROM " + TABLE_ITEMS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        //checks if cursor returns any info from the db
        if (cursor.moveToFirst()){
            //loops through cursor (quesry result set) amd create new customer obj and return list
            do {
                int itemID = cursor.getInt(0);
                String itemName = cursor.getString(1);
                String itemDept = cursor.getString(2);
                Double itemPrice = cursor.getDouble(3);
                int itemQuant = cursor.getInt(4);
                String itemBarcode = cursor.getString(5);

                ItemModel itemModel = new ItemModel(itemID, itemName, itemDept, itemPrice, itemQuant, itemBarcode);
                returnList.add(itemModel);

            } while (cursor.moveToNext());
        }
        else{
            //failure. Don't add to list.
        }

        cursor.close();
        db.close();
        return returnList;
    }
}
