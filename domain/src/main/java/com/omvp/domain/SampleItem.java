package com.omvp.domain;

import org.parceler.Parcel;

import lombok.Data;

@Data
@Parcel
public class SampleItem {

    String type;
    String title;

    public SampleItem() {
    }
}
