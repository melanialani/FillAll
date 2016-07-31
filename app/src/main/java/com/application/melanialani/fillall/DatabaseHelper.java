package com.application.melanialani.fillall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    //region TAG - DB NAME - DB VERSION
    // logcat tag
    private static final String TAG = "DatabaseHelper";

    // database version
    private static final int DATABASE_VERSION = 1;

    // database name
    private static final String DATABASE_NAME = "FillAll";
    //endregion

    //region TABLE NAMES - COLUMN NAMES
    // table names
    private static final String TABLE_DATA = "data";
    private static final String TABLE_DBVERSION = "dbversion";

    // column names of table dbversion
    private static final String KEY_DBVERSION_VERSION = "version";

    // column names of table data
    //private static final String KEY_DATA_ID = "id";
    private static final String KEY_DATA_COINS = "coins";
    private static final String KEY_DATA_HASUNLOCKEDSTAGE2 = "hasUnlockedStage2";
    private static final String KEY_DATA_HASUNLOCKEDSTAGE3 = "hasUnlockedStage3";
    private static final String KEY_DATA_HASUNLOCKEDCHARACTER1 = "hasUnlockedCharacter1";
    private static final String KEY_DATA_HASUNLOCKEDCHARACTER2 = "hasUnlockedCharacter2";
    private static final String KEY_DATA_HASUNLOCKEDCHARACTER3 = "hasUnlockedCharacter3";
    private static final String KEY_DATA_HASUNLOCKEDCHARACTER4 = "hasUnlockedCharacter4";
    private static final String KEY_DATA_HASUNLOCKEDCHARACTER5 = "hasUnlockedCharacter5";
    private static final String KEY_DATA_HASUNLOCKEDCHARACTER6 = "hasUnlockedCharacter6";
    private static final String KEY_DATA_HASUNLOCKEDCHARACTER7 = "hasUnlockedCharacter7";
    private static final String KEY_DATA_HASUNLOCKEDCHARACTER8 = "hasUnlockedCharacter8";
    private static final String KEY_DATA_HASUNLOCKEDCHARACTER9 = "hasUnlockedCharacter9";
    private static final String KEY_DATA_HASUNLOCKEDCHARACTER10 = "hasUnlockedCharacter10";

    // array column names for each table
    private static final String[] COLUMNS_TABLE_DATA = {
            //KEY_DATA_ID,
            KEY_DATA_COINS, KEY_DATA_HASUNLOCKEDSTAGE2, KEY_DATA_HASUNLOCKEDSTAGE3,
            KEY_DATA_HASUNLOCKEDCHARACTER1, KEY_DATA_HASUNLOCKEDCHARACTER2, KEY_DATA_HASUNLOCKEDCHARACTER3,
            KEY_DATA_HASUNLOCKEDCHARACTER4, KEY_DATA_HASUNLOCKEDCHARACTER5, KEY_DATA_HASUNLOCKEDCHARACTER6,
            KEY_DATA_HASUNLOCKEDCHARACTER7, KEY_DATA_HASUNLOCKEDCHARACTER8, KEY_DATA_HASUNLOCKEDCHARACTER9,
            KEY_DATA_HASUNLOCKEDCHARACTER10};
    //endregion

    //region CREATE TABLE STATEMENTS
    // table dbversion create statement
    private static final String CREATE_TABLE_DBVERSION = "CREATE TABLE " + TABLE_DBVERSION + "( "
            + KEY_DBVERSION_VERSION + " INTEGER PRIMARY KEY)";

    // table kalender create statement
    private static final String CREATE_TABLE_DATA = "CREATE TABLE " + TABLE_DATA + "("
            //+ KEY_DATA_ID + " INTEGER PRIMARY KEY AUNTOINCREMENT, "
            + KEY_DATA_COINS + " INTEGER NOT NULL, "
            + KEY_DATA_HASUNLOCKEDSTAGE2 + " INTEGER NOT NULL, "
            + KEY_DATA_HASUNLOCKEDSTAGE3 + " INTEGER NOT NULL, "
            + KEY_DATA_HASUNLOCKEDCHARACTER1 + " INTEGER NOT NULL, "
            + KEY_DATA_HASUNLOCKEDCHARACTER2 + " INTEGER NOT NULL, "
            + KEY_DATA_HASUNLOCKEDCHARACTER3 + " INTEGER NOT NULL, "
            + KEY_DATA_HASUNLOCKEDCHARACTER4 + " INTEGER NOT NULL, "
            + KEY_DATA_HASUNLOCKEDCHARACTER5 + " INTEGER NOT NULL, "
            + KEY_DATA_HASUNLOCKEDCHARACTER6 + " INTEGER NOT NULL, "
            + KEY_DATA_HASUNLOCKEDCHARACTER7 + " INTEGER NOT NULL, "
            + KEY_DATA_HASUNLOCKEDCHARACTER8 + " INTEGER NOT NULL, "
            + KEY_DATA_HASUNLOCKEDCHARACTER9 + " INTEGER NOT NULL, "
            + KEY_DATA_HASUNLOCKEDCHARACTER10 + " INTEGER NOT NULL "
            + ")";
    //endregion

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //region RECREATE TABLES - ON CREATE - ON UPGRADE
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_DATA);
        db.execSQL(CREATE_TABLE_DBVERSION);

        db.execSQL("INSERT INTO " + TABLE_DBVERSION + " VALUES(1)");
        db.execSQL("INSERT INTO " + TABLE_DATA + " VALUES(0, 0, 0," +
                "0, 0, 0, 0, 0, 0, 0, 0, 0, 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables

        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);

        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DBVERSION);

        // recreate the tables
        this.onCreate(db);
    }

    //region DBVersion
    public int getDBVersion(){
        int dbVersion;

        // build the query
        String query = "SELECT MAX(" + KEY_DBVERSION_VERSION + ") FROM " + TABLE_DBVERSION;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // build pengumuman object
        dbVersion = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getDBV", String.valueOf(dbVersion));

        // return dbVersion
        return dbVersion;
    }

    public void updateDBVersion(){
        Log.d(TAG + " DBVer", "version + 1");

        int version = this.getDBVersion() + 1;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_DBVERSION_VERSION, version);

        // insert
        db.insert(TABLE_DBVERSION, // table name
                null, // nullColumnHack
                values); // key/value -> keys = column names / values = column values

        // close
        db.close();
    }
    //endregion

    //region Data-GETTERS
    public int getCoins(){
        int coins;

        // build the query
        String query = "SELECT " + KEY_DATA_COINS + " FROM " + TABLE_DATA;

        /*String query = "SELECT " + KEY_DATA_2_COINS
                     + " FROM " + TABLE_DATA
                     + " WHERE " + KEY_DATA_1_ID + " = (SELECT MAX(" + KEY_DATA_1_ID + ") FROM " + TABLE_DATA + ")"
                     + " LIMIT 1";*/

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // set result into variables
        coins = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getCoins", String.valueOf(coins));

        // return coins
        return coins;
    }

    public int getHasUnlockedStage2(){
        int hasUnlockedStage2;

        // build the query
        String query = "SELECT " + KEY_DATA_HASUNLOCKEDSTAGE2 + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // set result into variables
        hasUnlockedStage2 = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getStg2", String.valueOf(hasUnlockedStage2));

        // return result
        return hasUnlockedStage2;
    }

    public int getHasUnlockedStage3(){
        int hasUnlockedStage3;

        // build the query
        String query = "SELECT " + KEY_DATA_HASUNLOCKEDSTAGE3 + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // set result into variables
        hasUnlockedStage3 = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getStg3", String.valueOf(hasUnlockedStage3));

        // return result
        return hasUnlockedStage3;
    }

    public int getHasUnlockedCharacter1(){
        int hasUnlockedCharacter1;

        // build the query
        String query = "SELECT " + KEY_DATA_HASUNLOCKEDCHARACTER1 + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // set result into variables
        hasUnlockedCharacter1 = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getChar1", String.valueOf(hasUnlockedCharacter1));

        // return result
        return hasUnlockedCharacter1;
    }

    public int getHasUnlockedCharacter2(){
        int hasUnlockedCharacter2;

        // build the query
        String query = "SELECT " + KEY_DATA_HASUNLOCKEDCHARACTER2 + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // set result into variables
        hasUnlockedCharacter2 = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getChar2", String.valueOf(hasUnlockedCharacter2));

        // return result
        return hasUnlockedCharacter2;
    }

    public int getHasUnlockedCharacter3(){
        int hasUnlockedCharacter3;

        // build the query
        String query = "SELECT " + KEY_DATA_HASUNLOCKEDCHARACTER3 + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // set result into variables
        hasUnlockedCharacter3 = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getChar3", String.valueOf(hasUnlockedCharacter3));

        // return result
        return hasUnlockedCharacter3;
    }

    public int getHasUnlockedCharacter4(){
        int hasUnlockedCharacter4;

        // build the query
        String query = "SELECT " + KEY_DATA_HASUNLOCKEDCHARACTER4 + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // set result into variables
        hasUnlockedCharacter4 = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getChar4", String.valueOf(hasUnlockedCharacter4));

        // return result
        return hasUnlockedCharacter4;
    }

    public int getHasUnlockedCharacter5(){
        int hasUnlockedCharacter5;

        // build the query
        String query = "SELECT " + KEY_DATA_HASUNLOCKEDCHARACTER5 + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // set result into variables
        hasUnlockedCharacter5 = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getChar5", String.valueOf(hasUnlockedCharacter5));

        // return result
        return hasUnlockedCharacter5;
    }

    public int getHasUnlockedCharacter6(){
        int hasUnlockedCharacter6;

        // build the query
        String query = "SELECT " + KEY_DATA_HASUNLOCKEDCHARACTER6 + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // set result into variables
        hasUnlockedCharacter6 = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getChar6", String.valueOf(hasUnlockedCharacter6));

        // return result
        return hasUnlockedCharacter6;
    }

    public int getHasUnlockedCharacter7(){
        int hasUnlockedCharacter7;

        // build the query
        String query = "SELECT " + KEY_DATA_HASUNLOCKEDCHARACTER7 + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // set result into variables
        hasUnlockedCharacter7 = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getChar7", String.valueOf(hasUnlockedCharacter7));

        // return result
        return hasUnlockedCharacter7;
    }

    public int getHasUnlockedCharacter8(){
        int hasUnlockedCharacter8;

        // build the query
        String query = "SELECT " + KEY_DATA_HASUNLOCKEDCHARACTER8 + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // set result into variables
        hasUnlockedCharacter8 = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getChar8", String.valueOf(hasUnlockedCharacter8));

        // return result
        return hasUnlockedCharacter8;
    }

    public int getHasUnlockedCharacter9(){
        int hasUnlockedCharacter9;

        // build the query
        String query = "SELECT " + KEY_DATA_HASUNLOCKEDCHARACTER9 + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // set result into variables
        hasUnlockedCharacter9 = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getChar9", String.valueOf(hasUnlockedCharacter9));

        // return result
        return hasUnlockedCharacter9;
    }

    public int getHasUnlockedCharacter10(){
        int hasUnlockedCharacter10;

        // build the query
        String query = "SELECT " + KEY_DATA_HASUNLOCKEDCHARACTER10 + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null){
            cursor.moveToFirst();
        }

        // set result into variables
        hasUnlockedCharacter10 = Integer.parseInt(cursor.getString(0));

        // log
        Log.d(TAG + " getChr10", String.valueOf(hasUnlockedCharacter10));

        // return result
        return hasUnlockedCharacter10;
    }
    //endregion

    //region DATA-SETTERS
    public void setCoins(int coins){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_COINS + " = " + coins;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setHasUnlockedStage2(int hasUnlockedStage2){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_HASUNLOCKEDSTAGE2 + " = " + hasUnlockedStage2;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setHasUnlockedStage3(int hasUnlockedStage3){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_HASUNLOCKEDSTAGE3 + " = " + hasUnlockedStage3;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setHasUnlockedCharacter1(int hasUnlockedCharacter1){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_HASUNLOCKEDCHARACTER1 + " = " + hasUnlockedCharacter1;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setHasUnlockedCharacter2(int hasUnlockedCharacter2){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_HASUNLOCKEDCHARACTER2 + " = " + hasUnlockedCharacter2;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setHasUnlockedCharacter3(int hasUnlockedCharacter3){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_HASUNLOCKEDCHARACTER3 + " = " + hasUnlockedCharacter3;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setHasUnlockedCharacter4(int hasUnlockedCharacter4){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_HASUNLOCKEDCHARACTER4 + " = " + hasUnlockedCharacter4;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setHasUnlockedCharacter5(int hasUnlockedCharacter5){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_HASUNLOCKEDCHARACTER5 + " = " + hasUnlockedCharacter5;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setHasUnlockedCharacter6(int hasUnlockedCharacter6){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_HASUNLOCKEDCHARACTER6 + " = " + hasUnlockedCharacter6;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setHasUnlockedCharacter7(int hasUnlockedCharacter7){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_HASUNLOCKEDCHARACTER7 + " = " + hasUnlockedCharacter7;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setHasUnlockedCharacter8(int hasUnlockedCharacter8){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_HASUNLOCKEDCHARACTER8 + " = " + hasUnlockedCharacter8;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setHasUnlockedCharacter9(int hasUnlockedCharacter9){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_HASUNLOCKEDCHARACTER9 + " = " + hasUnlockedCharacter9;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setHasUnlockedCharacter10(int hasUnlockedCharacter10){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_HASUNLOCKEDCHARACTER10 + " = " + hasUnlockedCharacter10;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }
    //endregion
}
