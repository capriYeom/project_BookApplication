package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.Adapter.BookPagerAdapter;
import com.example.myapplication.Fragment.BookmarkFragment;
import com.example.myapplication.Fragment.HistoryFragment;
import com.example.myapplication.Fragment.NewBookFragment;
import com.example.myapplication.Fragment.SearchBookFragment;
import com.example.myapplication.R;
import com.example.myapplication.utils.PreferenceUtils;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_BOOK_ISBN_NUM = "EXTRA_BOOK_DETAIL_NUM";

    TabLayout mTabLayout;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceUtils.init(getApplicationContext());
        initViewPager();

    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        BookPagerAdapter pagerAdapter = new BookPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.addFragment(new NewBookFragment());
        pagerAdapter.addFragment(new SearchBookFragment());
        pagerAdapter.addFragment(new BookmarkFragment());
        pagerAdapter.addFragment(new HistoryFragment());
        mViewPager.setAdapter(pagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("NEW"));
        mTabLayout.addTab(mTabLayout.newTab().setText("SEARCH"));
        mTabLayout.addTab(mTabLayout.newTab().setText("BOOKMARK"));
        mTabLayout.addTab(mTabLayout.newTab().setText("HISTORY"));
        mTabLayout.setTabTextColors(Color.LTGRAY, Color.BLUE);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_setting) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
