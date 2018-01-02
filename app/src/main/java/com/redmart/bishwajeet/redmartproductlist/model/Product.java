package com.redmart.bishwajeet.redmartproductlist.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    @SerializedName("id")
    public String mId;

    @SerializedName("title")
    public String mTitle;

    @SerializedName("desc")
    public String mDesc;

    @SerializedName("img")
    public Img mImg;

    @SerializedName("images")
    public ArrayList<Img> mImages = new ArrayList<>();

    @SerializedName("pricing")
    public Pricing mPricing;

    @SerializedName("description_fields")
    public DescriptionField mDescriptionField;
}
