package com.example.smartcity.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.smartcity.Model.IntervalDates;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Rental;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.DatesUtilitaries;
import com.example.smartcity.View.RecyclerView.ImageAdapter;
import com.example.smartcity.View.RecyclerView.ReviewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemDetailsFragment extends Fragment implements ReviewAdapter.OnItemListener {

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

    @BindView(R.id.commentsRV)
    RecyclerView recyclerView;

    @BindView(R.id.bookButton)
    Button bookButton;

    @BindView(R.id.back_button)
    FloatingActionButton backButton;

    Date start = new Date();
    Date end = new Date();

    private ReviewAdapter adapter;

    private ArrayList<IntervalDates> invalidDates = new ArrayList<>();

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

        for (Rental rental : itemSelected.getRentals()) {
            IntervalDates intervalDates = new IntervalDates(rental.getDateFrom(), rental.getDateTo());
            invalidDates.add(intervalDates);
        }

    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        adapter = new ReviewAdapter(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        BottomNavigationView bottomBar = getActivity().findViewById(R.id.bottom_navigation);
        bottomBar.setVisibility(View.GONE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        BottomNavigationView bottomBar = getActivity().findViewById(R.id.bottom_navigation);
        bottomBar.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);
        ButterKnife.bind(this, view);

        ImageAdapter adapterView = new ImageAdapter(itemSelected);
        viewPager.setAdapter(adapterView);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        itemDetailsIitle.setText(itemSelected.getTitle());
        itemDetailsDescription.setText(itemSelected.getDescription());
        ratingBar.setRating(itemSelected.getNbStars());
        rateNumber.setText(itemSelected.getNbAvis() + " avis");
        itemDetailsPrice.setText("Prix par jour : " + itemSelected.getPricePerDay());
        itemDetailsOwnerIdentity.setText(itemSelected.getOwnerFirstName() + " " + itemSelected.getOwnerName());


        /*
        if(itemSelected.getRentals().size() > 0) {
            itemDetailsRenterIdentity.setText("Alexis Dormal");
            itemsDetailsRenterRating.setRating(itemSelected.getRentals().get(0).getStarsNumber());
            itemDetailsComment.setText(itemSelected.getRentals().get(0).getComment());
        }
         */

        adapter.setItems(itemSelected.getReviews());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        bookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.dateRangePicker();
                CalendarConstraints.DateValidator dateValidator = new CalendarConstraints.DateValidator() {
                    @Override
                    public boolean isValid(long date) {
                        if (DatesUtilitaries.isPastDate(date)) return false;
                        for (IntervalDates i : invalidDates) {
                            if (i.isInInterval(date)) return false;
                        }
                        return true;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {

                    }

                };
                CalendarConstraints.Builder calendarBuilder = new CalendarConstraints.Builder();
                calendarBuilder.setValidator(dateValidator);
                CalendarConstraints calendarConstraints = calendarBuilder.build();

                builder.setCalendarConstraints(calendarConstraints);
                builder.setTitleText("Test");
                MaterialDatePicker picker = builder.build();
                picker.setHasOptionsMenu(false);
                picker.show(getFragmentManager(), picker.toString());
                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
                    @Override
                    public void onPositiveButtonClick(Pair<Long, Long> selection) {
                        start.setTime(selection.first);
                        end.setTime(selection.second);

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, new ConfirmRentalFragment(start, end, itemSelected));
                        transaction.addToBackStack(this.getClass().getName());
                        transaction.commit();
                    }
                });
            }
        });

        return view;

    }

    @Override
    public void onItemClick(int position) {

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
