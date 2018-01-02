package com.redmart.bishwajeet.redmartproductlist.view.viewHolders;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.redmart.bishwajeet.redmartproductlist.R;
import com.redmart.bishwajeet.redmartproductlist.viewModel.ListItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.iv_product)
    public AppCompatImageView ivProduct;

    private ListItemClickListener mListener;

    public ProductImageViewHolder(View itemView, ListItemClickListener listener) {
        super(itemView);
        mListener=listener;
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());
    }
}
