package com.me.mygdxgame;

import abalone.controller.Lanceur;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        //cfg.useGL20 = false;
        cfg.useGL20 = true;
        cfg.useAccelerometer = true;
        Lanceur launch = new Lanceur();
       initialize(launch.getScreen(), cfg);
        //initialize(new MyGdxGame(), cfg);
    }
}