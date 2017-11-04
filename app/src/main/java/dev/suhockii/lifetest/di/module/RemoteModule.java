package dev.suhockii.lifetest.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.suhockii.lifetest.data.remote.PictureApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RemoteModule {
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
                .baseUrl(PictureApi.BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    public PictureApi providePictureApi(Retrofit retrofit) {
        return retrofit.create(PictureApi.class);
    }
}
