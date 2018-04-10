package com.omvp.app.ui.samples.list.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.omvp.app.base.ui.BaseDiffUtilsCallback;
import com.omvp.app.model.SampleModel;

import org.parceler.Parcels;

import java.util.List;

/**
    Extend with BaseDiffUtilsCallback and override these 2 methods

    1. areContentsTheSame() : return true if content of two item is same
    2. getChangePayload() : return the difference between two items
 */

public class SampleDiffUtilsCallback extends BaseDiffUtilsCallback {

    public SampleDiffUtilsCallback(List<SampleModel> newList, List<SampleModel> oldList) {
        super(newList, oldList);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Object newItem = newList.get(newItemPosition);
        Object oldItem = oldList.get(oldItemPosition);
        if (newItem instanceof SampleModel && oldItem instanceof SampleModel) {
            return newItem.equals(oldItem);
        }
        return false;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        SampleModel newSampleModel = (SampleModel) newList.get(newItemPosition);
        SampleModel oldSampleModel = (SampleModel) oldList.get(oldItemPosition);

        Bundle diff = new Bundle();
        if (!newSampleModel.equals(oldSampleModel)) {
            diff.putParcelable("sample", Parcels.wrap(newSampleModel));
        }
        if (diff.size() == 0) {
            return null;
        }
        return diff;
    }
}
