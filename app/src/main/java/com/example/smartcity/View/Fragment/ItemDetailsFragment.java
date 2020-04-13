package com.example.smartcity.View.Fragment;
import android.content.Context;
import android.media.Rating;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity.Components.Text;
import com.example.smartcity.MainActivity;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.GlideApp;
import com.example.smartcity.View.RecyclerView.ImageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemDetailsFragment extends Fragment {

    private Item itemSelected;

    @BindView(R.id.viewPage)
    ViewPager viewPager;

    @BindView(R.id.itemDetailsIitle)
    TextView itemDetailsIitle;

    @BindView(R.id.itemDetailsPrice)
    TextView itemDetailsPrice;

    @BindView(R.id.itemDetailsOwnerIdentity)
    TextView itemDetailsOwnerIdentity;

    @BindView(R.id.itemDetailsDescription)
    TextView itemDetailsDescription;

    @BindView(R.id.itemDetailsRating)
    RatingBar ratingBar;

    @BindView(R.id.itemDetailsRateNumber)
    TextView rateNumber;

    @BindView(R.id.itemDetailsRenterIdentity)
    TextView itemDetailsRenterIdentity;

    @BindView(R.id.itemsDetailsRenterRating)
    RatingBar itemsDetailsRenterRating;

    @BindView(R.id.itemDetailsComment)
    TextView itemDetailsComment;

    /*

    @BindView(R.id.phoneNumber)
    TextView phoneNumber;

    @BindView(R.id.email)
    TextView email;
     */

    // Voir les disponibilités
    // Ajouter la demande de réservation

    public ItemDetailsFragment(Item itemSelected) {
        this.itemSelected = itemSelected;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);
        ButterKnife.bind(this, view);

        ImageAdapter adapterView = new ImageAdapter(itemSelected);
        viewPager.setAdapter(adapterView);

        itemDetailsIitle.setText(itemSelected.getTitle());
        itemDetailsDescription.setText(itemSelected.getDescription());
        ratingBar.setRating(itemSelected.getNbStars());
        rateNumber.setText(String.valueOf(itemSelected.getNbAvis() + " avis"));
        itemDetailsPrice.setText("Prix par jour : " + itemSelected.getPricePerDay());
        itemDetailsOwnerIdentity.setText(itemSelected.getOwnerFirstName() + " " + itemSelected.getOwnerName());

        if(itemSelected.getRentals().size() > 0) {
            itemDetailsRenterIdentity.setText("Alexis Dormal");
            itemsDetailsRenterRating.setRating(itemSelected.getRentals().get(0).getStarsNumber());
            itemDetailsComment.setText(itemSelected.getRentals().get(0).getComment());
        }

        return view;

    }





}


    /*
    private void displayImage(int iImage) {
        if (itemSelected.getPictures().size() > 0) {
            Context context = getContext();
            RequestOptions requestOptions = new RequestOptions().error(R.drawable.ic_exclamation_point);


            GlideApp.with(context)
                    .load(itemSelected.getPictures().get(iImage).getPath())
                    .apply(requestOptions)
                    .into(detailsItemImage);
        }



        }
    }

     */
