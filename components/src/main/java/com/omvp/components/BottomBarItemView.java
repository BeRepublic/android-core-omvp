package com.omvp.components;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;

public class BottomBarItemView extends BaseComponentView implements Checkable {

    private static final int[] CheckedStateSet = {android.R.attr.state_checked};

    private boolean mChecked = false;

    private AppCompatTextView mTextView;
    private AppCompatCheckBox mImageView;
    private AppCompatTextView mCounterTextView;

    private OnCheckedChangeListener mOnCheckedChangeListener;
    private OnReCheckedListener mOnReCheckedListener;

    public BottomBarItemView(Context context) {
        super(context);
    }

    public BottomBarItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomBarItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void loadAttributes(Context context, AttributeSet attrs) {

    }

    @Override
    protected void bindViews() {
        mTextView = findViewById(R.id.title);
        mImageView = findViewById(R.id.image);
        mCounterTextView = findViewById(R.id.number);

        setClickable(true);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.bottom_bar_item_view;
    }

    /**   Checkable methods   **/

    @Override
    public void setChecked(boolean checked) {
        mChecked = checked;
        refreshDrawableState();
        setCheckedRecursive(this, checked);
        if (mOnCheckedChangeListener != null){
            mOnCheckedChangeListener.onCheckedChanged(this, checked);
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        if (!isChecked()) {
            setChecked(!mChecked);

        } else {
            if (mOnReCheckedListener != null){
                mOnReCheckedListener.onReChecked(this);
            }
        }
    }

    /**   Drawable States    **/

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CheckedStateSet);
        }
        return drawableState;
    }

    /**   Handle clicks  **/

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return onTouchEvent(ev);
    }

    /**   State persistency  **/

    static class SavedState extends BaseSavedState {
        boolean checked;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            checked = (Boolean) in.readValue(null);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeValue(checked);
        }

        @Override
        public String toString() {
            return "CheckableLinearLayout.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " checked=" + checked + "}";
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    @Override
    public Parcelable onSaveInstanceState() {
        // Force our ancestor class to save its state
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);

        ss.checked = isChecked();
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;

        super.onRestoreInstanceState(ss.getSuperState());
        setChecked(ss.checked);
        requestLayout();
    }

    /**   Setters  **/

    public void setText(String text) {
        mTextView.setText(text);
        mTextView.setVisibility(VISIBLE);
    }

    public void setText(@StringRes int textRes) {
        mTextView.setText(textRes);
        mTextView.setVisibility(VISIBLE);
    }

    public void setIcon(@DrawableRes int iconRes) {
        mImageView.setButtonDrawable(iconRes);
    }

    public void setCounter(int count) {
        mCounterTextView.setText(String.valueOf(count));
        mCounterTextView.setVisibility(count > 0 ? VISIBLE : INVISIBLE);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListener = onCheckedChangeListener;
    }

    public void setOnReCheckedListener(OnReCheckedListener onReCheckedListener) {
        mOnReCheckedListener = onReCheckedListener;
    }

    private void setCheckedRecursive(ViewGroup parent, boolean checked) {
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = parent.getChildAt(i);
            if (v instanceof Checkable) {
                ((Checkable) v).setChecked(checked);
            }

            if (v instanceof ViewGroup) {
                setCheckedRecursive((ViewGroup) v, checked);
            }
        }
    }

    public interface OnCheckedChangeListener {
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param itemView The button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        void onCheckedChanged(BottomBarItemView itemView, boolean isChecked);
    }

    public interface OnReCheckedListener {
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param itemView The button view whose state has changed.
         */
        void onReChecked(BottomBarItemView itemView);
    }
}
