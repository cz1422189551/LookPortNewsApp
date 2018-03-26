package com.cz.lookportnews.dragrecycler;

import android.util.Log;

import com.cz.lookportnews.dragrecycler.entity.MyItemEntity;
import com.cz.lookportnews.dragrecycler.entity.MyTitleEntity;
import com.cz.lookportnews.dragrecycler.entity.OtherTitleEntity;

import java.util.ArrayList;
import java.util.List;

import jackson.com.commonrecyclerlib.CommonEntity;

/**
 * Created by jackson on 2017/4/8.
 * 管理频道数据的类
 */

public class DateControl {

    private static DateControl instance;
    private MyTitleEntity myTitleEntity;
    private ArrayList<CommonEntity> allEntities;

    public static DateControl getInstance() {
        if (instance == null) {
            synchronized (DateControl.class) {
                if (instance == null) {
                    instance = new DateControl();
                }
            }
        }
        return instance;
    }


    private List<MyItemEntity> getMyItemEntity() {
        ArrayList mItemEntities = new ArrayList<>();


        mItemEntities.add(new MyItemEntity("体育" , MyItemEntity.TYPE_MY));
        mItemEntities.add(new MyItemEntity("国内" , MyItemEntity.TYPE_MY));
        mItemEntities.add(new MyItemEntity("推荐" , MyItemEntity.TYPE_MY));
        mItemEntities.add(new MyItemEntity("社会" , MyItemEntity.TYPE_MY));
        mItemEntities.add(new MyItemEntity("娱乐" , MyItemEntity.TYPE_MY));
        mItemEntities.add(new MyItemEntity("汽车" , MyItemEntity.TYPE_MY));
//        for (int i = 0; i < 15; i++) {
//            mItemEntities.add(new MyItemEntity("频道" + i, MyItemEntity.TYPE_MY));
//        }
        return mItemEntities;
    }


    private List<MyItemEntity> getOtherItemEntity() {
        ArrayList mOtherEntities = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            mOtherEntities.add(new MyItemEntity("其他频道" + i, MyItemEntity.TYPE_OTHER));
//        }
        mOtherEntities.add(new MyItemEntity("NBA", MyItemEntity.TYPE_OTHER));
        mOtherEntities.add(new MyItemEntity("财经", MyItemEntity.TYPE_OTHER));
        mOtherEntities.add(new MyItemEntity("国际", MyItemEntity.TYPE_OTHER));
        mOtherEntities.add(new MyItemEntity("手机", MyItemEntity.TYPE_OTHER));
        mOtherEntities.add(new MyItemEntity("房产", MyItemEntity.TYPE_OTHER));
        mOtherEntities.add(new MyItemEntity("女人", MyItemEntity.TYPE_OTHER));
        mOtherEntities.add(new MyItemEntity("潮流", MyItemEntity.TYPE_OTHER));

        return mOtherEntities;
    }


    public MyTitleEntity getMyTitleEntity() {
        if (myTitleEntity == null) {
            myTitleEntity = new MyTitleEntity();
        }
        return myTitleEntity;
    }


    public List<CommonEntity> getAll() {
        if (allEntities == null) {
            allEntities = new ArrayList();
            allEntities.add(getMyTitleEntity());
            allEntities.addAll(getMyItemEntity());
            allEntities.add(new OtherTitleEntity());
            allEntities.addAll(getOtherItemEntity());
        }

        return allEntities;
    }

    public void moveMy2Other(MyItemEntity target) {
        getAll().remove(target);
        target.setType(MyItemEntity.TYPE_OTHER);
        getAll().add(instance.getMySize() + 2, target);
        Log.e("DateControl", " MyItemSize:" + instance.getMySize());
    }

    public void moveOhter2My(MyItemEntity target) {
        getAll().remove(target);
        target.setType(MyItemEntity.TYPE_MY);
        getAll().add(instance.getMySize() + 1, target);
        Log.e("DateControl", " OtherItemSize:" + instance.getOtherSize());

    }

    public int getMySize() {
        return getItemEntitiesSize(MyItemEntity.TYPE_MY);
    }

    public int getOtherSize() {
        return getItemEntitiesSize(MyItemEntity.TYPE_OTHER);
    }


    private int getItemEntitiesSize(int type) {
        final List<CommonEntity> all = getAll();
        int size = 0;
        for (CommonEntity en : all) {
            if (en instanceof MyItemEntity) {
                if (((MyItemEntity) en).getType() == type) {
                    size++;
                }
            }
        }
        return size;
    }


    public ArrayList<MyItemEntity> getItemEntities(int type) {
        ArrayList<MyItemEntity> arr = new ArrayList<>();
        for (CommonEntity en : getAll()) {
            if (en instanceof MyItemEntity) {
                if (((MyItemEntity) en).getType() == type) {
                    arr.add((MyItemEntity) en);
                }
            }
        }
        return arr;
    }

    public void clear() {
        allEntities = null;
        myTitleEntity = null;
    }


}
