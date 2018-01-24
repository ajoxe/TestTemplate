package com.example.android.ajtemplateapp;

import com.example.android.ajtemplateapp.model.DataModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by amirahoxendine on 1/23/18.
 */

public class DataUtility {

    public DataUtility(){
    }

    public HashMap<String, DataModel> buildMap(List<DataModel> modelList){
        HashMap<String, DataModel> dataModelMap = new HashMap<>();
        for (DataModel model: modelList){
            dataModelMap.put(model.getId(), model);
        }
        return dataModelMap;
    }

    public DataModel getModelFromMap(HashMap<String, DataModel> modelMap, String id){
        return modelMap.get(id);
    }
}
