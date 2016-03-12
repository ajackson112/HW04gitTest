package com.example.cschw04;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class KidDBHelper extends SQLiteOpenHelper { //1
    private static final String DATABASE_NAME = "mykids.db" ; //2
    private static final int DATABASE_VERSION = 1; //3
    // Database creation sql statement
    private static final String CREATE_TABLE_CONTACT = "create table contact (_id integer primary key autoincrement, " //4
            + "contactname text not null"
            + "cellnumber text, "
            + "birthday text);" ;
    public KidDBHelper(Context context) { //5
        super (context, DATABASE_NAME , null , DATABASE_VERSION );
    }
    @Override
    public void onCreate(SQLiteDatabase database) { //6
        database.execSQL( CREATE_TABLE_CONTACT );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {Log.w(KidDBHelper. class .getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data" );
        db.execSQL( "DROP TABLE IF EXISTS contact" );
        onCreate(db);
    }
}

