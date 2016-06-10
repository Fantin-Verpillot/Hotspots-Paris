package com.epita.parishotspot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Init values
        TextView textViewDistrict = (TextView) findViewById(R.id.district);
        TextView textViewAddress = (TextView) findViewById(R.id.address);
        TextView textViewName = (TextView) findViewById(R.id.name);
        TextView textViewCode = (TextView) findViewById(R.id.code);

        //Getting intent
        Intent intent = getIntent();

        //Displaying values
        textViewDistrict.setText(intent.getStringExtra("key_item_name"));
        textViewAddress.setText(intent.getStringExtra("key_item_name"));
        textViewName.setText(intent.getStringExtra("key_item_name"));
        textViewCode.setText(intent.getStringExtra("key_item_name"));
    }
}
