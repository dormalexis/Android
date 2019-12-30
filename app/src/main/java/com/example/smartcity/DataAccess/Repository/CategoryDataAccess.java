package com.example.smartcity.DataAccess.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.ItemCategory;

import java.util.List;

public interface CategoryDataAccess {

    MutableLiveData<ApiResponse<List<ItemCategory>>> getCategories();
}
