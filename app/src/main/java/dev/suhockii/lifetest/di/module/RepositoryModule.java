package dev.suhockii.lifetest.di.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dev.suhockii.lifetest.repo.AppRepository;
import dev.suhockii.lifetest.repo.LocalRepository;
import dev.suhockii.lifetest.repo.RemoteRepository;

@Module
public abstract class RepositoryModule {

    public static final String LOCAL_REPOSITORY = "Local";
    public static final String REMOTE_REPOSITORY = "Remote";

    @Singleton
    @Binds
    @Named(LOCAL_REPOSITORY)
    abstract AppRepository provideLocalRepository(LocalRepository localRepository);

    @Singleton
    @Binds
    @Named(REMOTE_REPOSITORY)
    abstract AppRepository provideRemoteRepository(RemoteRepository remoteRepository);
}
