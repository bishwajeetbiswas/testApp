package com.redmart.bishwajeet.redmartproductlist.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.redmart.bishwajeet.redmartproductlist.R;
import com.redmart.bishwajeet.redmartproductlist.RedmartApplication;
import com.redmart.bishwajeet.redmartproductlist.model.Img;
import com.redmart.bishwajeet.redmartproductlist.view.adapters.ProductImageAdapter;
import com.redmart.bishwajeet.redmartproductlist.viewModel.CommonMethods;
import com.redmart.bishwajeet.redmartproductlist.viewModel.ListItemClickListener;
import com.redmart.bishwajeet.redmartproductlist.viewModel.RedmartSubscriber;
import com.redmart.bishwajeet.redmartproductlist.data.RedmartResponse;
import com.redmart.bishwajeet.redmartproductlist.model.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailsActivity extends RedmartBaseActivity {

    @BindView(R.id.tv_product_title)
    TextView tvProductTitle;

    @BindView(R.id.rv_product_images)
    RecyclerView rvProductsImages;

    @BindView(R.id.tv_product_description)
    TextView tvProductDescription;

    @BindView(R.id.ll_description)
    LinearLayout llDescription;

    @BindView(R.id.tv_description_title)
    TextView tvDescriptionTitle;

    @BindView(R.id.tv_description)
    TextView tvDescription;

    private String mProductId;

    public static void launchActivity(Activity activity, String productId) {
        activity.startActivity(new Intent(activity, ProductDetailsActivity.class).putExtra("productId", productId));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
    }

    @Override
    public void handleActionBar() {
        setToolbarTitle(getResources().getString(R.string.product_activity_title));
        showToolbar(true);
        enableBackButton();
    }

    @Override
    public void loadValuesFromIntent() {
        Intent intent = getIntent();
        mProductId = intent.getStringExtra("productId");
        getProductDetail(mProductId);
    }

    @Override
    public void initViews() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity,
                LinearLayoutManager.HORIZONTAL, false);
        rvProductsImages.setLayoutManager(linearLayoutManager);
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }


    private void getProductDetail(String productId) {
        CommonMethods.showBottomProgressDialoge(mActivity, getResources().getString(R.string.please_wait));
        RedmartApplication mApp = (RedmartApplication) getApplication();
        mApp.getRedmartApiService().
                getRedmartProductDetail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RedmartSubscriber<RedmartResponse>(mActivity, true) {
                    @Override
                    public void onComplete() {
                        CommonMethods.hideBottomProgressDialoge();
                    }

                    @Override
                    public void onNext(RedmartResponse redmartResponse) {
                        super.onNext(redmartResponse);
                        if (redmartResponse.mProduct != null)
                            displayProductDetail(redmartResponse.mProduct);
                        else
                            CommonMethods.showOkAlertDialog(mActivity.getResources().getText(R.string.no_data_found_try_again).toString(), mActivity.getResources().getString(R.string.app_name)
                                    , mActivity, true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    private void displayProductDetail(Product productResponse) {
        tvProductTitle.setText(productResponse.mTitle);
        tvProductDescription.setText(productResponse.mDesc);
        if (productResponse.mDescriptionField.mSecondary.size() > 0) {
            tvDescriptionTitle.setText(productResponse.mDescriptionField.mSecondary.get(0).mName);
            tvDescription.setText(productResponse.mDescriptionField.mSecondary.get(0).mContent);
            llDescription.setVisibility(View.VISIBLE);
        } else
            llDescription.setVisibility(View.GONE);
        ArrayList<Img> images = productResponse.mImages;
        if (images != null && images.size() > 0) {
            rvProductsImages.setVisibility(View.VISIBLE);
            ProductImageAdapter productImageAdapter = new ProductImageAdapter(mActivity, images, new ListItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                }
            });
            rvProductsImages.setAdapter(productImageAdapter);
            runLayoutAnimation(rvProductsImages);
        } else {
            rvProductsImages.setVisibility(View.GONE);
        }
    }
}
