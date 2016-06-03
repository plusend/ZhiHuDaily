package com.plusend.zhihudaily.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plusend.zhihudaily.R;
import com.plusend.zhihudaily.model.bean.LatestNews;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by plusend on 16/6/1.
 */
public class BannerFragment extends Fragment {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;

    private LatestNews.TopStories mTopStories;

    public static BannerFragment newInstance(LatestNews.TopStories topStory) {
        BannerFragment fragment = new BannerFragment();
        Bundle args = new Bundle();
        args.putParcelable("story", topStory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTopStories = getArguments().getParcelable("story");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_banner, null);
        ButterKnife.bind(this, view);

        Picasso.with(getActivity()).load(mTopStories.getImage()).into(image);
        title.setText(mTopStories.getTitle());
        return view;
    }
}
