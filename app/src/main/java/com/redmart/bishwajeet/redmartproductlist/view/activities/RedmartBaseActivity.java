package com.redmart.bishwajeet.redmartproductlist.view.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.redmart.bishwajeet.redmartproductlist.R;
import com.redmart.bishwajeet.redmartproductlist.RedmartApplication;
import com.redmart.bishwajeet.redmartproductlist.view.customControls.RedmartTextView;

import butterknife.ButterKnife;

public abstract class RedmartBaseActivity extends AppCompatActivity {
    Toolbar toolbar;
    RedmartTextView tvTitle;

    public RedmartApplication mApp;
    public AppCompatActivity mActivity;
    public FrameLayout mActivityContentFrame;
    public ProgressDialog mProgressDialog;
    public CoordinatorLayout clRootLayout;
    public AppBarLayout mAppbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_redmart_base);

        mApp = (RedmartApplication) getApplicationContext();
        mActivity = this;

        mActivityContentFrame = findViewById(R.id.activity_content_frame);
        clRootLayout = findViewById(R.id.main_coordinator_layout);

        mAppbarLayout = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);
        tvTitle = findViewById(R.id.tvTitle);
        setActionBar();

        mProgressDialog = new ProgressDialog(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mActivityContentFrame.addView(LayoutInflater.from(this).inflate(layoutResID, null, false));
        ButterKnife.bind(this);
        initViews();
        handleActionBar();
        loadValuesFromIntent();
    }


    public void setActionBar() {
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setToolbarTitle(String text) {
        try {
            tvTitle.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setToolbarColor(int text) {
        try {
            toolbar.setBackgroundColor(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setToolbarTitleColor(int text) {
        try {
            tvTitle.setTextColor(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setToolbarCenter(boolean flag) {
        try {
            Toolbar.LayoutParams layoutParams =
                    new Toolbar.LayoutParams(tvTitle.getLayoutParams());
            if (!flag) {
                layoutParams.gravity = Gravity.LEFT;
            } else {
                layoutParams.gravity = Gravity.CENTER;
            }
            tvTitle.setLayoutParams(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enableBackButton() {
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } catch (Exception e) {

        }
    }

    public void showToolbar(boolean flag) {
        try {
            if (flag) {
                toolbar.setVisibility(View.VISIBLE);
            } else {
                toolbar.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void handleActionBar();

    public abstract void loadValuesFromIntent();

    public abstract void initViews();

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
