package com.omvp.app.ui.samples.sample_list_horizontal.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omvp.app.R;
import com.omvp.app.model.SampleModel;
import com.omvp.app.util.RecyclerDragHelper;
import com.omvp.components.SampleItemView;
import com.raxdenstudios.recycler.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SampleListAdapter extends RecyclerAdapter<Object, RecyclerView.ViewHolder> {

    private static final int HEADER = 0;
    private static final int NORMAL = 1;
    private static final int HORIZONTAL = 2;
    private static final int FOOTER = 3;

    private AdapterCallback mAdapterCallback;

    public interface AdapterCallback {
        void sampleItemSelected(int position, View sharedView);

        void sampleHorizontalItemSelected(int position, int horizontalListPosition, View sharedView);

        void sampleItemDeleteSelected(int position);
    }

    public SampleListAdapter(Context context, AdapterCallback callback) {
        super(context, -1);

        mAdapterCallback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        switch (viewType) {
            case HEADER:
            case NORMAL:
            case FOOTER:
                SampleItemView sampleItemView = new SampleItemView(parent.getContext());
                sampleItemView.setLayoutParams(params);
                return new SampleListViewHolder(sampleItemView);
            case HORIZONTAL:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_list_horizontal_item_layout, parent, false);
                view.setLayoutParams(params);
                return new SampleListHorizontalViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewItemHolder(RecyclerView.ViewHolder holder, Object data, int position) {
        switch (getItemViewType(position)) {
            case HORIZONTAL:
                ((SampleListHorizontalViewHolder) holder).bindView(data);
                break;
            case NORMAL:
                ((SampleListViewHolder) holder).bindView(data);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);
        if (item instanceof SampleModel) {
            return NORMAL;
        } else {
            return HORIZONTAL;
        }
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

        public void bindView(Object data) {
            if (data instanceof SampleModel) {
                SampleModel model = (SampleModel) data;
                mItemView.setSampleText(model.getTitle());
                mItemView.setSampleImage(model.getImageResId());
            }
        }

        @Override
        public void onClick(View v) {
            mAdapterCallback.sampleItemSelected(getAdapterPosition(), mItemView.getSharedView());
        }
    }

    class SampleListHorizontalViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        private SampleListHorizontalAdapter mAdapter;

        public SampleListHorizontalViewHolder(View itemView) {
            super(itemView);

            mRecyclerView = (RecyclerView) itemView;
            mAdapter = new SampleListHorizontalAdapter(itemView.getContext());
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),
                    LinearLayoutManager.HORIZONTAL, false));
            mRecyclerView.setHasFixedSize(true);
            mAdapter.setAdapterCallback(new SampleListHorizontalAdapter.AdapterCallback() {
                @Override
                public void sampleItemSelected(int position, View sharedView) {
                    mAdapterCallback.sampleHorizontalItemSelected(getAdapterPosition(), position, sharedView);
                }
            });
        }

        public void bindView(Object data) {
            if (data instanceof List) {
                List<SampleModel> models = (List<SampleModel>) data;
                mAdapter.setItems(models);
            }
        }
    }
}