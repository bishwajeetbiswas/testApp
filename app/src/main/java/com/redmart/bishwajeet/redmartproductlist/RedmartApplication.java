package com.redmart.bishwajeet.redmartproductlist;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.redmart.bishwajeet.redmartproductlist.data.RedmartFactory;
import com.redmart.bishwajeet.redmartproductlist.data.RedmartService;

/**
 * Created by Rajan on 12/30/2017.
 */

public class RedmartApplication extends Application {

    private static RedmartApplication mInstance;
    private Typeface redmartFontRegular = null;
    private Typeface redmartFontRegularBold = null;
    private Typeface redmartFontMedium = null;
    private RedmartService mRedmartService = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public RedmartService getRedmartApiService() {
        if (mRedmartService == null) {
            mRedmartService = RedmartFactory.create(this);
        }
        return mRedmartService;
    }

    public Typeface getRedmartFontRegular() {
        if (redmartFontRegular == null) {
            redmartFontRegular = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Regular.ttf");
        }
        return redmartFontRegular;
    }

    public Typeface getRedmartFontMedium() {
        if (redmartFontMedium == null) {
            redmartFontMedium = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Medium.ttf");
        }
        return redmartFontMedium;
    }

    public Typeface getRedmartFontBold() {
        if (redmartFontRegularBold == null) {
            redmartFontRegularBold = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Bold.ttf");
        }
        return redmartFontRegularBold;
    }

    public static synchronized RedmartApplication getInstance() {
        return mInstance;
    }
}
