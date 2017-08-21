package com.lemmetravel.seniorproject.fonts;

import android.app.Application;

import com.lemmetravel.seniorproject.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class InintialFonts extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
        .setDefaultFontPath("fonts/thaisanslite_r1.ttf")
        .setFontAttrId(R.attr.fontPath)
        .build());
    }
}
