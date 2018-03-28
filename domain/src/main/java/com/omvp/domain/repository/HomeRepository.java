package com.omvp.domain.repository;

import com.omvp.domain.SampleDomain;
import com.omvp.domain.SampleItem;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Angel on 15/02/2018.
 */

public interface HomeRepository extends Repository {

    Maybe<List<SampleItem>> retrieveList();
}
