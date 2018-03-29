package com.omvp.app.injector.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.preferences.AdvancedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides application-wide dependencies.
 */
@Module
public abstract class PreferencesModule {

    @Provides
    @Singleton
    static SharedPreferences sharedPreferences(Application application) {
        return application.getSharedPreferences(Utils.getPackageName(application), Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    static AdvancedPreferences advancedPreferences(SharedPreferences sharedPreferences) {
        return new AdvancedPreferences(sharedPreferences);
    }

}
