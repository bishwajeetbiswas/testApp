package com.redmart.bishwajeet.redmartproductlist.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.redmart.bishwajeet.redmartproductlist.BuildConfig;
import com.redmart.bishwajeet.redmartproductlist.R;
import com.redmart.bishwajeet.redmartproductlist.model.Product;
import com.redmart.bishwajeet.redmartproductlist.view.viewHolders.ProductViewHolder;
import com.redmart.bishwajeet.redmartproductlist.viewModel.ListItemClickListener;

import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter {
    private ArrayList<Product> mProducts;
    private Context mContext;
    private ListItemClickListener mListener;
    private final String REDMART_BASE_IMAGE_URL = BuildConfig.REDMART_BASE_IMAGE_URL;

    public ProductAdapter(Context context, ArrayList<Product> products, ListItemClickListener listener) {
        mContext = context;
        mProducts = products;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return (new ProductViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_product, parent, false), mListener));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductViewHolder rhvh = (ProductViewHolder) holder;

        rhvh.tvProductName.setText("" + mProducts.get(position).mTitle);
        rhvh.tvPrice.setText(mContext.getResources().getString(R.string.Rs) + mProducts.get(position).mPricing.mPrice);
        Glide.with(mContext)
                .load(REDMART_BASE_IMAGE_URL + mProducts.get(position).mImg.mName)
                .placeholder(mContext.getResources().getDrawable(R.drawable.place_holder))
                .error(mContext.getResources().getDrawable(R.drawable.place_holder))
                .into(rhvh.ivProduct);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
