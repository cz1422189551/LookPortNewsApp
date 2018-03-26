package com.cz.lookportnews.util;

import com.cz.lookportnews.entity.Channel;
import com.cz.lookportnews.entity.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import ikidou.reflect.TypeBuilder;

/**
 * Created by 14221 on 2018/3/26.
 */

public class JsonUtil {

    public static Gson gson = new Gson();




    public static <T> Result<T> fromJsonObject(String json, Class<T> clazz) {
        Type type = TypeBuilder.newInstance(Result.class).addTypeParam(clazz).build();
        return gson.fromJson(json, type);
    }


//
//    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
//        T[] arr = new Gson().fromJson(s, clazz);
//        return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
//    }

    public static String ObjectToJson(Object o) {
        return gson.toJson(o);
    }

}
