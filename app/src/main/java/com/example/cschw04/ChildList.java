package com.example.cschw04;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChildList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_list);
    }


    public void uninstallApp (View view) {
        Uri packageURI = Uri.parse("package:com.example.cschw04");
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        startActivity(uninstallIntent);
    }

    public void openList (View view) {
        Intent intent= new Intent(ChildList.this,KidList.class);
        startActivity(intent);
    }

    public void openChores (View view) {
        Intent intent= new Intent(ChildList.this,ChoreList.class);
        startActivity(intent);
    }
}
