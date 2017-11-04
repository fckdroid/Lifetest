package dev.suhockii.lifetest.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.suhockii.lifetest.data.remote.ProductsApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RemoteDataModule {
    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        OkHttpClient stetho = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(stetho)
                .baseUrl(ProductsApi.BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    public ProductsApi providePicturesApi(Retrofit retrofit) {
        return retrofit.create(ProductsApi.class);
    }
}
