package com.omvp.app.injector.module;

import com.omvp.app.injector.scope.PerActivity;
import com.omvp.domain.interactor.GetLocaleListUseCase;
import com.omvp.domain.interactor.GetLocaleUseCase;
import com.omvp.domain.interactor.GetSampleListUseCase;
import com.omvp.domain.interactor.GetSampleUseCase;
import com.omvp.domain.interactor.RemoveSampleUseCase;
import com.omvp.domain.interactor.SaveLocaleUseCase;
import com.omvp.domain.interactor.SaveSampleUseCase;
import com.omvp.domain.interactor.impl.GetLocaleListUseCaseImpl;
import com.omvp.domain.interactor.impl.GetLocaleUseCaseImpl;
import com.omvp.domain.interactor.impl.GetSampleListUseCaseImpl;
import com.omvp.domain.interactor.impl.GetSampleUseCaseImpl;
import com.omvp.domain.interactor.impl.RemoveSampleUseCaseImpl;
import com.omvp.domain.interactor.impl.SaveLocaleUseCaseImpl;
import com.omvp.domain.interactor.impl.SaveSampleUseCaseImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class UseCaseModule {

    @Binds
    @PerActivity
    abstract SaveLocaleUseCase saveLocaleUseCase(SaveLocaleUseCaseImpl repository);

    @Binds
    @PerActivity
    abstract GetLocaleUseCase getLocaleUseCase(GetLocaleUseCaseImpl repository);

    @Binds
    @PerActivity
    abstract GetLocaleListUseCase getLocaleListUseCase(GetLocaleListUseCaseImpl repository);

    // =============================================================================================

    @Binds
    @PerActivity
    abstract GetSampleUseCase getSampleUseCase(GetSampleUseCaseImpl repository);

    @Binds
    @PerActivity
    abstract GetSampleListUseCase getSampleListUseCase(GetSampleListUseCaseImpl repository);

    @Binds
    @PerActivity
    abstract RemoveSampleUseCase removeSampleUseCase(RemoveSampleUseCaseImpl repository);

    @Binds
    @PerActivity
    abstract SaveSampleUseCase saveSampleUseCase(SaveSampleUseCaseImpl repository);

}
