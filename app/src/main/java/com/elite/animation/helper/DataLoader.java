package com.elite.animation.helper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.elite.animation.application.MyApplication;
import com.elite.animation.bean.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Elite Group
 * Created by wjc133 on 2015/9/16.
 */
public class DataLoader {
    private static final String BASE_URL = "http://www.imooc.com/api/teacher?type=4&num=30";

    public static void refreshData(final Callback callback) {
        String tag = "MoocGet";
        MyApplication.getRequestQueue().cancelAll(tag);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, BASE_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<News> newsList = parseData(response);
                callback.onResult(newsList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error.getMessage());
            }
        });
        request.setTag(tag);
        MyApplication.getRequestQueue().add(request);
    }

    private static List<News> parseData(JSONObject response) {
        List<News> newsList = new ArrayList<>();
        try {
            JSONArray json = response.getJSONArray("data");
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObject = json.getJSONObject(i);
                News news = new News();
                news.setName(jsonObject.getString("name"));
                news.setDescription(jsonObject.getString("description"));
                news.setPicSmall(jsonObject.getString("picSmall"));
                news.setLearner(jsonObject.getString("learner"));
                newsList.add(news);
            }
            return newsList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface Callback {
        void onResult(List<News> newsList);

        void onError(String error);
    }
}
