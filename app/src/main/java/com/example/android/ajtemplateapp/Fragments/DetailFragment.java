package com.example.android.ajtemplateapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.ajtemplateapp.R;
import com.example.android.ajtemplateapp.model.DataModel;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    View rootView;
    /*String id;
    Bundle bundle;*/
    DataModel dataModel;
    TextView title, company, location, description;
    ImageView logo;
    Context context;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        context = getActivity();
        logo = (ImageView) rootView.findViewById(R.id.logo_image_view);
        title = (TextView) rootView.findViewById(R.id.title_text_view);
        company = (TextView) rootView.findViewById(R.id.company_text_view);
        location = (TextView) rootView.findViewById(R.id.location_text_view);
        description = (TextView) rootView.findViewById(R.id.description_text_view);
        setViews();

       /* if you were sending/getting arguments...*/
        /*bundle = getArguments();
        id = bundle.getString("id");*/

        return rootView;
    }

    public void updateDataModel(DataModel job){
       //alternate method would be to send/get bundle arguments
        dataModel = job;
    }

    public void setViews(){
        if (dataModel != null) {
            if (dataModel.getCompany_logo() != null) {
                Picasso.with(context)
                        .load(dataModel.getCompany_logo())
                        .resize(500, 500)
                        .onlyScaleDown()
                        .centerInside()
                        .into(logo);
            }
            title.setText(dataModel.getTitle());
            company.setText(dataModel.getCompany());
            location.setText(dataModel.getLocation());
            description.setText(Html.fromHtml(dataModel.getDescription()));
        }
    }

}
