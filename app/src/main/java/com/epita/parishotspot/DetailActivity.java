package com.epita.parishotspot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {


    private TextView textViewItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Init values
        textViewItemName = (TextView) findViewById(R.id.textViewItemName);

        //Getting intent
        Intent intent = getIntent();

        //Displaying values
        textViewItemName.setText(intent.getStringExtra("key_item_name"));
    }
}
