package com.example.countrypoc.repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.example.countrypoc.interfaces.RetrofitCallback;
import com.example.countrypoc.model.CountryResponse;
import com.example.countrypoc.network.APIInterface;
import com.example.countrypoc.network.RetrofitService;
import com.example.countrypoc.room.CountryRoomDBRepository;
import com.example.countrypoc.room.CountryTable;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.countrypoc.utils.Constatets.COUNTRY_INFORMATION;
import static com.example.countrypoc.utils.Constatets.COUNTRY_NAME;


public class CountryRepository
{
    private static CountryRepository countryRepository;
    ArrayList<CountryTable> webserviceResponseList = new ArrayList<>();
    private MutableLiveData<ArrayList<CountryTable>> newsData = new MutableLiveData<>();
    Application application;
    RetrofitCallback retrofitCallback;
    public  CountryRepository(Application application, RetrofitCallback retrofitCallback){
        this.retrofitCallback=retrofitCallback;
        this.application = application;
        mAPIInterface = RetrofitService.cteateService(APIInterface.class);
        if (countryRepository == null){
            countryRepository = new CountryRepository();
        }
    }

    private APIInterface mAPIInterface;

    public CountryRepository(){
        mAPIInterface = RetrofitService.cteateService(APIInterface.class);
    }

    public MutableLiveData<ArrayList<CountryTable>> getCountryInformation(){

        mAPIInterface.getCountryInformation().enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call,
                                   Response<CountryResponse> response) {
                if (response.isSuccessful()){
                    //newsData.setValue(response.body());


                    webserviceResponseList=response.body().getRows();
                    CountryRoomDBRepository countryRoomDBRepository = new CountryRoomDBRepository(application);
                    countryRoomDBRepository.insertPosts(webserviceResponseList);
                    newsData.setValue(webserviceResponseList);
                    retrofitCallback.onSuccess();
                    SharedPreferences.Editor mSharePreference=application.getSharedPreferences(COUNTRY_INFORMATION, Context.MODE_PRIVATE).edit();
                    mSharePreference.putString(COUNTRY_NAME,response.body().getTitle());
                    mSharePreference.apply();
                    mSharePreference.commit();
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                newsData.setValue(null);
                retrofitCallback.onFailure(t.getMessage());

            }
        });
        return newsData;
    }
}

