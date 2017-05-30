package es.cervecitas.food.foodcomposition.network;

import es.cervecitas.food.foodcomposition.ui.foodgroup.FG_ListItems;
import es.cervecitas.food.foodcomposition.ui.foodgroupdetail.F_ListItems;
import es.cervecitas.food.foodcomposition.ui.fooditem.FoodItemResponse;
import es.cervecitas.food.foodcomposition.ui.nutrientdetail.NutrientDetailResponse;
import es.cervecitas.food.foodcomposition.ui.nutrientes.BedcaResponse;
import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BedcaApi {

    @POST("/bdpub/procquery.php")
    Single<FG_ListItems> getFoodGroups(@Body RequestBody bodyParams);

    @POST("/bdpub/procquery.php")
    Single<FoodItemResponse> getFoodItemDetail(@Body RequestBody bodyParams);

    @POST("/bdpub/procquery.php")
    Single<F_ListItems> getFoods(@Body RequestBody bodyParams);

    @POST("/bdpub/procquery.php")
    Single<BedcaResponse> getNutrientNames(@Body RequestBody requestBody);

    @POST("/bdpub/procquery.php")
    Single<NutrientDetailResponse> getFoodByNutrient(@Body RequestBody requestBody);
}
