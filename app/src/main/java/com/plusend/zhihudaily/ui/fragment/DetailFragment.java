package com.plusend.zhihudaily.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.plusend.zhihudaily.R;
import com.plusend.zhihudaily.model.bean.DetailNews;
import com.plusend.zhihudaily.mvp.presenter.DetailNewsPresenter;
import com.plusend.zhihudaily.mvp.view.DetailNewsView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by plusend on 16/6/3.
 */
public class DetailFragment extends Fragment implements DetailNewsView {

    private static final String TAG = "DetailFragment";

    @BindView(R.id.large_image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.from)
    TextView from;
    @BindView(R.id.web_view)
    WebView mWebView;

    private int id;
    private DetailNews mDetailNews;

    public static DetailFragment newInstance(int id) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
        DetailNewsPresenter detailNewsPresenter = new DetailNewsPresenter(this, id);
        detailNewsPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, null);
        ButterKnife.bind(this, view);
        if (mDetailNews != null) {
            updateUI();
        }
        return view;
    }

    @Override
    public void showDetail(DetailNews detailNews) {
        if (detailNews.getId() != id) {
            return;
        }
        mDetailNews = detailNews;
        updateUI();
    }

    private void updateUI() {
        Picasso.with(getContext()).load(mDetailNews.getImage()).into(image);
        title.setText(mDetailNews.getTitle());
        from.setText(mDetailNews.getImageSource());

        String cssFormat = "<link rel=\"stylesheet\" href=\"%s\" type=\"text/css\">";
        String css = String.format(cssFormat, mDetailNews.getCss().get(0));
        int currentNightMode = AppCompatDelegate.getDefaultNightMode();
        String html;
        if (currentNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            html = "<html><head>" + css + "</head><body>" + "<div class=\"night\">" + mDetailNews.getBody() + "</div>" + "</body></html>";
        } else {
            html = "<html><head>" + css + "</head><body>" + mDetailNews.getBody() + "</body></html>";
        }
        html = html.replace("<div class=\"img-place-holder\"></div>", "");
        mWebView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }
}
