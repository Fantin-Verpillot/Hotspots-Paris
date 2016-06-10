package com.epita.parishotspot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.epita.parishotspot.Model.Hotspot;

import java.util.List;

//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;

    String[] values = new String[]{"Donut"
            , "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich",
            "Jelly Bean", "Kit Kat", "Lollipop", "Marshmallow"};

//    private List<Hotspot> hotspots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

//        getHotspots();

        showList();

        //Setting onItemClickListener to listview
        listView.setOnItemClickListener(this);
    }

//    private void getHotspots() {
//        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(HotspotService.ENDPOINT)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        HotspotService service = retrofit.create(HotspotService.class);
//
//        Call<List<Hotspot>> call = service.getHotspots();
//
//        call.enqueue(new Callback<List<Hotspot>>() {
//            @Override
//            public void onResponse(Call<List<Hotspot>> call, Response<List<Hotspot>> response) {
//                loading.dismiss();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Hotspot>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), String.format("Cannot retrieve hotspot list"), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void showList() {

//        String[] values = new String[hotspots.size()];


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

        Intent intent = new Intent(this, DetailActivity.class);

        //Getting the requested book from the list
//        Book book = books.get(position);

        //Adding info about hotspot
        intent.putExtra("key_item_name", values[pos]);

        //Starting another activity to show book details
        startActivity(intent);

    }
}
