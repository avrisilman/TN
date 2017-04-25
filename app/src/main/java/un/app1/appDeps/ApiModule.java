package un.app1.appDeps;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import un.app1.appNetwork.service.ApiService;
import un.app1.appNetwork.service.MainService;

@Module
public class ApiModule {

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://private-1e1e5e-app118.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor headerInterceptor = chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder()
                    .header("Accept", "application/json");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };

        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(headerInterceptor)
                .build();
    }

    @Singleton
    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    MainService provideMainService(ApiService apiService, Context context) {
        return new MainService(apiService, context);
    }

}