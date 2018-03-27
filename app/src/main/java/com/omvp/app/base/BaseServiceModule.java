package com.omvp.app.base;

import com.omvp.app.injector.scope.PerService;
import com.omvp.app.util.DisposableManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Provides base service dependencies. This must be included in all services modules, which must
 * provide a concrete implementation of {@link android.app.Service}.
 */
@Module
public abstract class BaseServiceModule {

    public static final String DISPOSABLE_SERVICE_MANAGER = "BaseServiceModule.disposableServiceManager";

    @Provides
    @Named(DISPOSABLE_SERVICE_MANAGER)
    @PerService
    static DisposableManager disposableServiceManager() {
        return new DisposableManager();
    }

}
