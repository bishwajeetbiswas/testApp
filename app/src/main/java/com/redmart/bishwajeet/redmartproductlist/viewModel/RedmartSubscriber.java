package com.redmart.bishwajeet.redmartproductlist.viewModel;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.redmart.bishwajeet.redmartproductlist.R;
import com.redmart.bishwajeet.redmartproductlist.data.RedmartResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class RedmartSubscriber<T> implements Observer<T> {
    private Context mContext;
    private boolean manageEroor;

    public RedmartSubscriber(Context context, boolean manageError) {
        this.manageEroor = manageError;
        this.mContext = context;
        if (context instanceof Activity && manageError)
            this.manageEroor = true;
        else this.manageEroor = false;
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onComplete();
        RedmartResponse response = (RedmartResponse) t;
        if (response.mProducts != null || response.mProduct != null) {
            //for other stuffs
        } else
            CommonMethods.showOkAlertDialog(mContext.getResources().getText(R.string.no_data_found_try_again).toString(), mContext.getResources().getString(R.string.app_name)
                    , mContext, true);
    }

    @Override
    public void onError(Throwable e) {
        onComplete();
        if (manageEroor)
            CommonMethods.showOkAlertDialog(mContext.getResources().getText(R.string.network_error).toString(), mContext.getResources().getString(R.string.app_name)
                    , mContext, true);
    }

    @Override
    public abstract void onComplete();
}
