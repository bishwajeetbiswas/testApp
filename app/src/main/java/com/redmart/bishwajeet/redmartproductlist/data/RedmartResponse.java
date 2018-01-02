package com.redmart.bishwajeet.redmartproductlist.data;

import com.google.gson.annotations.SerializedName;
import com.redmart.bishwajeet.redmartproductlist.model.Product;

import java.util.ArrayList;

/**
 * Created by bishwajeetbiswas on 02/10/17.
 */

public class RedmartResponse {
    @SerializedName("products")
    public ArrayList<Product> mProducts;
    @SerializedName("product")
    public Product mProduct;
}
