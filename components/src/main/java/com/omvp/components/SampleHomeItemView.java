package com.omvp.components;

import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

public class SampleHomeItemView extends BaseComponentView {

    private AppCompatTextView mSampleTextView;

    public SampleHomeItemView(Context context) {
        super(context);
    }

    public SampleHomeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SampleHomeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void loadAttributes(Context context, AttributeSet attrs) {

    }

    @Override
    protected void bindViews() {
        mSampleTextView = findViewById(R.id.text);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.sample_home_item_layout;
    }

    public void setSampleText(String text) {
        mSampleTextView.setText(text);
    }

}
