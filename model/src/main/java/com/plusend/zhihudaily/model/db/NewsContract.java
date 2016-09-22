package com.plusend.zhihudaily.model.db;

import android.provider.BaseColumns;

/**
 * Created by plusend on 16/7/5.
 */
public final class NewsContract {


    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public NewsContract() {
    }

    public static abstract class LatestNews implements BaseColumns{
        public static final String TABLE_NAME = "latest";
        public static final String COLUMN_NAME_JSON = "json";
    }

    public static abstract class BeforeNews implements BaseColumns{
        public static final String TABLE_NAME = "before";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_JSON = "json";
    }

    public static abstract class DetailNews implements BaseColumns{
        public static final String TABLE_NAME = "detail";
        public static final String COLUMN_NAME_ID = "news_id";
        public static final String COLUMN_NAME_JSON = "json";
    }
}
