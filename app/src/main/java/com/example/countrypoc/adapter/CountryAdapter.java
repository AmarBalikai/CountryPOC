package com.example.countrypoc.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.countrypoc.R;
import com.example.countrypoc.databinding.ItemLayoutBinding;
import com.example.countrypoc.room.CountryTable;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

  private ArrayList<CountryTable> countryResponseList;

  @NonNull
  @Override
  public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    ItemLayoutBinding Binding =
        DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
            R.layout.item_layout, viewGroup, false);
    return new CountryViewHolder(Binding);
  }

  @Override
  public void onBindViewHolder(@NonNull CountryViewHolder countryViewHolder, int i) {
    CountryTable modelInformation = countryResponseList.get(i);
    countryViewHolder.employeeListItemBinding.setData(modelInformation);
  }

  @Override
  public int getItemCount() {
    if (countryResponseList != null) {
      return countryResponseList.size();
    } else {
      return 0;
    }
  }

  public void setCountryResponseList(ArrayList<CountryTable> countryResponseList) {
    this.countryResponseList = countryResponseList;
    notifyDataSetChanged();
  }

  class CountryViewHolder extends RecyclerView.ViewHolder {

    private ItemLayoutBinding employeeListItemBinding;

    public CountryViewHolder(@NonNull ItemLayoutBinding employeetListItemBinding) {
      super(employeetListItemBinding.getRoot());
      this.employeeListItemBinding = employeetListItemBinding;
    }
  }
}
