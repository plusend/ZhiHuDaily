package com.plusend.zhihudaily.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plusend.zhihudaily.R;
import com.plusend.zhihudaily.model.bean.Story;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by plusend on 16/6/3.
 */
public class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Story> mStoriesList = new ArrayList<>();

    private static final int DATE = 1;
    private static final int NORMAL = 2;

    public StoryAdapter(Context context, List<Story> StoriesList) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mStoriesList = StoriesList;
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL) {
            View view = mLayoutInflater.inflate(R.layout.rv_main_item, parent, false);
            return new NormalHolder(view);
        } else {
            View view = mLayoutInflater.inflate(R.layout.rv_main_item_date, parent, false);
            return new DateHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalHolder) {
            ((NormalHolder) holder).title.setText(mStoriesList.get(position).getTitle());
            Picasso.with(mContext).load(mStoriesList.get(position).getImages().get(0)).into(((NormalHolder) holder).image);
        } else if (holder instanceof DateHolder) {
            ((DateHolder) holder).date.setText(String.valueOf(mStoriesList.get(position).getType()));
            ((DateHolder) holder).title.setText(mStoriesList.get(position).getTitle());
            Picasso.with(mContext).load(mStoriesList.get(position).getImages().get(0)).into(((DateHolder) holder).image);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mStoriesList.get(position).getType() != 0)
            return DATE;
        else return NORMAL;
    }



    @Override
    public int getItemCount() {
        return mStoriesList.size();
    }

    public class DateHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.image)
        ImageView image;

        public DateHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

    }

    public class NormalHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.image)
        ImageView image;

        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
    }
}
