package com.example.countrypoc.network;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.countrypoc.utils.Constatets.BASE_URL;


public class RetrofitService
{
    private static Retrofit retrofit = null;
   /* OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);

            httpClient.connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS);


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constatets.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
*/

    public static <S> S cteateService(Class<S> serviceClass) {
        return getClient().create(serviceClass);
    }
   public static Retrofit getClient() {
       if (retrofit == null) {

           OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
           HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
           logging.setLevel(HttpLoggingInterceptor.Level.BODY);
           httpClient.addInterceptor(logging);

           httpClient.connectTimeout(120, TimeUnit.SECONDS)
                   .writeTimeout(120, TimeUnit.SECONDS)
                   .readTimeout(120, TimeUnit.SECONDS);

           retrofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   //.baseUrl(PROD_ENV)
                   .addConverterFactory(GsonConverterFactory.create())
                   .client(httpClient.build())
                   .build();
       }
       return retrofit;
   }
}
