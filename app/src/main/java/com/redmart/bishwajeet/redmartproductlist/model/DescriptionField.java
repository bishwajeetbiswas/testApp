package com.redmart.bishwajeet.redmartproductlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Rajan on 1/3/2018.
 */

public class DescriptionField {
    @SerializedName("secondary")
    public ArrayList<Secondary> mSecondary = new ArrayList<>();
}
