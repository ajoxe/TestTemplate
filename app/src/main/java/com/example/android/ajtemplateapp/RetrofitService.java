package com.example.android.ajtemplateapp;

import com.example.android.ajtemplateapp.model.DataModel;
import com.example.android.ajtemplateapp.model.DataModelResponse;


import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by amirahoxendine on 1/23/18.
 */

public interface RetrofitService {
    @GET("/positions.json")
    Call<DataModel[]> getJobs(@QueryMap Map<String, String> searchOptions);
}
