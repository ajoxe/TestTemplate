package com.example.android.ajtemplateapp.controllers;

import android.content.Context;
import android.graphics.ColorSpace;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.ajtemplateapp.R;
import com.example.android.ajtemplateapp.model.DataModel;
import com.example.android.ajtemplateapp.views.ModelViewHolder;

import java.util.List;

/**
 * Created by amirahoxendine on 1/23/18.
 */

public class ModelAdapter extends RecyclerView.Adapter<ModelViewHolder> {
    List<DataModel> modelList;
    View.OnClickListener rvClick;
    Context context;

    public ModelAdapter(List<DataModel> modelList, View.OnClickListener rvClick, Context context) {
        this.modelList = modelList;
        this.rvClick = rvClick;
        this.context = context;
    }

    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View childView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_item_view, parent, false);

        return new ModelViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(ModelViewHolder holder, int position) {
        DataModel model = modelList.get(position);
        holder.onBind(model, context);
        holder.itemView.setTag(model.getId());
        holder.itemView.setOnClickListener(rvClick);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
