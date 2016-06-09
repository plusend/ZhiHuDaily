package com.plusend.zhihudaily.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setTitle("");
        }

        mIds = getIntent().getIntegerArrayListExtra("id");
        int current = getIntent().getIntExtra("current", 0);

        DetailAdapter detailAdapter = new DetailAdapter(getSupportFragmentManager(), mIds);
        mViewPager.setAdapter(detailAdapter);
        mViewPager.setCurrentItem(current);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "news from zhihu daily");
                intent.setType("text/plain");
                startActivity(intent);
                break;
            case R.id.action_collect:
                item.setIcon(R.drawable.collected);
                break;
            case R.id.action_praise:
                item.setIcon(R.drawable.praised);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
