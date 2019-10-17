package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.BookSortType;
import com.example.myapplication.R;
import com.example.myapplication.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_bookmark_sort);

        spinner.setOnItemSelectedListener(this);

        List<BookSortType> categories = new ArrayList<>();
        categories.add(BookSortType.RATING);
        categories.add(BookSortType.PRICE);
        categories.add(BookSortType.NAME);

        ArrayAdapter<BookSortType> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        
        PreferenceUtils.setHistory(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
