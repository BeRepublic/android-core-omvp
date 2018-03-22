package com.omvp.app.interceptor.location;

import android.location.Location;

import com.google.android.gms.location.LocationListener;
import com.raxdenstudios.square.interceptor.Interceptor;

public interface LocationInterceptor extends Interceptor {

    Location getCurrentLocation();

    void addLocationListener(LocationListener locationListener);

}
