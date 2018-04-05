package com.omvp.app.ui.samples.vibration.view;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.SeekBar;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.vibration.presenter.VibrationPresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

public class VibrationFragment extends BaseViewFragment<VibrationPresenterImpl, VibrationFragment.FragmentCallback>
        implements VibrationView {

    @BindView(R.id.seekbar)
    SeekBar mSeekBar;
    @BindView(R.id.duration)
    AppCompatTextView mDurationTextView;

    private long mDuration = 100;

    public interface FragmentCallback extends BaseViewFragmentCallback {
        void onVibrateSelected(long duration);
    }

    public static VibrationFragment newInstance(Bundle bundle) {
        VibrationFragment fragment = new VibrationFragment();
        bundle = bundle == null ? new Bundle() : bundle;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        setupViews();
    }

    private void setupViews() {
        mSeekBar.setProgress(1);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateDuration(progress);
                if (progress < 1) {
                    seekBar.setProgress(1);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @OnClick(R.id.button1)
    public void onVibrateClicked(View view) {
        mCallback.onVibrateSelected(mDuration);
    }

    private void updateDuration(int progress) {
        mDuration = progress * 100;
        mDurationTextView.setText(mDuration + " ms");
    }
}
