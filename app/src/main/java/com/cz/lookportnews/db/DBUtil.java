package com.cz.lookportnews.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cz.lookportnews.entity.News;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2017-6-30.
 */

public class DBUtil {

    private static final String TAG = "DBUtil";

    public static final String DB_NAME = "cz_news";

    public static final int VERSION = 1;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHSS");
    private static DBUtil dbUtil;

    private SQLiteDatabase db;

    private DBUtil(Context mContext) {
        ReaderOpenHelp dbHelper = new ReaderOpenHelp(mContext, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 单例模式构造dbUtil对象
     *
     * @param context
     * @return
     */
    public synchronized static DBUtil getInstance(Context context) {
        if (dbUtil == null) {
            dbUtil = new DBUtil(context);
        }
        return dbUtil;
    }


    /**
     * 更新阅读记录
     */
    public void update(String time, Long id) {

        db.execSQL("update readed set time=? where b_id=?", new String[]{time, String.valueOf(id)});

    }


    /**
     * 取消收藏
     *
     * @param bId Id
     */
    public void delete(int bId) {
        String query = "delete news  where id=" + bId + "";
        db.execSQL(query);
    }

    //添加阅读记录
    public void saveReaded(News news) {
        if (news != null) {
            News readedById = findReadedById(String.valueOf(news.getId()));
            if (readedById == null || readedById.getId() == null) {
                saveReaed(news);
            } else {
                Date date = new Date();
                update(sdf.format(date), readedById.getId());
            }

        }
    }

    private void saveReaed(News news) {
        ContentValues values = new ContentValues();
        values.put("id", news.getId());
        values.put("title", news.getTitle());
        Date date = new Date();
        values.put("time", sdf.format(date));
        values.put("orign", news.getOrigin());
        db.insert("readed", null, values);
    }

    /**
     * 清空所有阅读记录
     */
    public void deleteReaded() {
        String query = "delete readed ";
        db.execSQL(query);
    }

    //分页查询阅读记录
    public News findReaded(String start, String end) {
        Cursor cursor = db.rawQuery("select * from readed  order by time desc  Limit ? Offset ? ", new String[]{end, start});
        News news = new News();
        if (cursor.moveToFirst()) {
            do {
                news.setId(cursor.getLong(cursor.getColumnIndex("id")));
                news.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                news.setOrigin(cursor.getString(cursor.getColumnIndex("orign")));
            } while (cursor.moveToNext());
            return news;
        }
        return null;
    }

    //收藏新闻
    public void saveNews(News news) {
        if (news != null) {
            ContentValues values = new ContentValues();
            values.put("id", news.getId());
            values.put("title", news.getTitle());
            values.put("content", news.getContent());
            values.put("editor", news.getEditor());
            values.put("orign", news.getOrigin());
            values.put("time", news.getTime());
            values.put("pageSource", news.getPageSource());
            values.put("imgUrl", news.getImgUrl());
            db.insert("news", null, values);
        }
    }

    //分页查询
    public News findNews(String start, String end) {
        Cursor cursor = db.rawQuery("select * from news order by id desc  Limit ? Offset ? ", new String[]{end, start});
        News news = new News();
        if (cursor.moveToFirst()) {
            do {
                news.setId(cursor.getLong(cursor.getColumnIndex("id")));
                news.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                news.setContent(cursor.getString(cursor.getColumnIndex("content")));
                news.setEditor(cursor.getString(cursor.getColumnIndex("editor")));
                news.setPageSource(cursor.getString(cursor.getColumnIndex("pageSource")));
                news.setOrigin(cursor.getString(cursor.getColumnIndex("orign")));
                news.setTime(cursor.getString(cursor.getColumnIndex("time")));
                news.setImgUrl(cursor.getString(cursor.getColumnIndex("imgUrl")));
            } while (cursor.moveToNext());
            return news;
        }
        return null;
    }

    /**
     * 根据id查询阅读记录
     *
     * @param newsId
     * @return
     */
    public News findReadedById(String newsId) {
        Cursor cursor = db.rawQuery("select * from readed where id = ?", new String[]{newsId});
        News news = new News();
        if (cursor.moveToFirst()) {
            do {
                news.setId(cursor.getLong(cursor.getColumnIndex("id")));
            } while (cursor.moveToNext());
            return news;
        }
        return null;
    }


    public News findNewsById(String newsId) {
        Cursor cursor = db.rawQuery("select * from news where id = ?", new String[]{newsId});
        News news = new News();
        if (cursor.moveToFirst()) {
            do {
                news.setId(cursor.getLong(cursor.getColumnIndex("id")));
                news.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                news.setContent(cursor.getString(cursor.getColumnIndex("content")));
                news.setEditor(cursor.getString(cursor.getColumnIndex("editor")));
                news.setPageSource(cursor.getString(cursor.getColumnIndex("pageSource")));
                news.setOrigin(cursor.getString(cursor.getColumnIndex("orign")));
                news.setTime(cursor.getString(cursor.getColumnIndex("time")));
                news.setImgUrl(cursor.getString(cursor.getColumnIndex("imgUrl")));
            } while (cursor.moveToNext());
            return news;
        }
        return null;
    }

    public News findAll() {
        Cursor cursor = db.rawQuery("select * from news ", new String[]{"1"});
        News news = new News();
        if (cursor.moveToFirst()) {
            do {
                news.setId(cursor.getLong(cursor.getColumnIndex("id")));
                news.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                news.setContent(cursor.getString(cursor.getColumnIndex("content")));
                news.setEditor(cursor.getString(cursor.getColumnIndex("editor")));
                news.setPageSource(cursor.getString(cursor.getColumnIndex("pageSource")));
                news.setOrigin(cursor.getString(cursor.getColumnIndex("orign")));
                news.setTime(cursor.getString(cursor.getColumnIndex("time")));
                news.setImgUrl(cursor.getString(cursor.getColumnIndex("imgUrl")));
            } while (cursor.moveToNext());
            return news;
        }
        return null;
    }


}
