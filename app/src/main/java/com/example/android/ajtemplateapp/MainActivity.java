package com.example.android.ajtemplateapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.ajtemplateapp.Fragments.DetailFragment;
import com.example.android.ajtemplateapp.Fragments.ListFragment;

public class MainActivity extends AppCompatActivity {
    ListFragment listFragment;
    DetailFragment detailFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listFragment = new ListFragment();
        detailFragment = new DetailFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, listFragment);
        fragmentTransaction.addToBackStack("next");
        fragmentTransaction.commit();
    }
}
