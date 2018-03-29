package com.omvp.app.injector.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.raxdenstudios.cron.CronHandler;
import com.raxdenstudios.cron.data.CronService;
import com.raxdenstudios.cron.data.factory.CronPreferencesServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides application-wide dependencies.
 */
@Module
public abstract class CronModule {

    @Provides
    @Singleton
    CronService cronService(SharedPreferences settings) {
        return new CronPreferencesServiceImpl(settings);
    }

    @Provides
    @Singleton
    CronHandler cronHandler(Context context, CronService cronService) {
        return new CronHandler(context, cronService);
    }

}
