package com.example.devsmar.simplemvpnews.mvp.model;




import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Dev Smar on 3/25/2018.
 */

public interface NewsRestService {

   @GET("everything")
   Observable<Articles> getNewsBySearch(@Query("q") String item, @Query("apiKey") String key, @Query("pageSize") int size);
}
