package com.plusend.zhihudaily.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.plusend.zhihudaily.model.bean.LatestNews;
import com.plusend.zhihudaily.ui.fragment.BannerFragment;

import java.util.List;

/**
 * Created by plusend on 16/6/1.
 */
public class BannerAdapter extends FragmentPagerAdapter {

    private List<LatestNews.TopStories> mTopStories;

    public BannerAdapter(FragmentManager fm, List<LatestNews.TopStories> topStories) {
        super(fm);
        mTopStories = topStories;
    }

    @Override
    public Fragment getItem(int position) {
        return BannerFragment.newInstance(mTopStories.get(position));
    }

    @Override
    public int getCount() {
        return mTopStories.size();
    }

}
