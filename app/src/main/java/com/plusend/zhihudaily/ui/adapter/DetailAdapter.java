package com.plusend.zhihudaily.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.plusend.zhihudaily.ui.fragment.DetailFragment;

import java.util.List;

/**
 * Created by plusend on 16/6/1.
 */
public class DetailAdapter extends FragmentStatePagerAdapter {

    private List<Integer> mIdList;

    public DetailAdapter(FragmentManager fm, List<Integer> ids) {
        super(fm);
        mIdList = ids;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailFragment.newInstance(mIdList.get(position));
    }

    @Override
    public int getCount() {
        return mIdList.size();
    }
}
