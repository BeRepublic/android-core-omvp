package com.omvp.app.model;

import android.support.annotation.NonNull;

import com.omvp.app.base.ui.BaseModel;
import com.omvp.commons.DontObfuscate;

import org.parceler.Parcel;

import lombok.Data;

@Data
@Parcel
@DontObfuscate
public class SampleModel extends BaseModel {

    String title;
    String link;
    String pubdate;
    Integer imageResId;

    public SampleModel() {

    }

    @Override
    public int compareTo(@NonNull Object o) {
        SampleModel compare = (SampleModel) o;
        if (title.equals(compare.title)
                && link.equals(compare.link)
                && pubdate.equals(compare.pubdate)
                && imageResId.equals(compare.imageResId)) {
            return 0;
        }
        return 1;
    }
}
