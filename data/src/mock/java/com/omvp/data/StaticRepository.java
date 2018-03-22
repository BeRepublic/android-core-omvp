package com.omvp.data;

import android.net.Uri;
import android.util.LongSparseArray;

import com.omvp.domain.SampleDomain;

import org.threeten.bp.LocalDateTime;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StaticRepository {

    private static final int NUM_ITEMS = 20;

    public static Map<Long, SampleDomain> sampleDomainList = new LinkedHashMap<>(NUM_ITEMS);

    public static void init() {
        if (sampleDomainList.isEmpty()) {
            for (int i = 1; i <= NUM_ITEMS; i++) {
                sampleDomainList.put((long) i, initSampleDomain(i));
            }
        }
    }

    public static SampleDomain initSampleDomain(long id) {
        SampleDomain domain = new SampleDomain();
        domain.setId(id);
        domain.setTitle("item " + id);
        domain.setLink(Uri.parse("https://www.google.com"));
        domain.setPubdate(LocalDateTime.now());
        domain.setImageResId(R.mipmap.ic_launcher_round);
        return domain;
    }

}
