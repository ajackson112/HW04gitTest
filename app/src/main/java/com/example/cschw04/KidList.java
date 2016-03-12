package com.example.cschw04;

import android.content.Intent;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;


public class KidList extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_list);

    }

    public void addKid (View view) {
        Intent intent= new Intent(KidList.this,KidAdd.class);
        startActivity(intent);
    }
}