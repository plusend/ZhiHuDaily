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
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.plusend.zhihudaily.R;
import com.plusend.zhihudaily.common.Constants;
import com.plusend.zhihudaily.model.bean.BeforeNews;
import com.plusend.zhihudaily.model.bean.LatestNews;
import com.plusend.zhihudaily.model.bean.Story;
import com.plusend.zhihudaily.model.bean.TopStories;
import com.plusend.zhihudaily.mvp.presenter.BeforeNewsPresenter;
import com.plusend.zhihudaily.mvp.presenter.LatestNewsPresenter;
import com.plusend.zhihudaily.mvp.presenter.Presenter;
import com.plusend.zhihudaily.mvp.view.LatestNewsView;
import com.plusend.zhihudaily.ui.adapter.BannerAdapter;
import com.plusend.zhihudaily.ui.adapter.StoryAdapter;
import com.plusend.zhihudaily.ui.view.RecyclerViewHeader;
import com.plusend.zhihudaily.utils.PreferenceUtil;
import com.plusend.zhihudaily.utils.ThemeUtil;

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
    @BindView(R.id.indicator)
    LinearLayout indicator;

    private BannerAdapter mBannerAdapter;
    private List<TopStories> mTopStories = new ArrayList<>();

    private Presenter mPresenter;
    private BeforeNewsPresenter mBeforePresenter;
    private String mBeforeDate;
    private boolean isLoading = false;

    private int preDotPosition = 0;

    private List<Story> mStoriesList = new ArrayList<>();
    StoryAdapter mStoryAdapter;

    private int night_mode = AppCompatDelegate.MODE_NIGHT_NO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        night_mode = PreferenceUtil.getInt(MainActivity.this, Constants.NIGHT_MODE,
                AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        mPresenter = new LatestNewsPresenter(this);
        mBeforePresenter = new BeforeNewsPresenter(this);

        mStoryAdapter = new StoryAdapter(this, mStoriesList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mStoryAdapter);
        mStoryAdapter.setOnItemClickListener(new StoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ArrayList<Integer> mIds = new ArrayList<>();
                int size = mStoriesList.size();
                for (int i = 0; i < size; ++i) {
                    Story story = mStoriesList.get(i);
                    mIds.add(story.getId());
                }
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putIntegerArrayListExtra("id", mIds);
                intent.putExtra("current", position);
                startActivity(intent);
            }
        });

        mRecyclerViewHeader.attachTo(mRecyclerView);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                if ((visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE &&
                        (lastVisibleItemPosition) >= totalItemCount - 1)) {
                    if (!isLoading) {
                        isLoading = true;
                        mBeforePresenter.getBeforeNews(mBeforeDate);
                    }
                }

                int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                if (mStoriesList.get(firstVisibleItemPosition).getType() != 0) {
                    Log.d(TAG, "date:" + mStoriesList.get(firstVisibleItemPosition).getType());
//                    if (toolbar != null)
//                        toolbar.setTitle(mStoriesList.get(firstVisibleItemPosition).getType());
                }
            }
        });

        mBannerAdapter = new BannerAdapter(getSupportFragmentManager(), mTopStories);
        mViewPager.setAdapter(mBannerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 取余后的索引，得到新的page的索引
                int newPosition = position % mTopStories.size();
                // 把上一个点设置为被选中
                indicator.getChildAt(preDotPosition).setEnabled(false);
                // 根据索引设置那个点被选中
                indicator.getChildAt(newPosition).setEnabled(true);
                // 新索引赋值给上一个索引的位置
                preDotPosition = newPosition;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final GestureDetector tapGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                ArrayList<Integer> mIds = new ArrayList<>();
                int size = mTopStories.size();
                for (int i = 0; i < size; ++i) {
                    TopStories story = mTopStories.get(i);
                    mIds.add(story.getId());
                }
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putIntegerArrayListExtra("id", mIds);
                intent.putExtra("current", mViewPager.getCurrentItem());
                startActivity(intent);
                return false;
            }
        });

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                tapGestureDetector.onTouchEvent(event);
                return false;
            }
        });
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
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
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
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
            return true;
        } else if (id == R.id.action_night) {
            night_mode = night_mode == AppCompatDelegate.MODE_NIGHT_NO ? AppCompatDelegate.MODE_NIGHT_YES
                    : AppCompatDelegate.MODE_NIGHT_NO;
            PreferenceUtil.putInt(MainActivity.this, Constants.NIGHT_MODE, night_mode);
            ThemeUtil.setTheme(MainActivity.this, night_mode);
            recreate();
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
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
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
        if (news == null)
            return;
        mBeforeDate = news.getDate();
        mStoriesList.clear();
        mStoriesList.addAll(news.getStories());
        mStoryAdapter.notifyDataSetChanged();

        mTopStories.clear();
        mTopStories.addAll(news.getTopStories());

        View dot;
        LinearLayout.LayoutParams params;
        indicator.removeAllViews();
        for (int i = 0; i < news.getTopStories().size(); ++i) {
            // 每循环一次添加一个点到线行布局中
            dot = new View(this);
            dot.setBackgroundResource(R.drawable.dot_bg_selector);
            params = new LinearLayout.LayoutParams(20, 20);
            params.leftMargin = 10;
            dot.setEnabled(false);
            dot.setLayoutParams(params);
            indicator.addView(dot); // 向线性布局中添加"点"
        }
        indicator.getChildAt(preDotPosition).setEnabled(true);

        mBannerAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addBefore(BeforeNews news) {
        mBeforeDate = news.getDate();
        mStoriesList.addAll(news.getStories());
        mStoryAdapter.notifyDataSetChanged();
        isLoading = false;
    }

}
