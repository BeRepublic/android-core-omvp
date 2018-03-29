package com.omvp.app.util;

import android.util.Patterns;

import com.omvp.commons.Constants;
import com.raxdenstudios.commons.util.Utils;

public class ValidationHelper {

    public static boolean validateEmail(String email) {
        return Utils.hasValue(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean validatePassword(String password) {
        return Utils.hasValue(password) && Constants.PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean validatePhone(String phone) {
        return Utils.hasValue(phone) && Patterns.PHONE.matcher(phone).matches();
    }

    public static boolean validateUrl(String personalUrl) {
        return Patterns.WEB_URL.matcher(personalUrl).matches();
    }
}
