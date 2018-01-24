package com.example.android.ajtemplateapp.model;

import org.json.JSONArray;

import java.lang.reflect.Array;

/**
 * Created by amirahoxendine on 1/24/18.
 */

public class DataModelResponse extends JSONArray{
    DataModel[] response;

    public void setResponse(DataModel[] response) {
        this.response = response;
    }

    public DataModel[] getResponse() {
        return response;
    }
}
