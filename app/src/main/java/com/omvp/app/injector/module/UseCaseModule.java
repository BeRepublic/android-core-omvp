package com.omvp.app.injector.module;

import com.omvp.domain.interactor.GetLocaleListUseCase;
import com.omvp.domain.interactor.GetLocaleUseCase;
import com.omvp.domain.interactor.GetSampleListUseCase;
import com.omvp.domain.interactor.GetSampleUseCase;
import com.omvp.domain.interactor.RegisterDeviceUseCase;
import com.omvp.domain.interactor.RemoveSampleUseCase;
import com.omvp.domain.interactor.SaveLocaleUseCase;
import com.omvp.domain.interactor.SaveSampleUseCase;
import com.omvp.domain.interactor.impl.GetLocaleListUseCaseImpl;
import com.omvp.domain.interactor.impl.GetLocaleUseCaseImpl;
import com.omvp.domain.interactor.impl.GetSampleListUseCaseImpl;
import com.omvp.domain.interactor.impl.GetSampleUseCaseImpl;
import com.omvp.domain.interactor.impl.RegisterDeviceUseCaseImpl;
import com.omvp.domain.interactor.impl.RemoveSampleUseCaseImpl;
import com.omvp.domain.interactor.impl.SaveLocaleUseCaseImpl;
import com.omvp.domain.interactor.impl.SaveSampleUseCaseImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract SaveLocaleUseCase saveLocaleUseCase(SaveLocaleUseCaseImpl usecase);

    @Binds
    @Singleton
    abstract GetLocaleUseCase getLocaleUseCase(GetLocaleUseCaseImpl usecase);

    @Binds
    @Singleton
    abstract GetLocaleListUseCase getLocaleListUseCase(GetLocaleListUseCaseImpl usecase);

    @Binds
    @Singleton
    abstract RegisterDeviceUseCase registerDeviceUseCase(RegisterDeviceUseCaseImpl usecase);

    // =============== SAMPLE ======================================================================

    @Binds
    @Singleton
    abstract GetSampleUseCase getSampleUseCase(GetSampleUseCaseImpl usecase);

    @Binds
    @Singleton
    abstract GetSampleListUseCase getSampleListUseCase(GetSampleListUseCaseImpl usecase);

    @Binds
    @Singleton
    abstract RemoveSampleUseCase removeSampleUseCase(RemoveSampleUseCaseImpl usecase);

    @Binds
    @Singleton
    abstract SaveSampleUseCase saveSampleUseCase(SaveSampleUseCaseImpl usecase);
}
