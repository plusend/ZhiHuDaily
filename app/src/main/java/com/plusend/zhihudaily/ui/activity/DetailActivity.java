package com.plusend.zhihudaily.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.plusend.zhihudaily.R;
import com.plusend.zhihudaily.ui.adapter.DetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    @BindView(R.id.vp_detail)
    ViewPager mViewPager;

    private List<Integer> mIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        mIds = getIntent().getIntegerArrayListExtra("id");
        int current = getIntent().getIntExtra("current", 0);

        DetailAdapter detailAdapter = new DetailAdapter(getSupportFragmentManager(), mIds);
        mViewPager.setAdapter(detailAdapter);
        mViewPager.setCurrentItem(current);
    }


}
