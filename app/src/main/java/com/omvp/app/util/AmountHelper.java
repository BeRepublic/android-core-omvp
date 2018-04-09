package com.omvp.app.util;


import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class AmountHelper {

    public static final String EURO_ISO = "EUR";

    public boolean greater(BigDecimal b1, BigDecimal b2) {
        return b1.compareTo(b2) > 0;
    }

    public boolean equal(BigDecimal b1, BigDecimal b2) {
        return b1.compareTo(b2) == 0;
    }

    public boolean minor(BigDecimal b1, BigDecimal b2) {
        return b1.compareTo(b2) < 0;
    }

    // Convert double value to human amount value

    public static String amountToString(double amount) {
        return amountToString(
                new BigDecimal(String.valueOf(amount)),
                Currency.getInstance(Locale.getDefault()).getCurrencyCode()
        );
    }

    public static String amountToString(double amount, @NonNull String currencyIso) {
        return amountToString(
                new BigDecimal(String.valueOf(amount)),
                currencyIso,
                2
        );
    }

    public static String amountToString(double amount, @NonNull String currencyIso, int scale) {
        return amountToString(
                new BigDecimal(String.valueOf(amount)),
                currencyIso,
                scale
        );
    }


    // Convert BigDecimal value to human amount value

    public static String amountToString(@NonNull BigDecimal amount) {
        return amountToString(
                amount,
                Currency.getInstance(Locale.getDefault()).getCurrencyCode()
        );
    }

    public static String amountToString(@NonNull BigDecimal amount, @NonNull String currencyIso) {
        return amountToString(
                amount,
                currencyIso,
                2
        );
    }

    public static String amountToString(@NonNull BigDecimal amount, @NonNull String currencyIso, int scale) {
        BigDecimal displayPrice = amount.setScale(scale, RoundingMode.HALF_EVEN);

        Currency currency = Currency.getInstance(currencyIso);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setCurrency(currency);

        return numberFormat.format(displayPrice.doubleValue());
    }


    public static String convertPercentageToString(@NonNull BigDecimal percent, int digits) {
        NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.getDefault());
        if (digits > 0) {
            numberFormat.setMinimumFractionDigits(digits);
        }
        return numberFormat.format(percent.doubleValue());
    }
}
