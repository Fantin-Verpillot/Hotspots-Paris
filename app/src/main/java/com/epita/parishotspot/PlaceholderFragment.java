package com.epita.parishotspot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.epita.parishotspot.Models.Record;

public class PlaceholderFragment extends Fragment {

    Record recordData;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Log.i("NUMBER", String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER)));
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER)));

        //Init view elements
        TextView textViewName = (TextView) rootView.findViewById(R.id.name);
        TextView textViewCode = (TextView) rootView.findViewById(R.id.code);
        TextView textViewAddress = (TextView) rootView.findViewById(R.id.address);
        TextView textViewDistrict = (TextView) rootView.findViewById(R.id.district);

        //Getting record
        recordData = (Record) getActivity().getIntent().getSerializableExtra("record");

        //Displaying values
        textViewName.setText(recordData.getFields().getNomSite());
        textViewCode.setText(recordData.getFields().getCodeSite());
        textViewAddress.setText(recordData.getFields().getAdresse());
        textViewDistrict.setText(recordData.getFields().getArrondissement());

        //Manage Google Maps Button
        Button button = (Button) rootView.findViewById(R.id.map_button);
        button.setOnClickListener(new View.OnClickListener()
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

        return rootView;
    }
}