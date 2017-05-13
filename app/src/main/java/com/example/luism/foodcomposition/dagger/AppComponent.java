package com.example.luism.foodcomposition.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class
        })
public interface AppComponent {

}
