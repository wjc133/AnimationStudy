package com.elite.animation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.elite.animation.R;

/**
 * Elite Group
 * Created by wjc133 on 2015/9/16.
 */
public class SelectorAdapter extends BaseAdapter {

    private String[] mTitles;
    private String[] mDescriptions;
    private LayoutInflater mInflater;

    public SelectorAdapter(Context context, String[] titles, String[] descriptions) {
        mTitles = titles;
        mDescriptions = descriptions;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public Object getItem(int position) {
        return mTitles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_main, null);
            holder = new ViewHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.item_title);
            holder.descriptionView = (TextView) convertView.findViewById(R.id.item_description);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleView.setText(mTitles[position]);
        holder.descriptionView.setText(mDescriptions[position]);
        return convertView;
    }

    private class ViewHolder {
        TextView titleView;
        TextView descriptionView;
    }
}
