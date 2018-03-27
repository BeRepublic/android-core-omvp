package com.omvp.app.injector.module;

import com.omvp.data.repository.CredentialsRepositoryImpl;
import com.omvp.data.repository.DeviceRepositoryImpl;
import com.omvp.data.repository.LocaleRepositoryImpl;
import com.omvp.data.repository.SampleRepositoryImpl;
import com.omvp.domain.repository.CredentialsRepository;
import com.omvp.domain.repository.DeviceRepository;
import com.omvp.domain.repository.LocaleRepository;
import com.omvp.domain.repository.SampleRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract LocaleRepository localeRepository(LocaleRepositoryImpl repository);

    @Binds
    @Singleton
    abstract CredentialsRepository credentialsRepository(CredentialsRepositoryImpl repository);

    @Binds
    @Singleton
    abstract DeviceRepository deviceRepository(DeviceRepositoryImpl repository);


    // =============== SAMPLE ======================================================================

    @Binds
    @Singleton
    abstract SampleRepository sampleRepository(SampleRepositoryImpl repository);

}
