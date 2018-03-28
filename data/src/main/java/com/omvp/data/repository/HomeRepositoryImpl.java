package com.omvp.data.repository;

import com.omvp.data.StaticHomeRepository;
import com.omvp.domain.SampleItem;
import com.omvp.domain.repository.HomeRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;

public class HomeRepositoryImpl implements HomeRepository {

    @Inject
    public HomeRepositoryImpl() {
        StaticHomeRepository.init();
    }

    @Override
    public Maybe<List<SampleItem>> retrieveList() {
        List<SampleItem> list = new ArrayList<>(StaticHomeRepository.sampleItemList.values());
        return Maybe.just(list);
    }
}
