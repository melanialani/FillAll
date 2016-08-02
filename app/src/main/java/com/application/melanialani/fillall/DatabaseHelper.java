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
    private static final String TAG = "DBHelper";

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
    private static final String KEY_DATA_STAGESUNLOCKED = "stagesUnlocked";
    private static final String KEY_DATA_CHARACTERSUNLOCKED = "charactersUnlocked";
    private static final String KEY_DATA_CHOSENCHARACTER = "chosenCharacter";

    // array column names for each table
    private static final String[] COLUMNS_TABLE_DATA = {
            KEY_DATA_COINS, KEY_DATA_STAGESUNLOCKED, KEY_DATA_CHARACTERSUNLOCKED,
            KEY_DATA_CHOSENCHARACTER};
    //endregion

    //region CREATE TABLE STATEMENTS
    // table dbversion create statement
    private static final String CREATE_TABLE_DBVERSION = "CREATE TABLE " + TABLE_DBVERSION + "( "
            + KEY_DBVERSION_VERSION + " INTEGER PRIMARY KEY)";

    // table kalender create statement
    private static final String CREATE_TABLE_DATA = "CREATE TABLE " + TABLE_DATA + "("
            //+ KEY_DATA_ID + " INTEGER PRIMARY KEY AUNTOINCREMENT, "
            + KEY_DATA_COINS + " INTEGER NOT NULL, "
            + KEY_DATA_STAGESUNLOCKED + " TEXT NOT NULL, "
            + KEY_DATA_CHARACTERSUNLOCKED + " TEXT NOT NULL, "
            + KEY_DATA_CHOSENCHARACTER + " INTEGER NOT NULL "
            + ")";
    //endregion

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //region ON CREATE - ON UPGRADE
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_DATA);
        db.execSQL(CREATE_TABLE_DBVERSION);

        db.execSQL("INSERT INTO " + TABLE_DBVERSION + " VALUES(1)");
        db.execSQL("INSERT INTO " + TABLE_DATA + " VALUES(0, '1', '1', 'PUR')");
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
    //endregion

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

    //region DATA
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

    public String getStagesUnlocked(){
        String stagesUnlocked;

        // build the query
        String query = "SELECT " + KEY_DATA_STAGESUNLOCKED + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // set result into variables
        stagesUnlocked = cursor.getString(0);

        // log
        Log.d(TAG + " STAGES", stagesUnlocked);

        // return result
        return stagesUnlocked;
    }

    public String getCharactersUnlocked(){
        String charactersUnlocked;

        // build the query
        String query = "SELECT " + KEY_DATA_CHARACTERSUNLOCKED + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // set result into variables
        charactersUnlocked = cursor.getString(0);

        // log
        Log.d(TAG + " CHARA", charactersUnlocked);

        // return result
        return charactersUnlocked;
    }

    public String getChosenCharacter(){
        String chosenCharacter;

        // build the query
        String query = "SELECT " + KEY_DATA_CHOSENCHARACTER + " FROM " + TABLE_DATA;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // if we got results, get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // set result into variables
        chosenCharacter = cursor.getString(0);

        // log
        Log.d(TAG + " CH_CHARA", chosenCharacter);

        // return result
        return chosenCharacter;
    }

    public void setCoins(int coins){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_COINS + " = " + coins;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setStagesUnlocked(String stagesUnlocked){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_STAGESUNLOCKED + " = " + stagesUnlocked;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setCharactersUnlocked(String charactersUnlocked){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                    + " SET " + KEY_DATA_CHARACTERSUNLOCKED + " = " + charactersUnlocked;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }

    public void setChosenCharacter(String chosenCharacter){
        // build the query
        String query = "UPDATE " + TABLE_DATA
                + " SET " + KEY_DATA_CHOSENCHARACTER + " = " + chosenCharacter;

        // get reference to writable database
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery(query, null);
    }
    //endregion
}
