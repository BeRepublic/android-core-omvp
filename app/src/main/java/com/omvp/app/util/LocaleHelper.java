package com.omvp.app.util;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Created by Angel on 26/03/2018.
 */

public class LocaleHelper {

    public static void updateConfiguration(Resources resources, Locale locale) {
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

}
