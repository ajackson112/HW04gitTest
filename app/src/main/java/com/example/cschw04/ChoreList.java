package com.example.cschw04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChoreList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_list);
    }

    public void addChore (View view) {
        Intent intent= new Intent(ChoreList.this,ChoreAdd.class);
        startActivity(intent);
    }
}
