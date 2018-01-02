package com.redmart.bishwajeet.redmartproductlist.view.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.redmart.bishwajeet.redmartproductlist.R;
import com.redmart.bishwajeet.redmartproductlist.RedmartApplication;
import com.redmart.bishwajeet.redmartproductlist.model.Product;
import com.redmart.bishwajeet.redmartproductlist.viewModel.CommonMethods;
import com.redmart.bishwajeet.redmartproductlist.viewModel.ListItemClickListener;
import com.redmart.bishwajeet.redmartproductlist.viewModel.RedmartSubscriber;
import com.redmart.bishwajeet.redmartproductlist.data.RedmartResponse;
import com.redmart.bishwajeet.redmartproductlist.view.adapters.ProductAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends RedmartBaseActivity {


//    @Inject
//    RedmartFactory redmartFactory;

    @BindView(R.id.rv_products)
    RecyclerView rvProducts;

    private boolean isLoading, isLastPage;
    private int page_size, mPage;
    private RedmartApplication mApp;
    private ArrayList<Product> products;
    private ProductAdapter productAdapter;
    private boolean isPagination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mApp = (RedmartApplication) getApplication();
        ButterKnife.bind(this);
        //mApp.getNetComponent().inject(this);
        page_size = 10;
        getRedmartProducts(mPage);
    }

    @Override
    public void handleActionBar() {
        setToolbarTitle(getResources().getString(R.string.home_activity_title));
        showToolbar(true);
        enableBackButton();
    }

    @Override
    public void loadValuesFromIntent() {

    }

    @Override
    public void initViews() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity,
                LinearLayoutManager.VERTICAL, false);
        rvProducts.setLayoutManager(linearLayoutManager);
        implementPagination(linearLayoutManager);
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void implementPagination(final LinearLayoutManager linearLayoutManager) {
        rvProducts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= page_size) {
                        getRedmartProducts(mPage++);
                    }
                }
            }

        });
    }

    private void getRedmartProducts(int page) {
        isLoading = true;
        CommonMethods.showBottomProgressDialoge(mActivity, getResources().getString(R.string.please_wait));
        mApp.getRedmartApiService()
                .getRedmartProduct(page, page_size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RedmartSubscriber<RedmartResponse>(mActivity, true) {
                    @Override
                    public void onComplete() {
                        CommonMethods.hideBottomProgressDialoge();
                        isLoading = false;
                    }

                    @Override
                    public void onNext(RedmartResponse redmartResponse) {
                        super.onNext(redmartResponse);
                        if (redmartResponse != null)
                            displayProdutcs(redmartResponse);
                        else
                            CommonMethods.showOkAlertDialog(mActivity.getResources().getText(R.string.no_data_found_try_again)
                                            .toString(), mActivity.getResources().getString(R.string.app_name)
                                    , mActivity, true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        isLoading = false;
                        mPage--;
                    }
                });
    }

    private void displayProdutcs(RedmartResponse redmartResponse) {
        ArrayList<Product> productList = redmartResponse.mProducts;
        if (productList == null || productList.size() < page_size)
            isLastPage = true;
        else
            isLastPage = false;
        isPagination = true;
        if (products == null) {
            isPagination = false;
            products = new ArrayList<>();
        }
        int previousSize = products.size();
        products.addAll(productList);
        if (productAdapter == null) {
            productAdapter = new ProductAdapter(mActivity, products, new ListItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    String mId = products.get(position).mId;
                    ProductDetailsActivity.launchActivity(mActivity, mId);
                }
            });
            rvProducts.setAdapter(productAdapter);
        } else
            productAdapter.notifyItemRangeChanged(previousSize, products.size());
        if (!isPagination)
            runLayoutAnimation(rvProducts);
    }
}
