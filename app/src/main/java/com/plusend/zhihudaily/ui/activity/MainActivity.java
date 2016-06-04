package com.plusend.zhihudaily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.plusend.zhihudaily.R;
import com.plusend.zhihudaily.model.bean.LatestNews;
import com.plusend.zhihudaily.mvp.presenter.LatestNewsPresenter;
import com.plusend.zhihudaily.mvp.presenter.Presenter;
import com.plusend.zhihudaily.mvp.view.LatestNewsView;
import com.plusend.zhihudaily.ui.adapter.BannerAdapter;
import com.plusend.zhihudaily.ui.adapter.StoryAdapter;
import com.plusend.zhihudaily.ui.view.RecyclerViewHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LatestNewsView {

    private static final String TAG = "MainActivity";

    @BindView(R.id.rv_main)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_main)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_main_header)
    RecyclerViewHeader mRecyclerViewHeader;
    @BindView(R.id.vp_main)
    ViewPager mViewPager;

    private BannerAdapter mBannerAdapter;
    private List<LatestNews.TopStories> mTopStories = new ArrayList<>();

    private Presenter mPresenter;

    private List<LatestNews.Stories> mStoriesList = new ArrayList<>();
    StoryAdapter mStoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mPresenter = new LatestNewsPresenter(this);

        mStoryAdapter = new StoryAdapter(this, mStoriesList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mStoryAdapter);
        mStoryAdapter.setOnItemClickListener(new StoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ArrayList<Integer> mIds = new ArrayList<>();
                int size = mStoriesList.size();
                for(int i = 0; i < size; ++i){
                    LatestNews.Stories story = mStoriesList.get(i);
                    mIds.add(story.getId());
                }
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putIntegerArrayListExtra("id", mIds);
                intent.putExtra("current", position);
                startActivity(intent);
            }
        });

        mRecyclerViewHeader.attachTo(mRecyclerView);

        mBannerAdapter = new BannerAdapter(getSupportFragmentManager(), mTopStories);
        mViewPager.setAdapter(mBannerAdapter);
        setSwipeRefreshLayoutListener();
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                mPresenter.start();
            }
        });
    }

    private void setSwipeRefreshLayoutListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.stop();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showNews(LatestNews news) {
        mStoriesList.addAll(news.getStories());
        mStoryAdapter.notifyDataSetChanged();

        mTopStories.clear();
        mTopStories.addAll(news.getTopStories());
        mBannerAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
