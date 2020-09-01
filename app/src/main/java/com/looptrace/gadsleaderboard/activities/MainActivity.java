package com.looptrace.gadsleaderboard.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.looptrace.gadsleaderboard.R;
import com.looptrace.gadsleaderboard.customAdapters.ViewPagerAdapter;
import com.looptrace.gadsleaderboard.fragments.HourFragment;
import com.looptrace.gadsleaderboard.fragments.SkillFragment;

public class MainActivity extends AppCompatActivity {

    private Button mSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);
        mSubmitBtn = toolbar.findViewById(R.id.btn_submit);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SubmitActivity.class));
            }
        });

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        HourFragment hourFragment = new HourFragment();
        SkillFragment skillFragment = new SkillFragment();

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorBG));

        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        mViewPagerAdapter.addFragment(hourFragment, "Learning Leaders");
        mViewPagerAdapter.addFragment(skillFragment, "Skill IQ Leaders");
        viewPager.setAdapter(mViewPagerAdapter);

    }
}
