package com.omvp.app.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.omvp.app.util.DisposableManager;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;

import static com.omvp.app.base.BaseBroadcastReceiverModule.DISPOSABLE_BROADCAST_RECEIVER_MANAGER;

public abstract class BaseBroadcastReceiver extends BroadcastReceiver {

    @Inject
    @Named(DISPOSABLE_BROADCAST_RECEIVER_MANAGER)
    protected DisposableManager mDisposableManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        AndroidInjection.inject(this, context);
    }

}
