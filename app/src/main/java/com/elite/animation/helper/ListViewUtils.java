package com.elite.animation.helper;

import android.view.View;
import android.widget.ListView;

/**
 * Elite Group
 * Created by wjc133 on 2015/10/9.
 */
public class ListViewUtils {
    public static View getViewByPosition(int pos, ListView listView) {
        int firstListItemPosition = listView.getFirstVisiblePosition();
        int lastListItemPosition = listView.getLastVisiblePosition();
        if(pos >= firstListItemPosition && pos <= lastListItemPosition) {
            int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        } else {
            return null;
        }
    }
}
