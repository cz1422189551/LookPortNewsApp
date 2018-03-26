package com.cz.lookportnews.util;

import com.cz.lookportnews.ui.IAdapterNotificaitionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14221 on 2018/3/5.
 */

public class Constant {


    public static final String BASEURL= "http://192.168.31.199:8080/";

    public static int TEXT_SIZE = 1; //

    //小号的字体
    public static int TextDefalut =12;

    public static List<IAdapterNotificaitionListener> adapterNotificaitionListenerList =new ArrayList<>();

    public static void addAdapter(IAdapterNotificaitionListener adapter){
        if(adapter!=null){
            adapterNotificaitionListenerList.add(adapter);
        }
    }
    public static void reMoveAdapter(IAdapterNotificaitionListener adapter){
        adapterNotificaitionListenerList.remove(adapter);
    }
    public static void reAllAdapter(){
        adapterNotificaitionListenerList.removeAll(adapterNotificaitionListenerList);
    }

    public static void notification(){
        for (IAdapterNotificaitionListener iAdapterNotificaitionListener : adapterNotificaitionListenerList) {
            if(iAdapterNotificaitionListener!=null){
                iAdapterNotificaitionListener.notification();;
            }
        }
    }

}
