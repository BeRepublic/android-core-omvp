package com.omvp.components;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

public class NoticeDialogComponentView extends BaseComponentView {

    AppCompatTextView mTitleTextView;
    AppCompatTextView mDescriptionTextView;
    AppCompatTextView mAcceptTextView;
    AppCompatTextView mDenyTextView;

    public NoticeDialogComponentView(Context context) {
        super(context);
    }

    public NoticeDialogComponentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoticeDialogComponentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void loadAttributes(Context context, AttributeSet attrs) {

    }

    @Override
    protected void bindViews() {
        mTitleTextView = findViewById(R.id.title);
        mDescriptionTextView = findViewById(R.id.description);
        mAcceptTextView = findViewById(R.id.accept);
        mDenyTextView = findViewById(R.id.deny);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.notice_dialog_component_view;
    }

    public void setTitleText(String text) {
        mTitleTextView.setText(text);
    }

    public void setDescriptionText(String text) {
        mDescriptionTextView.setText(text);
    }

    public void setAcceptTextButton(String text, OnClickListener onClickListener) {
        mAcceptTextView.setText(text);
        mAcceptTextView.setOnClickListener(onClickListener);
        mAcceptTextView.setVisibility(View.VISIBLE);
    }

    public void setDenyTextButton(String text, OnClickListener onClickListener) {
        mDenyTextView.setText(text);
        mDenyTextView.setOnClickListener(onClickListener);
        mDenyTextView.setVisibility(View.VISIBLE);
    }

}
