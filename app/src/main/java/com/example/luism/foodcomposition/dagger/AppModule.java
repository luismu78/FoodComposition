package com.example.luism.foodcomposition.dagger;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    Locale provideLocale(Context context) {
        // https://stackoverflow.com/questions/38267213/how-to-get-the-current-locale-api-level-24
        // holder.getTvId().setText(String.format(locale, "%d", food.getFg_id()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        } else {
            return context.getResources().getConfiguration().locale;
        }
    }
}
