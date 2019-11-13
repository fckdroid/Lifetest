package dev.suhockii.lifetest.di.module

import javax.inject.Named
import javax.inject.Singleton

import dagger.Binds
import dagger.Module
import dev.suhockii.lifetest.repo.AppRepository
import dev.suhockii.lifetest.repo.LocalRepository
import dev.suhockii.lifetest.repo.RemoteRepository

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    @Named(LOCAL_REPOSITORY)
    abstract fun provideLocalRepository(localRepository: LocalRepository): AppRepository

    @Singleton
    @Binds
    @Named(REMOTE_REPOSITORY)
    abstract fun provideRemoteRepository(remoteRepository: RemoteRepository): AppRepository

    companion object {

        const val LOCAL_REPOSITORY = "Local"
        const val REMOTE_REPOSITORY = "Remote"
    }
}
