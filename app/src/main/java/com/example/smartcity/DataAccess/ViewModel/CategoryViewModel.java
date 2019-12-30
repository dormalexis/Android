package com.example.smartcity.DataAccess.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcity.DataAccess.Repository.CategoryDataAccess;
import com.example.smartcity.DataAccess.Repository.CategoryRepository;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewModel extends ViewModel {

    CategoryDataAccess categoryDataAccess;

    MutableLiveData<List<ItemCategory>> categories;

    public CategoryViewModel(Context context) {

        categoryDataAccess = new CategoryRepository(context);
    }

    public MutableLiveData<ApiResponse<List<ItemCategory>>> getCategories() {
        return categoryDataAccess.getCategories();
    }
}
