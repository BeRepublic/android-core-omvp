package com.omvp.domain.interactor.impl;

import com.omvp.domain.SampleItem;
import com.omvp.domain.interactor.GetHomeListUseCase;
import com.omvp.domain.repository.HomeRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;

public class GetHomeListUseCaseImpl extends BaseUseCaseImpl<HomeRepository> implements GetHomeListUseCase {

    @Inject
    public GetHomeListUseCaseImpl(HomeRepository repository) {
        super(repository);
    }

    @Override
    public Maybe<List<SampleItem>> execute() {
        return getRepository().retrieveList();
    }
}
