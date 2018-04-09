package com.omvp.data;

import android.net.Uri;

import com.omvp.domain.SampleDomain;

import org.threeten.bp.LocalDateTime;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import timber.log.Timber;

public class StaticRepository {

    private static final int NUM_ITEMS = 20;

    public static Map<String, SampleDomain> sampleDomainList = new LinkedHashMap<>(NUM_ITEMS);

    public static void init() {
        if (sampleDomainList.isEmpty()) {
            for (int i = 1; i <= NUM_ITEMS; i++) {
                String id = UUID.randomUUID().toString();
                sampleDomainList.put(id, initSampleDomain(id, i));
            }
        }
    }

    public static SampleDomain initSampleDomain(String id, int position) {
        Timber.d("id: " + id);
        SampleDomain domain = new SampleDomain();
        domain.setId(id);
        domain.setTitle("item " + position);
        domain.setLink(Uri.parse("https://www.google.com"));
        domain.setPubdate(LocalDateTime.now());
        domain.setImageResId(R.mipmap.ic_launcher_round);
        return domain;
    }

}
