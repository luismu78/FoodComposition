package com.example.luism.foodcomposition.network;

import com.example.luism.foodcomposition.pojos.FG_ListItems;
import com.example.luism.foodcomposition.pojos.F_ListItems;

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
}
