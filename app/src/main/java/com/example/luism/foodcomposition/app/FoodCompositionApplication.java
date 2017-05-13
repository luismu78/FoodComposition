package com.example.luism.foodcomposition.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class FoodCompositionApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initLeakCannary();
    }

    private void initLeakCannary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);
    }
}
