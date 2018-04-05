package com.omvp.app.ui.samples.list.view;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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
import com.omvp.app.ui.samples.list.adapter.SampleListAdapter;
import com.omvp.app.ui.samples.list.presenter.SampleListPresenter;
import com.omvp.app.util.RecyclerDragHelper;
import com.omvp.domain.SampleDomain;

import java.util.List;

import butterknife.BindView;

public class SampleListFragment extends BaseViewFragment<SampleListPresenter, SampleListFragment.FragmentCallback>
        implements SampleListView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_view)
    View mEmptyView;

    private SampleListAdapter mAdapter;

    public interface FragmentCallback extends BaseViewFragmentCallback {
        void onSampleItemSelected(SampleDomain sampleDomain, View sharedView);
    }

    public static SampleListFragment newInstance(Bundle bundle) {
        SampleListFragment fragment = new SampleListFragment();
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
    public void drawSampleList(List<SampleModel> sampleModelList) {
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

    @Override
    public void drawViewMoved(int oldPosition, int newPosition) {
        mAdapter.moveItem(oldPosition, newPosition);
    }

    @Override
    public void drawViewSwiped(int position) {
        mAdapter.removeItem(position);
    }

    private void setupViews() {
        mAdapter = new SampleListAdapter(getActivity(),
                (SampleListAdapter.AdapterCallback) mPresenter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

        RecyclerDragHelper dragHelper = new RecyclerDragHelper((RecyclerDragHelper.ActionCompletionContract) mPresenter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(dragHelper);
        mAdapter.setTouchHelper(touchHelper);
        touchHelper.attachToRecyclerView(mRecyclerView);
    }
}
