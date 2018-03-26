package com.omvp.data.repository;

import android.text.TextUtils;

import com.omvp.domain.repository.LocaleRepository;
import com.raxdenstudios.preferences.AdvancedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;

public class LocaleRepositoryImpl implements LocaleRepository {

    private static final String PREF_LANGUAGE_SELECTED = "pref_language_selected";

    private final AdvancedPreferences preferences;
    private final Set<Locale> availableLocaleList;
    private final Locale defaultLocale;

    @Inject
    LocaleRepositoryImpl(AdvancedPreferences preferences, @Named("default") Locale defaultLocale, Set<Locale> availableLocaleList) {
        this.preferences = preferences;
        this.defaultLocale = defaultLocale;
        this.availableLocaleList = availableLocaleList;
    }

    @Override
    public Single<List<Locale>> retrieveList() {
        return Single.create(new SingleOnSubscribe<List<Locale>>() {
            @Override
            public void subscribe(SingleEmitter<List<Locale>> emitter) throws Exception {
                try {
                    if (!emitter.isDisposed()) {
                        emitter.onSuccess(new ArrayList<>(availableLocaleList));
                    }
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }
        });
    }

    @Override
    public Completable persist(final Locale locale) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                try {
                    if (!emitter.isDisposed()) {
                        preferences.put(PREF_LANGUAGE_SELECTED, locale.toString());
                        preferences.commit();
                        emitter.onComplete();
                    }
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }
        });
    }

    @Override
    public Single<Locale> retrieve() {
        return Single.create(new SingleOnSubscribe<Locale>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Locale> emitter) throws Exception {
                try {
                    if (!emitter.isDisposed()) {
                        Locale locale;
                        String language = preferences.get(PREF_LANGUAGE_SELECTED, "");
                        if (!TextUtils.isEmpty(language)) {
                            String[] values = language.split("_");
                            locale = new Locale(values[0], values[1]);
                        } else {
                            locale = defaultLocale;
                            preferences.put(PREF_LANGUAGE_SELECTED, locale.toString());
                            preferences.commit();
                        }
                        emitter.onSuccess(locale);
                    }
                } catch (Exception ex) {
                    emitter.onError(ex);
                }
            }
        });
    }

}
