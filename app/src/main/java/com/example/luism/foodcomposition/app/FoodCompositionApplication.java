package com.example.luism.foodcomposition.app;

import android.app.Application;

import com.example.luism.foodcomposition.dagger.AppComponent;
import com.example.luism.foodcomposition.dagger.AppModule;
import com.example.luism.foodcomposition.dagger.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

public class FoodCompositionApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initLeakCannary();

        appComponent = initDagger(this);
    }

    private void initLeakCannary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);
    }

    protected AppComponent initDagger(FoodCompositionApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
