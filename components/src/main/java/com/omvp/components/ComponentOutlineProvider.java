package com.omvp.components;

import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ComponentOutlineProvider extends ViewOutlineProvider {

    private Resources mResources;

    public ComponentOutlineProvider(Resources resources) {
        mResources = resources;
    }

    @Override
    public void getOutline(View view, Outline outline) {
        if (view != null && view.getBackground() != null) {
            Rect rect = view.getBackground().copyBounds();
            rect.offset(0, -20);
            outline.setRoundRect(rect, 20);
        }
    }
}
