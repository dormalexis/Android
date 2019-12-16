package com.example.smartcity.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartcity.DataAccess.ViewModel.ItemViewModel;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddItemFragment extends Fragment {

    @BindView(R.id.addItemName)
    EditText name;
    @BindView(R.id.addItemDescription)
    EditText description;
    @BindView(R.id.addItemPrice)
    EditText price;
    @BindView(R.id.addItemConfirmation)
    Button confirmation;

    ItemViewModel itemModel = new ItemViewModel();

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_additem,container,false);
        ButterKnife.bind(this, view);
        confirmation.setOnClickListener(confirmationListener);
        return view;
    }

    private View.OnClickListener confirmationListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Item item = new Item();
            item.setName(name.getText().toString());
            item.setDescription(description.getText().toString());
            item.setVisible(true);
            item.setPricePerDay(10.);
            item.setOwner(1);
            item.setItemCategory(1);
            itemModel.postItem(item);
        }
    };


}
