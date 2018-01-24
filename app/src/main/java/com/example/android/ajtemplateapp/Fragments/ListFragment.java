package com.example.android.ajtemplateapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.ajtemplateapp.DataUtility;
import com.example.android.ajtemplateapp.R;
import com.example.android.ajtemplateapp.RetrofitService;
import com.example.android.ajtemplateapp.controllers.ModelAdapter;
import com.example.android.ajtemplateapp.model.DataModel;
import com.example.android.ajtemplateapp.model.DataModelResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    View rootView;
    /*retrofit fields*/
    String baseURL = "https://jobs.github.com";
    Map<String, String> options = new HashMap<>(); //queries from search screens
    EditText keywordEditText, locationEditText;
    Button search;
    String keywordQuery;
    String locationQuery;

    RecyclerView recyclerView;
    ModelAdapter modelAdapter;
    LinearLayoutManager linearLayoutManager;
    List<DataModel> modelList = new ArrayList<>();
    Context context;
    View.OnClickListener rvClick;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_list, container, false);
        keywordEditText = (EditText) rootView.findViewById(R.id.keyword_edit_text);
        locationEditText = (EditText) rootView.findViewById(R.id.location_edit_text);
        search = (Button) rootView.findViewById(R.id.search_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            keywordQuery = keywordEditText.getText().toString();
            locationQuery = locationEditText.getText().toString();
            fetchResults();
            }
        });

        setRecyclerView();
        return rootView;
    }

    public void setRecyclerView(){
        recyclerView = (RecyclerView) rootView.findViewById(R.id.model_recycler_view);
        context = getActivity();
        setRvOnClick();
        modelAdapter = new ModelAdapter(modelList, rvClick, context);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(modelAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    public void setRvOnClick(){
        rvClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = v.getTag().toString();
                DetailFragment detailFragment = new DetailFragment();
                DataUtility utility = new DataUtility();
                DataModel dm = utility.getModelFromMap(utility.buildMap(modelList), id);
                detailFragment.updateDataModel(dm);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_container, detailFragment);
                fragmentTransaction.addToBackStack("next");
                fragmentTransaction.commit();


                /*Bundle bundle = new Bundle();
                bundle.putString("id" , id);
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(bundle);*/
            }
        };
    }

    public void fetchResults(){
        options.put("location", locationQuery);
        options.put("description", keywordQuery);
        connectWithRetrofit(options);
    }

    /*TODO add retrofit call*/
    public void connectWithRetrofit(Map<String, String> queries){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<DataModel[]> call = service.getJobs(queries);
        call.enqueue(new retrofit2.Callback<DataModel[]>() {
            @Override
            public void onResponse(Call<DataModel[]> call, retrofit2.Response<DataModel[]> response) {
                DataModelResponse models = new DataModelResponse();
                       models.setResponse(response.body());

                modelList.addAll(Arrays.asList(models.getResponse()));
                Log.d("retrofit: ", "onResponse: " + modelList.size());
                modelAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<DataModel[]> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
