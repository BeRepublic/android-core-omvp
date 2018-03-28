package com.omvp.app.model.mapper;

import android.content.Context;

import com.omvp.app.injector.scope.PerFragment;
import com.omvp.app.model.SampleItemModel;
import com.omvp.app.model.SampleModel;
import com.omvp.commons.DataMapper;
import com.omvp.domain.SampleDomain;
import com.omvp.domain.SampleItem;

import org.modelmapper.ModelMapper;

import javax.inject.Inject;

@PerFragment
public class SampleItemModelDataMapper extends DataMapper<SampleItem, SampleItemModel> {

    @Inject
    SampleItemModelDataMapper(Context context, ModelMapper modelMapper) {
        super(context, modelMapper);
    }

    @Override
    public SampleItemModel transform(SampleItem source) {
        return getModelMapper().map(source, SampleItemModel.class);
    }

    @Override
    public SampleItem inverseTransform(SampleItemModel source) {
        return getModelMapper().map(source, SampleItem.class);
    }

    @Override
    public boolean equals(SampleItem source, SampleItemModel destination) {
        return false;
    }

}

