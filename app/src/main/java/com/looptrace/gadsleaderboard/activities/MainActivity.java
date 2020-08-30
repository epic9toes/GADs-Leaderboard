package com.looptrace.gadsleaderboard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.looptrace.gadsleaderboard.R;
import com.looptrace.gadsleaderboard.customAdapters.ViewPagerAdapter;
import com.looptrace.gadsleaderboard.fragments.LearningFragment;
import com.looptrace.gadsleaderboard.fragments.SkillFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private LearningFragment mLearningFragment;
    private SkillFragment mSkillFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);

        mLearningFragment = new LearningFragment();
        mSkillFragment = new SkillFragment();

        mTabLayout.setupWithViewPager(mViewPager);

        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        mViewPagerAdapter.addFragment(mLearningFragment, "Learning Leaders");
        mViewPagerAdapter.addFragment(mSkillFragment, "Skill IQ Leaders");
        mViewPager.setAdapter(mViewPagerAdapter);

    }
}
