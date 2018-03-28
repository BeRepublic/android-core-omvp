package com.omvp.app.base;

import com.omvp.app.injector.scope.PerBroadcastReceiver;
import com.omvp.app.util.DisposableManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Provides base broadcastreceiver dependencies. This must be included in all broadcastreceivers modules, which must
 * provide a concrete implementation of {@link android.content.BroadcastReceiver}.
 */
@Module
public abstract class BaseBroadcastReceiverModule {

    public static final String DISPOSABLE_BROADCAST_RECEIVER_MANAGER = "BaseServiceModule.disposableBroascastReceiverManager";

    @Provides
    @Named(DISPOSABLE_BROADCAST_RECEIVER_MANAGER)
    @PerBroadcastReceiver
    static DisposableManager disposableBroadcastReceiverManager() {
        return new DisposableManager();
    }

}
