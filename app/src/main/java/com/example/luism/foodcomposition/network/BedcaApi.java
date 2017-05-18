package com.example.luism.foodcomposition.network;

import com.example.luism.foodcomposition.ui.foodgroup.FG_ListItems;
import com.example.luism.foodcomposition.ui.foodgroupdetail.F_ListItems;
import com.example.luism.foodcomposition.ui.fooditem.FoodItemResponse;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BedcaApi {

    // FoodGroups
    @Headers({
            "Content-Type:text/xml",
            "Accept-Charset: utf-8"
    })
    @POST("/bdpub/procquery.php")
    Single<FG_ListItems> getFoodGroups(@Body RequestBody bodyParams);

    // Food Group Detail
    @Headers({
            "Content-Type:text/xml",
            "Accept-Charset: utf-8"
    })
    @POST("/bdpub/procquery.php")
    Single<F_ListItems> getFoodGroupDetail(@Body RequestBody bodyParams);

    // Food Item detail
    @Headers({
            "Content-Type:text/xml",
            "Accept-Charset: utf-8"
    })
    @POST("/bdpub/procquery.php")
    Single<FoodItemResponse> getFoodItemDetail(@Body RequestBody bodyParams);
}
