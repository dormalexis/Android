package com.example.smartcity.DataAccess.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcity.DataAccess.Repository.CategoryRepository;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewModel extends ViewModel {

    CategoryRepository categoryRepository;

    MutableLiveData<List<ItemCategory>> categories;

    public CategoryViewModel(Context context) {

        categoryRepository = new CategoryRepository(context);
    }

    public MutableLiveData<List<ItemCategory>> getCategories() {
        categories = categoryRepository.getCategories();
        return categories;
    }
}
