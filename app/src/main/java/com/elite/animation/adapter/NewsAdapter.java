package com.elite.animation.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.elite.animation.R;
import com.elite.animation.application.MyApplication;
import com.elite.animation.bean.News;
import com.elite.animation.helper.BitmapCache;

import java.util.List;


/**
 * Elite Group
 * Created by wjc133 on 2015/9/16.
 */
public class NewsAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<News> mNewsList;

    public NewsAdapter(Context context, List<News> newsList) {
        this.mNewsList = newsList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mNewsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_ed, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.image_content);
            holder.descriptionView = (TextView) convertView.findViewById(R.id.tv_content);
            holder.titleView = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleView.setText(mNewsList.get(position).getName());
        holder.descriptionView.setText(mNewsList.get(position).getDescription());
//        holder.imageView.setImageResource(R.mipmap.ic_launcher);
        //加载网络图片
        ImageLoader imageLoader=new ImageLoader(MyApplication.getRequestQueue(), new BitmapCache());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, R.drawable.thumb2);
        imageLoader.get(mNewsList.get(position).getPicSmall(), imageListener);
        holder.imageView.setTransitionName("robot" + position);
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView descriptionView;
        TextView titleView;
    }
}
