
package com.redmart.bishwajeet.redmartproductlist.data;

import com.redmart.bishwajeet.redmartproductlist.model.Product;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RedmartService {
    @GET("chandan")
    Observable<RedmartResponse> getRedmartProduct();

    @GET("chandan")
    Observable<RedmartResponse> getRedmartProductDetail();
}
