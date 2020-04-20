package com.example.countrypoc.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.countrypoc.interfaces.RetrofitCallback;
import com.example.countrypoc.model.CountryResponse;
import com.example.countrypoc.repository.CountryRepository;
import com.example.countrypoc.room.CountryRoomDBRepository;
import com.example.countrypoc.room.CountryTable;
import com.example.countrypoc.utils.Constatets;

import java.util.ArrayList;
import java.util.List;

import static com.example.countrypoc.utils.ConnectivityReceiver.isConnected;
import static com.example.countrypoc.utils.Constatets.SOMETHING_WENT_WRONG_PLEASE_TRY_AGAIN;


public class CountryDetailViewModel extends AndroidViewModel implements RetrofitCallback
{

  private CountryRoomDBRepository countryRoomDBRepository;
  private LiveData<List<CountryTable>> mAllPosts;
  private LiveData<CountryResponse> mAllPostsssssss;
  CountryRepository countryRepository ;
  private LiveData<ArrayList<CountryTable>>  retroObservable;
  Application application;
  public CountryDetailViewModel(Application application){
    super(application);
    this.application=application;
    countryRoomDBRepository = new CountryRoomDBRepository(application);
    countryRepository = new CountryRepository(application,this);

    getDataFromServer(application);

    mAllPosts = countryRoomDBRepository.getAllPosts();
  }

  public void getDataFromServer(Application application) {
    if(isConnected(application))
    {
      retroObservable = countryRepository.getCountryInformation();
    }
    else
    {
      Toast.makeText(application, Constatets.DEVICE_NOT_CONNECTED_TO_INTERNET,Toast.LENGTH_SHORT).show();
    }
  }

  public LiveData<List<CountryTable>> getAllPosts() {
    return mAllPosts;
  }
  @Override
  public void onSuccess() {

  }

  @Override
  public void onFailure(String error) {

    Toast.makeText(application,SOMETHING_WENT_WRONG_PLEASE_TRY_AGAIN,Toast.LENGTH_LONG).show();

  }

}