package com.omvp.app.ui.samples.list.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.omvp.app.model.SampleModel;
import com.omvp.components.SampleItemView;
import com.raxdenstudios.recycler.RecyclerAdapter;

import org.parceler.Parcels;

import java.util.List;

public class SampleListAdapter extends RecyclerAdapter<SampleModel, SampleListAdapter.SampleListViewHolder> {

    private AdapterCallback mAdapterCallback;

    private ItemTouchHelper mItemTouchHelper;

    public interface AdapterCallback {
        void sampleItemSelected(int position, View sharedView);

        void sampleItemDeleteSelected(int position);
    }

    public SampleListAdapter(Context context, AdapterCallback callback) {
        super(context, -1);

        mAdapterCallback = callback;
    }

    @Override
    public SampleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        SampleItemView sampleItemView = new SampleItemView(parent.getContext());
        sampleItemView.setLayoutParams(params);
        return new SampleListViewHolder(sampleItemView);
    }

    @Override
    public void onBindViewItemHolder(final SampleListViewHolder holder, SampleModel data, int position) {
        holder.bindView(data);

        holder.mItemView.getDragView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mItemTouchHelper.startDrag(holder);
                }
                return false;
            }
        });
    }

    /*
       Override {@link #onBindViewHolder(ViewHolder, int, List)} if Adapter can handle efficient partial bind.
    */
    @Override
    public void onBindViewHolder(final SampleListViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewItemHolder(holder, mData.get(position), position);

        } else {
            Bundle bundle = (Bundle) payloads.get(0);
            if (bundle.containsKey("sample")) {
                SampleModel sampleModel = Parcels.unwrap(bundle.getParcelable("sample"));
                holder.bindView(sampleModel);
            }
        }
    }

    /*
    * Add this method to update the adapter via DiffUtil.
    */
    public void onNewData(List<SampleModel> newData) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SampleDiffUtilsCallback(newData, mData));
        diffResult.dispatchUpdatesTo(this);
        mData.clear();
        mData.addAll(newData);
    }

    public void setTouchHelper(ItemTouchHelper touchHelper) {
        this.mItemTouchHelper = touchHelper;
    }

    class SampleListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SampleItemView mItemView;

        public SampleListViewHolder(SampleItemView itemView) {
            super(itemView);

            mItemView = itemView;
            mItemView.setOnClickListener(this);
            mItemView.setDeleteClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAdapterCallback.sampleItemDeleteSelected(getAdapterPosition());
                }
            });
        }

        public void bindView(SampleModel data) {
            mItemView.setSampleText(data.getTitle());
            mItemView.setSampleImage(data.getImageResId());
        }

        @Override
        public void onClick(View v) {
            mAdapterCallback.sampleItemSelected(getAdapterPosition(), mItemView.getSharedView());
        }
    }
}
