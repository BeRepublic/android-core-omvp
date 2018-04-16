package com.omvp.commons;

import java.util.regex.Pattern;

/**
 * Created by Angel on 18/07/2017.
 */

public class Constants {

    public static final int SPLASH_DELAY = 1500;
    public static final String DEFAULT_FONT = "fonts/AvenirNextLTPro-Regular.otf";

    //LOCATION
    public static final int LOCATION_INTERVAL = 10000;
    public static final int LOCATION_FASTEST_INTERVAL = LOCATION_INTERVAL / 2;

    // Patterns
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^[0-9A-Za-z!@#$%]{6,}$");
}
