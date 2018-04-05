package com.omvp.app.ui.samples.locale.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ArrayAdapter;

import com.omvp.app.R;
import com.omvp.app.base.mvp.view.BaseViewFragment;
import com.omvp.app.base.mvp.view.BaseViewFragmentCallback;
import com.omvp.app.ui.samples.locale.presenter.SampleLocalePresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SampleLocaleFragment extends BaseViewFragment<SampleLocalePresenterImpl, SampleLocaleFragment.FragmentCallback>
        implements SampleLocaleView {

    @BindView(R.id.locale_value)
    AppCompatTextView mLocaleValue;
    @BindView(R.id.locale_selector)
    AppCompatButton mLocaleSelector;

    public interface FragmentCallback extends BaseViewFragmentCallback {

    }

    public static SampleLocaleFragment newInstance(Bundle bundle) {
        SampleLocaleFragment fragment = new SampleLocaleFragment();
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

    }

    @Override
    public void drawLocale(String locale) {
        mLocaleValue.setText(locale);
    }

    @Override
    public void drawLocaleSelector(List<String> localeList, int selection) {
        new AlertDialog.Builder(mContext)
                .setSingleChoiceItems(
                        new ArrayAdapter<>(mContext, android.R.layout.select_dialog_singlechoice, localeList),
                        selection,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.localeSelected(which);
                                dialog.dismiss();
                            }
                        }
                )
                .setTitle("Elige un idioma")
                .create()
                .show();
    }

    @OnClick(R.id.locale_selector)
    public void onLocaleChangeButtonSelected(View view) {
        mPresenter.changeLocale();
    }

}
