package com.omvp.app.base.ui;

import android.support.v7.util.DiffUtil;

import java.util.List;

public abstract class BaseDiffUtilsCallback<T> extends DiffUtil.Callback {

    protected List<T> newList;
    protected List<T> oldList;

    public BaseDiffUtilsCallback(List<T> newList, List<T> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    /*
        Here, we don’t have any key parameter to check if the items are same.
        So, we are always returning true which invokes areContentTheSame() for every position.
    */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }
}
