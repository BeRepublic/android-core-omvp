package com.omvp.app.model;

import com.omvp.commons.DontObfuscate;

import org.parceler.Parcel;

import lombok.Data;

@Data
@Parcel
@DontObfuscate
public class SampleItemModel {

    String title;
    String type;

    public SampleItemModel() {

    }

}
