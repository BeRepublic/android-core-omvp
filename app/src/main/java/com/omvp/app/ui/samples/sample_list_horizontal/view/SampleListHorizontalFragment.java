package com.omvp.app.ui.samples.sample_list_horizontal.view;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.model.SampleModel;
import com.omvp.app.ui.samples.sample_list_horizontal.adapter.SampleListAdapter;
import com.omvp.app.ui.samples.sample_list_horizontal.presenter.SampleListHorizontalPresenter;
import com.omvp.app.util.RecyclerDragHelper;
import com.omvp.domain.SampleDomain;

import java.util.List;

import butterknife.BindView;

public class SampleListHorizontalFragment extends BaseViewFragment<SampleListHorizontalPresenter, SampleListHorizontalFragment.FragmentCallback>
        implements SampleListHorizontalView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_view)
    View mEmptyView;

    private SampleListAdapter mAdapter;

    public interface FragmentCallback extends BaseViewFragmentCallback {
        void onSampleItemSelected(SampleDomain sampleDomain, View sharedView);
    }

    public static SampleListHorizontalFragment newInstance(Bundle bundle) {
        SampleListHorizontalFragment fragment = new SampleListHorizontalFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_sample_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            mPresenter.onAddSampleItemSelected();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        setupViews();
    }

    @Override
    public void drawSampleList(List<Object> sampleModelList) {
        mAdapter.setItems(sampleModelList);

        mEmptyView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        mEmptyView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onSampleItemSelected(SampleDomain sampleDomain, View sharedView) {
        mCallback.onSampleItemSelected(sampleDomain, sharedView);
    }

    @Override
    public void drawRemoveAnimation(int position) {
        mAdapter.removeItem(position);
    }

    @Override
    public void drawAddAnimation(SampleModel model) {
        mAdapter.addItem(model);

        Toast.makeText(mContext, "Added " + model.getTitle(), Toast.LENGTH_SHORT).show();
    }

    private void setupViews() {
        mAdapter = new SampleListAdapter(getActivity(),
                (SampleListAdapter.AdapterCallback) mPresenter);

        LinearLayoutManager manager = new LinearLayoutManager(
                mContext,
                LinearLayoutManager.VERTICAL,
                false
        );

        /*
            Call LinearLayoutManager.setInitialPrefetchItemCount(N), where N is the number of views visible per inner item.
            For example, if your inner, horizontal lists show a minimum of three and a half item views at a time,
            you can improve performance by calling LinearLayoutManager.setInitialPrefetchItemCount(4).
            Doing so allows RecyclerView to create all relevant views early, while the outer RecyclerView is scrolling,
            which significantly reduces the amount of stuttering during scrolls.
         */
        manager.setInitialPrefetchItemCount(3);

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL));
    }
}
