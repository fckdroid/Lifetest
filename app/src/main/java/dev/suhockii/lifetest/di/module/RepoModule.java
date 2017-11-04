package dev.suhockii.lifetest.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dev.suhockii.lifetest.data.remote.ProductsApi;
import dev.suhockii.lifetest.repo.ProductRepo;
import dev.suhockii.lifetest.repo.ProductRepoImpl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class RepoModule {
    @Singleton
    @Binds
    abstract ProductRepo provideProductRepo(ProductRepoImpl productRepo);
}
