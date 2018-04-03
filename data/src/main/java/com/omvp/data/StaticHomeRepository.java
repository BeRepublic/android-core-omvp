package com.omvp.data;

import com.omvp.domain.SampleItem;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.omvp.commons.Constants.HORIZONTAL_LIST;
import static com.omvp.commons.Constants.INPUT;
import static com.omvp.commons.Constants.LIST;
import static com.omvp.commons.Constants.LOCALE;
import static com.omvp.commons.Constants.LOCATION;
import static com.omvp.commons.Constants.MULTIPLE_FRAGMENTS;
import static com.omvp.commons.Constants.PAGER;
import static com.omvp.commons.Constants.PICTURE;
import static com.omvp.commons.Constants.SOCIAL;
import static com.omvp.commons.Constants.VIBRATION;
import static com.omvp.commons.Constants.VIEW;

public class StaticHomeRepository {

    private static final int NUM_ITEMS = 10;

    public static Map<Integer, SampleItem> sampleItemList = new LinkedHashMap<>(NUM_ITEMS);

    public static void init() {
        if (sampleItemList.isEmpty()) {
            sampleItemList.put(0, initSampleItem(VIEW, "A sample view"));
            sampleItemList.put(1, initSampleItem(LIST, "A sample list view"));
            sampleItemList.put(2, initSampleItem(HORIZONTAL_LIST, "A sample list view with nested horizontal list"));
            sampleItemList.put(3, initSampleItem(PAGER, "A sample pager view"));
            sampleItemList.put(4, initSampleItem(MULTIPLE_FRAGMENTS, "A sample view with multiple fragments"));
            sampleItemList.put(5, initSampleItem(LOCATION, "A sample view to show device location"));
            sampleItemList.put(6, initSampleItem(PICTURE, "A sample view to take or pick up photos"));
            sampleItemList.put(7, initSampleItem(LOCALE, "A sample view to change device language"));
            sampleItemList.put(8, initSampleItem(VIBRATION, "A sample view to use device vibration"));
            sampleItemList.put(9, initSampleItem(INPUT, "A view with input layouts"));
            sampleItemList.put(10, initSampleItem(SOCIAL, "A view with socials connection"));
        }
    }

    private static SampleItem initSampleItem(String type, String title) {
        SampleItem item = new SampleItem();
        item.setType(type);
        item.setTitle(title);
        return item;
    }

}
