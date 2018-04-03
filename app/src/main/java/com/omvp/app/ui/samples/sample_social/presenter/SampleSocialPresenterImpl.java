
package com.omvp.app.ui.samples.sample_social.presenter;


import com.omvp.app.base.mvp.presenter.BasePresenter;
import com.omvp.app.ui.samples.sample_social.view.SampleSocialView;
import com.omvp.app.util.SocialAuthManager;

import javax.inject.Inject;

public class SampleSocialPresenterImpl extends BasePresenter<SampleSocialView>
        implements SampleSocialPresenter, SocialAuthManager.SocialAuthCallback {

    @Inject
    public SampleSocialPresenterImpl(SampleSocialView sampleSocialView) {
        super(sampleSocialView);
    }

    @Override
    public void onViewLoaded() {
        super.onViewLoaded();


    }

    @Override
    public void onSignInSuccess(SocialAuthManager.SocialAuth socialAuth, SocialAuthManager.SocialAuthData data) {

    }

    @Override
    public void onSignInFailed(SocialAuthManager.SocialAuth socialAuth, String message) {

    }
}
