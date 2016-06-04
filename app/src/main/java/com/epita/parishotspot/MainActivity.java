package com.epita.parishotspot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private String[] values = new String[] {"Donut"
            , "Eclair" , "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich",
            "Jelly Bean", "Kit Kat", "Lollipop", "Marshmallow"];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HotspotService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HotspotService service = retrofit.create(HotspotService.class);
        Call<List<Hotspot>> listHotspot = service.loadHotspots(); */



        listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);
    }


}
