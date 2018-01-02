package com.redmart.bishwajeet.redmartproductlist.data;

import android.content.Context;

import com.google.gson.Gson;
import com.redmart.bishwajeet.redmartproductlist.BuildConfig;
import com.redmart.bishwajeet.redmartproductlist.utils.ConnectivityInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RedmartFactory {
    private static RedmartService mInterface;

    public static RedmartService create(Context context) {

        if (mInterface == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ConnectivityInterceptor(context));
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.REDMART_BASE_URL)
                    .client(builder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
            mInterface = retrofit.create(RedmartService.class);
        }
        return mInterface;
    }
}

