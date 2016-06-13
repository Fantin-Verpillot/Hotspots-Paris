package com.epita.parishotspot;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epita.parishotspot.Models.Record;

import java.util.List;

public class DetailFragment extends Fragment {

    private Record recordData;
    private String shareDescription;
    private Context context;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static DetailFragment newInstance(int sectionNumber) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        //Init view elements
        TextView textViewName = (TextView) rootView.findViewById(R.id.name);
        TextView textViewAddress = (TextView) rootView.findViewById(R.id.address);
        TextView textViewDistrict = (TextView) rootView.findViewById(R.id.district);

        //Getting record
        if (SwapActivity.getListType().equals("main")) {
            recordData = MainActivity.getRecord(getArguments().getInt(ARG_SECTION_NUMBER) - 1);
        } else {
            recordData = SearchableActivity.getRecord(getArguments().getInt(ARG_SECTION_NUMBER) - 1);
        }

        //Displaying values
        textViewName.setText(recordData.getFields().getNomSite());
        textViewAddress.setText(recordData.getFields().getAdresse());
        textViewDistrict.setText(recordData.getFields().getArrondissement());

        shareDescription =
                "Hotspot : " + recordData.getFields().getNomSite() + " - "
                + recordData.getFields().getAdresse() + " "
                + recordData.getFields().getArrondissement();

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

                //test
                PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                if (activities.size() > 0) {
                    startActivity(intent);
                } else {
                    Toast.makeText(v.getContext(), String.format("L'application Google Maps est introuvable"), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    public String getShareDescription() {
        return shareDescription;
    }
}