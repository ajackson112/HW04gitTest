package com.example.cschw04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class KidDataSource {


    private SQLiteDatabase database ;
    private KidDBHelper dbHelper ;
    public KidDataSource(Context context) {
        dbHelper = new KidDBHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper .getWritableDatabase();
    }
    public void close() {
        dbHelper .close();
    }

    public boolean insertKid(Kid k) {
        boolean didSucceed = false ;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put( "contactname" , k.getContactName());
            initialValues.put( "streetaddress" , k.getStreetAddress());
            initialValues.put( "city" , k.getCity());
            initialValues.put( "state" , k.getState());
            initialValues.put( "zipcode" , k.getZipCode());
            initialValues.put( "phonenumber" , k.getPhoneNumber());
            initialValues.put( "cellnumber" , k.getCellNumber());
            initialValues.put( "email" , k.getEMail());
            initialValues.put( "birthday" , String. valueOf (k.getBirthday().toMillis( false )));
            didSucceed = database .insert( "contact" , null , initialValues) > 0;
        }
        catch (Exception e) {
        }
        return didSucceed;
    }
    public boolean updateKid (Kid k) {
        boolean didSucceed = false ;
        try {
            Long rowId = Long. valueOf (k.getContactID());
            ContentValues updateValues = new ContentValues();
            updateValues.put( "contactname" , k.getContactName());
            updateValues.put( "streetaddress" , k.getStreetAddress());
            updateValues.put( "city" , k.getCity());
            updateValues.put( "state" , k.getState());
            updateValues.put( "zipcode" , k.getZipCode());
            updateValues.put( "phonenumber" , k.getPhoneNumber());
            updateValues.put( "cellnumber" , k.getCellNumber());
            updateValues.put( "email" , k.getEMail());
            updateValues.put( "birthday" , String. valueOf (k.getBirthday().toMillis( false )));
            didSucceed = database .update( "contact" , updateValues, "_id=" + rowId, null ) > 0;
        }
        catch (Exception e) {

        }
        return didSucceed;
    }

    public int getLastKidId() {
        int lastId = -1;
        try {
            String query = "Select MAX(_id) from contact" ; //1
            Cursor cursor = database.rawQuery(query, null ); //2
            cursor.moveToFirst(); //3
            lastId = cursor.getInt(0); //4
            cursor.close(); //5
        }
        catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }

    public ArrayList<String> getKidName() {
        ArrayList<String> kidNames = new ArrayList<String>(); //1
        try {
            String query = "Select kidname from kid" ; //2
            Cursor cursor = database .rawQuery(query, null );
            cursor.moveToFirst(); //3
            while (!cursor.isAfterLast()) {
                kidNames.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            kidNames = new ArrayList<String>(); //4
        }
        return kidNames;
    }
}
