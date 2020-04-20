package com.example.countrypoc.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.countrypoc.R;
import com.example.countrypoc.adapter.CountryAdapter;
import com.example.countrypoc.application.App;
import com.example.countrypoc.databinding.FragmentMainBinding;
import com.example.countrypoc.room.CountryTable;
import com.example.countrypoc.viewmodel.CountryDetailViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.countrypoc.utils.Constatets.COUNTRY_INFORMATION;
import static com.example.countrypoc.utils.Constatets.COUNTRY_NAME;

public class MainFragment extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    FragmentMainBinding binding;
    private CountryDetailViewModel mainViewModel;
    private CountryAdapter countryAdapter;
    private SharedPreferences mSharePreference;



    @SuppressLint("NewApi")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);
        View root = binding.getRoot();
        swipeRefreshLayout = root.findViewById(R.id.swipeToRefresh);
        LinearLayoutManager horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.rvCountryList.setLayoutManager(horizontalLayout);
        binding.rvCountryList.setHasFixedSize(true);
        binding.rvCountryList.setItemAnimator(new DefaultItemAnimator());

        mainViewModel = new ViewModelProvider(this).get(CountryDetailViewModel.class);
        countryAdapter = new CountryAdapter();
        binding.rvCountryList.setAdapter(countryAdapter);

        mSharePreference = Objects.requireNonNull(getActivity()).getSharedPreferences(COUNTRY_INFORMATION, Context.MODE_PRIVATE);

        getAllCountryInformation();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainViewModel.getDataFromServer(App.getInstance());
            }
        });
        return root;
    }

    @SuppressLint("NewApi")
    private void getAllCountryInformation() {

        mainViewModel.getAllPosts().observe(Objects.requireNonNull(getActivity()), new Observer<List<CountryTable>>() {
            @Override
            public void onChanged(@Nullable List<CountryTable> modelInformationList) {
                swipeRefreshLayout.setRefreshing(false);
                ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle(mSharePreference.getString(COUNTRY_NAME,""));


                countryAdapter.setCountryResponseList((ArrayList<CountryTable>) modelInformationList);
            }
        });
    }
}
