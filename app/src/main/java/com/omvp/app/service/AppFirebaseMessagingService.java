package com.omvp.app.service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.omvp.app.util.DisposableManager;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;

import static com.omvp.app.base.BaseServiceModule.DISPOSABLE_SERVICE_MANAGER;

public class AppFirebaseMessagingService extends FirebaseMessagingService {

    @Inject
    @Named(DISPOSABLE_SERVICE_MANAGER)
    protected DisposableManager mDisposableManager;

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mDisposableManager.dispose();
    }


}
