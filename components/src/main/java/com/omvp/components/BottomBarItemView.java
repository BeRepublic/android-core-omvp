package com.omvp.components;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
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

    private Drawable mCounterBackground;
    private int mCounterColor;
    private int mIconRes;

    private boolean mAnimate;

    private int mCount = 0;

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
        TypedArray typedArray = context.getTheme().
                obtainStyledAttributes(attrs, R.styleable.BottomBarItemView, 0, 0);

        if (mCounterBackground == null) {
            mCounterBackground = ContextCompat.getDrawable(
                    getContext(),
                    typedArray.getResourceId(R.styleable.BottomBarItemView_counter_background, R.drawable.bottom_bar_dot_bg)
            );
        }

        if (mCounterColor <= 0) {
            mCounterColor = typedArray.getResourceId(R.styleable.BottomBarItemView_counter_color, R.color.color_accent);
        }

        if (mIconRes <= 0) {
            mIconRes = typedArray.getResourceId(R.styleable.BottomBarItemView_icon, R.drawable.bottom_navigation_item_home);
        }
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
        mCounterTextView.setBackgroundDrawable(mCounterBackground);
        mCounterTextView.setTextColor(mCounterColor);
        mImageView.setButtonDrawable(mIconRes);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.bottom_bar_item_view;
    }

    /**
     * Checkable methods
     **/

    @Override
    public void setChecked(boolean checked) {
        mChecked = checked;
        refreshDrawableState();
        setCheckedRecursive(this, checked);
        if (mOnCheckedChangeListener != null) {
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
            if (mOnReCheckedListener != null) {
                mOnReCheckedListener.onReChecked(this);
            }
        }
    }

    /**
     * Drawable States
     **/

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CheckedStateSet);
        }
        return drawableState;
    }

    /**
     * Handle clicks
     **/

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return onTouchEvent(ev);
    }

    /**
     * State persistence
     **/

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

    /**
     * Setters
     **/

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
        mCount += count;
        mCounterTextView.setText(String.valueOf(mCount));
        if (mAnimate) {
            mCounterTextView.setVisibility(INVISIBLE);
            if (mCount > 0) {
                animateCounter();
            }
        } else {
            mCounterTextView.setVisibility(mCount > 0 ? VISIBLE : INVISIBLE);
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListener = onCheckedChangeListener;
    }

    public void setOnReCheckedListener(OnReCheckedListener onReCheckedListener) {
        mOnReCheckedListener = onReCheckedListener;
    }

    public void setCounterBackground(@DrawableRes int background) {
        mCounterTextView.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), background));
    }

    public void setCounterBackground(@Nullable Drawable background) {
        mCounterTextView.setBackgroundDrawable(background);
    }

    public void setCounterColor(int color) {
        mCounterTextView.setTextColor(color);
    }

    public void setAnimateCounter(boolean animate) {
        mAnimate = animate;
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

    private void animateCounter() {
        mCounterTextView.setScaleX(1.8f);
        mCounterTextView.setScaleY(1.8f);
        mCounterTextView.setTranslationY(-20);

        mCounterTextView.animate()
                .scaleX(1.f)
                .scaleY(1.f)
                .translationYBy(20)
                .setDuration(300)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mCounterTextView.setVisibility(VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }

    public interface OnCheckedChangeListener {
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param itemView  The button view whose state has changed.
         * @param isChecked The new checked state of buttonView.
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

/*    public static class Builder {
        Context context;
        int iconRes;
        ViewGroup.LayoutParams params;
        boolean animateCounter;
        Object tag;
        boolean checked;
        OnCheckedChangeListener mCheckedChangeListener;
        OnReCheckedListener mReCheckedListener;
        int counterColor;
        Drawable counterBackground;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder icon(@DrawableRes int iconRes) {
            this.iconRes = iconRes;
            return this;
        }

        public Builder params(ViewGroup.LayoutParams params) {
            this.params = params;
            return this;
        }

        public Builder animateCounter(boolean animate) {
            this.animateCounter = animateCounter;
            return this;
        }

        public Builder tag(Object tag) {
            this.tag = tag;
            return this;
        }

        public Builder checked(boolean checked) {
            this.checked = checked;
            return this;
        }

        public Builder checkedChangeListener(OnCheckedChangeListener listener) {
            this.mCheckedChangeListener = listener;
            return this;
        }

        public Builder recheckedListener(OnReCheckedListener listener) {
            this.mReCheckedListener = listener;
            return this;
        }

        public Builder counterColor(int color) {
            this.counterColor = color;
            return this;
        }

        public Builder counterBackgrount(Drawable drawable) {
            this.counterBackground = drawable;
            return this;
        }

        public BottomBarItemView build() {
            return new BottomBarItemView(this);
        }
    }*/
}
