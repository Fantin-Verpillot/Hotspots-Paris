package com.epita.parishotspot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.epita.parishotspot.Models.Record;

public class DetailActivity extends AppCompatActivity {

    Record recordData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Init view elements
        TextView textViewName = (TextView) findViewById(R.id.name);
        TextView textViewCode = (TextView) findViewById(R.id.code);
        TextView textViewAddress = (TextView) findViewById(R.id.address);
        TextView textViewDistrict = (TextView) findViewById(R.id.district);

        //Getting record
        recordData = (Record) getIntent().getSerializableExtra("record");

        //Displaying values
        textViewName.setText(recordData.getFields().getNomSite());
        textViewCode.setText(recordData.getFields().getCodeSite());
        textViewAddress.setText(recordData.getFields().getAdresse());
        textViewDistrict.setText(recordData.getFields().getArrondissement());

        //Manage Google Maps Button
        Button button = (Button) findViewById(R.id.map_button);
        button.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Double latitude = recordData.getGeometry().getCoordinates().get(1);
                Double longitude = recordData.getGeometry().getCoordinates().get(0);
                String label = recordData.getFields().getNomSite();
                String url = "http://maps.google.com/maps?q="+ latitude  +"," + longitude +"("+ label + ")&iwloc=A&hl=es";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
    }
}
