package com.example.android.ajtemplateapp.views;

import android.content.Context;
import android.graphics.ColorSpace;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.ajtemplateapp.R;
import com.example.android.ajtemplateapp.model.DataModel;
import com.squareup.picasso.Picasso;

/**
 * Created by amirahoxendine on 1/23/18.
 */

public class ModelViewHolder extends RecyclerView.ViewHolder {
    public TextView jobTitle, company, location;
    public ImageView logo;


    public ModelViewHolder(View itemView) {
        super(itemView);
        jobTitle = (TextView) itemView.findViewById(R.id.item_title_text_view);
        company = (TextView) itemView.findViewById(R.id.item_company_text_view);
        location = (TextView) itemView.findViewById(R.id.item_location_text_view);
        logo = (ImageView) itemView.findViewById(R.id.item_logo_image_view);
    }

    public void onBind(DataModel model, Context context){
        jobTitle.setText(model.getTitle());
        company.setText(model.getCompany());
        location.setText(model.getLocation());
        if (model.getCompany_logo()!= null) {
            Picasso.with(context)
                    .load(model.getCompany_logo())
                    .resize(500, 500)
                    .onlyScaleDown()
                    .centerInside()
                    .into(logo);
        }
    }
}
