package com.example.smartcity.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.smartcity.Model.Item;
import java.util.ArrayList;

public interface ItemDataAccess {
    ArrayList<Item> getAllItems();
}
