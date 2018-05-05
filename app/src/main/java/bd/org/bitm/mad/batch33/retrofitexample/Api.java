package bd.org.bitm.mad.batch33.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://services.hanselandpetal.com/feeds/";

    @GET("flowers.json")
    Call<List<Product>> getProductList();

}
