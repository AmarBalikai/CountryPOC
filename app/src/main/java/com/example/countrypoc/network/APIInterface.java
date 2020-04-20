package com.example.countrypoc.network;


import com.example.countrypoc.model.CountryResponse;
import com.example.countrypoc.utils.Constatets;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET(Constatets.COUNTRY_URL)
    Call<CountryResponse> getCountryInformation();
}
