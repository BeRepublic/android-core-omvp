package com.omvp.domain.repository;

import java.util.List;
import java.util.Locale;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface LocaleRepository extends Repository {

    Single<List<Locale>> retrieveList();

    Completable persist(final Locale locale);

    Single<Locale> retrieve();

}
