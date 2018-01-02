
package com.redmart.bishwajeet.redmartproductlist.data;

import com.redmart.bishwajeet.redmartproductlist.model.Product;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RedmartService {
    @GET("search")
    Observable<RedmartResponse> getRedmartProduct(@Query("page") int page,@Query("pageSize") int pageSize);

    @GET("products/{product_id}")
    Observable<RedmartResponse> getRedmartProductDetail(@Path("product_id") String productId);
}
