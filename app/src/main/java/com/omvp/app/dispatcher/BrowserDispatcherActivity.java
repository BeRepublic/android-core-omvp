package com.omvp.app.dispatcher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.omvp.app.base.BaseActivity;

/**
 * ${rootProject.ext.androidAppName}://open?action=operation_1
 * ${rootProject.ext.androidAppName}://open?action=operation_2
 */
public class BrowserDispatcherActivity extends BaseActivity {

    private static final String ACTION = "action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            String action = uri.getQueryParameter(ACTION);
            // TODO: depends of server implementation
        }
    }

}
