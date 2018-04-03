package com.omvp.components;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import java.lang.reflect.Field;

import timber.log.Timber;

/**
 * Created by Angel on 09/10/2017.
 */

public class TextAdvancedInputLayout extends TextInputLayout {

    private int mErrorGravity = Gravity.END;

    public TextAdvancedInputLayout(Context context) {
        super(context);
    }

    public TextAdvancedInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextAdvancedInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setErrorEnabled(boolean enabled) {
        super.setErrorEnabled(enabled);
        if (!enabled) {
            return;
        }
        try {
            Field errorViewField = TextInputLayout.class.getDeclaredField("mErrorView");
            errorViewField.setAccessible(true);
            TextView errorView = (TextView) errorViewField.get(this);
            if (errorView != null) {
                errorView.setGravity(mErrorGravity);
                LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                params.gravity = mErrorGravity;
                errorView.setLayoutParams(params);
            }
        }
        catch (Exception e) {
            Timber.e(e);
        }
    }

    public void setErrorGravity(int errorGravity) {
        mErrorGravity = errorGravity;
    }
}
