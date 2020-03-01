package com.redmart.bishwajeet.redmartproductlist.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.redmart.bishwajeet.redmartproductlist.BuildConfig;
import com.redmart.bishwajeet.redmartproductlist.R;
import com.redmart.bishwajeet.redmartproductlist.model.Img;
import com.redmart.bishwajeet.redmartproductlist.view.viewHolders.ProductImageViewHolder;
import com.redmart.bishwajeet.redmartproductlist.viewModel.ListItemClickListener;

import java.util.ArrayList;


public class ProductImageAdapter extends RecyclerView.Adapter {
    private ArrayList<Img> mImages;
    private Context mContext;
    private ListItemClickListener mListener;
    private final String REDMART_BASE_IMAGE_URL = BuildConfig.REDMART_BASE_IMAGE_URL;

    public ProductImageAdapter(Context context, ArrayList<Img> images, ListItemClickListener listener) {
        mContext = context;
        mImages = images;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return (new ProductImageViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_product_image, parent, false), mListener));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductImageViewHolder rhvh = (ProductImageViewHolder) holder;

        Glide.with(mContext)
                .load(mImages.get(position).mName)
                .placeholder(mContext.getResources().getDrawable(R.drawable.place_holder))
                .error(mContext.getResources().getDrawable(R.drawable.place_holder))
                .into(rhvh.ivProduct);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }
}
