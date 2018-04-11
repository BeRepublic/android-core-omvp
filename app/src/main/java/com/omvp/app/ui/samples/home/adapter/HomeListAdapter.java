package com.omvp.app.ui.samples.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.omvp.app.ui.samples.home.presenter.HomePresenterImpl;
import com.omvp.components.SampleHomeItemView;
import com.raxdenstudios.recycler.RecyclerAdapter;

public class HomeListAdapter extends RecyclerAdapter<HomePresenterImpl.SampleItemModel, HomeListAdapter.HomeListViewHolder> {

    private AdapterCallback mAdapterCallback;

    public interface AdapterCallback {
        void itemSelected(int position);
    }

    public HomeListAdapter(Context context, AdapterCallback callback) {
        super(context, -1);

        mAdapterCallback = callback;
    }

    @Override
    public HomeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        SampleHomeItemView sampleItemView = new SampleHomeItemView(parent.getContext());
        sampleItemView.setLayoutParams(params);
        return new HomeListViewHolder(sampleItemView);
    }

    @Override
    public void onBindViewItemHolder(final HomeListViewHolder holder, HomePresenterImpl.SampleItemModel data, int position) {
        holder.bindView(data);
    }

    class HomeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SampleHomeItemView mItemView;

        public HomeListViewHolder(SampleHomeItemView itemView) {
            super(itemView);

            mItemView = itemView;
            mItemView.setOnClickListener(this);
        }

        public void bindView(HomePresenterImpl.SampleItemModel data) {
            mItemView.setSampleText(data.getTitle());
        }

        @Override
        public void onClick(View v) {
            mAdapterCallback.itemSelected(getAdapterPosition());
        }
    }
}
