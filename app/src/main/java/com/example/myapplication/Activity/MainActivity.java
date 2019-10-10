package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import com.example.myapplication.Adapter.BookPagerAdapter;
import com.example.myapplication.Fragment.NewBookFragment;
import com.example.myapplication.Fragment.SearchBookFragment;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewPager();

    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        BookPagerAdapter pagerAdapter = new BookPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.addFragment(new NewBookFragment());
        pagerAdapter.addFragment(new SearchBookFragment());
        mViewPager.setAdapter(pagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("NEW"));
        mTabLayout.addTab(mTabLayout.newTab().setText("SEARCH"));
        mTabLayout.setTabTextColors(Color.LTGRAY, Color.BLUE);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        mViewPager.setCurrentItem(tab.getPosition());
                        break;
                    case 1:
                        mViewPager.setCurrentItem(tab.getPosition());
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
