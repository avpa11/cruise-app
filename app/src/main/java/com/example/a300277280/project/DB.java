package com.example.a300277280.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 12;
    public static final String DATABASE_NAME = "Volna.db";

    private static final String SQL_CREATE_ENTRIES_Customers =
        "CREATE TABLE " + Customers.TABLE_NAME + " ( " + Customers._ID + " INTEGER PRIMARY KEY," + Customers.COLUMN_NAME_NAME + " TEXT, " + Customers.COLUMN_NAME_EMAIL + " TEXT, " + Customers.COLUMN_NAME_PRICE + " DOUBLE" + " ) ";

    private static final String SQL_CREATE_ENTRIES_Activities =
        "CREATE TABLE " + Activities.TABLE_NAME + " ( " + Activities._ID + " INTEGER PRIMARY KEY," + Activities.COLUMN_NAME_CustomerName + " TEXT, " + Activities.COLUMN_NAME_ActivityName + " TEXT, " + Activities.COLUMN_NAME_DATE + " TEXT" + " ) ";

    private static final String SQL_CREATE_ENTRIES_Ports =
        "CREATE TABLE " + PortsTable.TABLE_NAME + " ( " + PortsTable._ID + " INTEGER PRIMARY KEY," + PortsTable.COLUMN_NAME_CustomerName + " TEXT, " + PortsTable.COLUMN_NAME_NameOfPort + " TEXT, " + PortsTable.COLUMN_NAME_Type + " TEXT, " + PortsTable.COLUMN_NAME_Qty + " INT, " + PortsTable.COLUMN_NAME_Price + " DOUBLE" + " ) ";

    private static final String SQL_CREATE_ENTRIES_Room =
        "CREATE TABLE " + Room.TABLE_NAME + " ( " + Room._ID + " INTEGER PRIMARY KEY," + Room.COLUMN_NAME_CustomerName + " TEXT, " + Room.COLUMN_NAME_RoomNumber + " INT, " + Room.COLUMN_NAME_RoomType + " TEXT, " + Room.COLUMN_NAME_Cleaning + " TEXT, " + Room.COLUMN_NAME_Breackfast + " TEXT " + " ) ";


    private static final String SQL_DELETE_ENTRIES_Customers = "DROP TABLE IF EXISTS " + Customers.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_Activities = "DROP TABLE IF EXISTS " + Activities.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_Ports = "DROP TABLE IF EXISTS " + PortsTable.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_Room = "DROP TABLE IF EXISTS " + Room.TABLE_NAME;

    public DB(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DB", "Constructor");
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_ENTRIES_Customers);
        db.execSQL(SQL_CREATE_ENTRIES_Activities);
        db.execSQL(SQL_CREATE_ENTRIES_Ports);
        db.execSQL(SQL_CREATE_ENTRIES_Room);
        Log.d("DB", "OnCreate");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_ENTRIES_Customers);
        db.execSQL(SQL_DELETE_ENTRIES_Activities);
        db.execSQL(SQL_DELETE_ENTRIES_Ports);
        db.execSQL(SQL_DELETE_ENTRIES_Room);
        Log.d("DB", "onUpgrade");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.d("DB", "onDowngrade");
        onUpgrade(db, oldVersion, newVersion);
    }
}
