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

    // HOME ITEMS
    public static final String VIEW = "VIEW";
    public static final String LIST = "LIST";
    public static final String HORIZONTAL_LIST = "HORIZONTAL_LIST";
    public static final String PAGER = "PAGER";
    public static final String MULTIPLE_FRAGMENTS = "MULTIPLE_FRAGMENTS";
    public static final String LOCATION = "LOCATION";
    public static final String PICTURE = "PICTURE";
    public static final String LOCALE = "LOCALE";
    public static final String VIBRATION = "VIBRATION";
    public static final String INPUT = "INPUT";
    public static final String SOCIAL = "SOCIAL";
    public static final String NOTICE_DIALOG = "NOTICE_DIALOG";

    // Patterns
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^[0-9A-Za-z!@#$%]{6,}$");
}
