package com.elite.animation.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.elite.animation.R;
import com.elite.animation.adapter.NewsAdapter;
import com.elite.animation.bean.News;
import com.elite.animation.helper.DataLoader;
import com.elite.animation.helper.ListViewUtils;

import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Elite Group
 * Created by wjc133 on 2015/10/9.
 */
public class SharedActivity extends Activity {
    private PtrFrameLayout mPtrLayout;
    private ListView mListView;
    private NewsAdapter mAdapter;

    private View mPreImg;
    private List<News> mNewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra);
        mPtrLayout = (PtrFrameLayout) findViewById(R.id.ptr_list_frame);
        mListView = (ListView) findViewById(R.id.ptr_listview);
        mPtrLayout.setPullToRefresh(false);
        //自定义头部
        CustomHeaderView headerView = new CustomHeaderView(this);
        headerView.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
//        headerView.setPadding(0, 15, 0, 10);
        mPtrLayout.setHeaderView(headerView);
        mPtrLayout.addPtrUIHandler(headerView);
        mPtrLayout.setKeepHeaderWhenRefresh(true);
        getData();
        mPtrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, content, header);
//                return true;
            }

            //为了演示效果，延迟2s获取数据
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                mPtrLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 2000);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SharedActivity.this, SharedDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", mNewsList.get(position).getName());
                bundle.putString("desc", mNewsList.get(position).getDescription());
                bundle.putString("listener", mNewsList.get(position).getLearner());
                bundle.putString("picUrl", mNewsList.get(position).getPicSmall());
                bundle.putInt("position", position);
                //// TODO: 2015/10/9 记得要添加图片

                View v = ListViewUtils.getViewByPosition(position, mListView);
                if (v != null) {
                    mPreImg = v.findViewById(R.id.image_content);
                }
                intent.putExtra("news", bundle);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SharedActivity.this, mPreImg, "robot" + position).toBundle());
            }
        });
    }

    private void getData() {
        DataLoader.refreshData(new DataLoader.Callback() {

            @Override
            public void onResult(List<News> newsList) {
                if (mNewsList != newsList) {
                    mNewsList = newsList;
                }
                if (mAdapter == null) {
                    mAdapter = new NewsAdapter(SharedActivity.this, newsList);
                    mListView.setAdapter(mAdapter);
                } else {
                    mAdapter.notifyDataSetChanged();
                }
                if (mPtrLayout.isRefreshing()) {
                    mPtrLayout.refreshComplete();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(SharedActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
