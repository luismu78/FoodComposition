package com.example.luism.foodcomposition.dagger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.luism.foodcomposition.app.Constants;
import com.example.luism.foodcomposition.network.BedcaApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@Module
public class NetworkModule {

    private static final String NAME_BASE_URL = "NAME_BASE_URL";
    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 256 * 1024; // 256k
    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_AGE = 24 * 60 * 60; // 24 horas

    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return Constants.BASE_URL_BEDCA;
    }

    @Provides
    @Singleton
    Converter.Factory provideXmlConverter() {
        return SimpleXmlConverterFactory.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final Context context) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .cache(new Cache(context.getCacheDir(), HTTP_RESPONSE_DISK_CACHE_MAX_SIZE))
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                if (isNetworkAvailable(context)) {
                                    Response response = chain.proceed(chain.request());
                                    CacheControl cacheControl = new CacheControl.Builder()
                                            .maxAge(HTTP_RESPONSE_DISK_CACHE_MAX_AGE, TimeUnit.SECONDS)
                                            .build();
                                    return response.newBuilder()
                                            .header("Cache-Control", cacheControl.toString())
                                            .build();
                                } else {
                                    Request request = chain.request();
                                    request = request.newBuilder()
                                            .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
                                            .build();
                                    return chain.proceed(request);
                                }
                            }
                        }
                )
                .addInterceptor(loggingInterceptor) //TODO: REMOVE this interceptor before releasing. IMPORTANT!!!!
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, OkHttpClient okHttpClient, @Named(NAME_BASE_URL) String baseUrl) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(converter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    BedcaApi provideBedcaApi(Retrofit retrofit) {
        return retrofit.create(BedcaApi.class);
    }

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();

    }
}
