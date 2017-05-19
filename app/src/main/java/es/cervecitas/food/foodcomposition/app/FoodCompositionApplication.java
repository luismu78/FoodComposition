package es.cervecitas.food.foodcomposition.app;

import android.app.Application;

import es.cervecitas.food.foodcomposition.dagger.AppComponent;
import es.cervecitas.food.foodcomposition.dagger.AppModule;
import es.cervecitas.food.foodcomposition.dagger.DaggerAppComponent;

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
