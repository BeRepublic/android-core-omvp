package com.omvp.components;

import android.graphics.Outline;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ComponentOutlineProvider extends ViewOutlineProvider {



    @Override
    public void getOutline(View view, Outline outline) {

    }
}
