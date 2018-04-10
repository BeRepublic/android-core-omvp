
package com.omvp.app.ui.samples.locale.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.base.reactivex.BaseDisposableCompletableObserver;
import com.omvp.app.base.reactivex.BaseDisposableSingleObserver;
import com.omvp.app.ui.samples.locale.view.SampleLocaleView;
import com.omvp.app.util.LocaleHelper;
import com.omvp.domain.interactor.GetLocaleListUseCase;
import com.omvp.domain.interactor.GetLocaleUseCase;
import com.omvp.domain.interactor.SaveLocaleUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SampleLocalePresenterImpl extends BasePresenter<SampleLocaleView> implements SampleLocalePresenter {

    @Inject
    GetLocaleUseCase mGetLocaleUseCase;
    @Inject
    GetLocaleListUseCase mGetLocaleListUseCase;
    @Inject
    SaveLocaleUseCase mSaveLocaleUseCase;

    private Locale mLocale;
    private List<Locale> mLocaleList;

    @Inject
    public SampleLocalePresenterImpl(SampleLocaleView sampleLocaleView) {
        super(sampleLocaleView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();

        loadLocale();
    }

    @Override
    public void changeLocale() {
        mDisposableManager.add(mGetLocaleListUseCase.execute()
                .map(new Function<List<Locale>, List<Locale>>() {
                    @Override
                    public List<Locale> apply(List<Locale> localeList) throws Exception {
                        mLocaleList = localeList;
                        return localeList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new BaseDisposableSingleObserver<List<Locale>>(mContext) {
                    @Override
                    protected void onError(int code, String title, String description) {
                        hideProgress();
                        showError(code, title, description);
                    }

                    @Override
                    protected void onStart() {
                        showProgress();
                    }

                    @Override
                    public void onSuccess(List<Locale> localeList) {
                        hideProgress();
                        drawLocaleSelector(localeList);
                    }
                }));
    }

    @Override
    public void localeSelected(int position) {
        mLocale = mLocaleList.get(position);
        mDisposableManager.add(mSaveLocaleUseCase.execute(mLocale)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new BaseDisposableCompletableObserver(mContext) {
                    @Override
                    protected void onStart() {
                        showProgress();
                    }

                    @Override
                    protected void onError(int code, String title, String description) {
                        hideProgress();
                        showError(code, title, description);
                    }

                    @Override
                    public void onComplete() {
                        hideProgress();
                        LocaleHelper.updateConfiguration(mResources, mLocale);
                        drawLocale(mLocale);
                    }
                }));
    }

    private void loadLocale() {
        mDisposableManager.add(mGetLocaleUseCase.execute()
                .map(new Function<Locale, Locale>() {
                    @Override
                    public Locale apply(Locale locale) throws Exception {
                        mLocale = locale;
                        return locale;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new BaseDisposableSingleObserver<Locale>(mContext) {
                    @Override
                    protected void onError(int code, String title, String description) {
                        hideProgress();
                        showError(code, title, description);
                    }

                    @Override
                    protected void onStart() {
                        showProgress();
                    }

                    @Override
                    public void onSuccess(Locale locale) {
                        hideProgress();
                        drawLocale(locale);
                    }
                }));
    }

    private void drawLocale(Locale locale) {
        if (mView != null) {
            mView.drawLocale(locale.toString());
        }
    }

    private void drawLocaleSelector(List<Locale> localeList) {
        if (mView != null) {
            int selection = -1;
            int count = 0;
            List<String> localeStringList = new ArrayList<>();
            for (Locale locale : localeList) {
                if (mLocale.getLanguage().equals(locale.getLanguage())) {
                    selection = count;
                }
                localeStringList.add(locale.toString());
                count ++;
            }
            mView.drawLocaleSelector(localeStringList, selection);
        }
    }

}
