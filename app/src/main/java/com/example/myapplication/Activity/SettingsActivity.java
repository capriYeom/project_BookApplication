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

    private Spinner mSpinner;
    private List<BookSortType> mCategory = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mSpinner = findViewById(R.id.spinner_bookmark_sort);

        mSpinner.setOnItemSelectedListener(this);

        mCategory.add(BookSortType.RATING);
        mCategory.add(BookSortType.PRICE);
        mCategory.add(BookSortType.NAME);
        mCategory.add(BookSortType.ISBN);

        ArrayAdapter<BookSortType> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mCategory);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(dataAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        BookSortType type;

        if (PreferenceUtils.getSortType() != null) {
            type = PreferenceUtils.getSortType();
            for (int i = 0; i < mCategory.size(); i++) {
                if (mCategory.get(i).equals(type)) {
                    mSpinner.setSelection(i);
                    return;
                }
            }
        }
        mSpinner.setSelection(0);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        PreferenceUtils.setSortType(BookSortType.valueOf(item));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
