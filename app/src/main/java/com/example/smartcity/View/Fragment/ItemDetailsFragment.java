package com.example.smartcity.View.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity.DataAccess.ViewModel.ProposalViewModel;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Proposal;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.GlideApp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ItemDetailsFragment extends Fragment {

    @BindView(R.id.titleItemDetailsText)
    TextView titleText;
    @BindView(R.id.detailsItemImage)
    ImageView detailsItemImage;
    @BindView(R.id.descriptionItemText)
    TextView descriptionItemText;
    @BindView(R.id.countImageText)
    TextView countImageText;
    @BindView(R.id.nextImageButton)
    ImageButton nextImageButton;
    @BindView(R.id.previousImageButton)
    ImageButton previousImageButton;
    @BindView(R.id.inputDateFrom)
    TextView inputDateFrom;
    @BindView(R.id.buttonDateFrom)
    Button buttonDateFrom;
    @BindView(R.id.inputTimeFrom)
    TextView inputTimeFrom;
    @BindView(R.id.buttonTimeFrom)
    Button buttonTimeFrom;
    @BindView(R.id.inputDateTo)
    TextView inputDateTo;
    @BindView(R.id.buttonDateTo)
    Button buttonDateTo;
    @BindView(R.id.inputTimeTo)
    TextView inputTimeTo;
    @BindView(R.id.buttonTimeTo)
    Button buttonTimeTo;
    @BindView(R.id.bookItemButton)
    Button bookItemButton;


    private Item itemSelected;
    private GregorianCalendar dateFrom;
    private GregorianCalendar dateTo;
    private ProposalViewModel proposalViewModel;

    public ItemDetailsFragment(Item itemSelected)
    {
        this.itemSelected = itemSelected;
        dateFrom = new GregorianCalendar();
        dateTo = new GregorianCalendar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);
        ButterKnife.bind(this,view);

        previousImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(true);
            }
        });
        nextImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(false);
            }
        });

        buttonDateFrom.setOnClickListener(onClickListenerDateSelector);
        buttonTimeFrom.setOnClickListener(onClickListenerTimeSelector);
        buttonDateTo.setOnClickListener(onClickListenerDateSelector);
        buttonTimeTo.setOnClickListener(onClickListenerTimeSelector);

        bookItemButton.setOnClickListener(onClickListenerBookItem);

        displayImage(0);
        changeTextImage(0,itemSelected.getPictures().size());

        titleText.setText(itemSelected.getDescription());

        return view;
    }

    private View.OnClickListener onClickListenerDateSelector = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int month, int day) {
                            String generateText = (day + "-" + (month + 1) + "-" + year);
                            if(v.getId() == R.id.buttonDateFrom)
                            {
                                inputDateFrom.setText(generateText);
                                dateFrom.set(year,month,day);
                            }
                            else
                            {
                                inputDateTo.setText(generateText);
                                dateTo.set(year,month,day);
                            }
                        }
                    }, year, month, day);
            datePickerDialog.show();
        }
    };

    private View.OnClickListener onClickListenerTimeSelector = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar calendar = Calendar.getInstance();
           int hour = calendar.get(Calendar.HOUR_OF_DAY);
           int minute = calendar.get(Calendar.MINUTE);


            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hour, int minute) {
                            java.util.Date date = new java.util.Date();
                            date.setMinutes(minute);
                            date.setHours(hour);
                            String generateText = (hour + " : " + (minute < 10 ? "0":"") + minute);
                            if(v.getId() == R.id.buttonTimeFrom)
                            {

                                dateFrom.setTime(date);
                                inputTimeFrom.setText(generateText);
                            }
                            else
                            {
                                dateTo.setTime(date);
                                inputTimeTo.setText(generateText);
                            }
                        }
                    }, hour, minute,true);
            timePickerDialog.show();
        }
    };

    private View.OnClickListener onClickListenerBookItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Proposal proposal = new Proposal();
            proposalViewModel = new ProposalViewModel(getContext());
            proposal.setMessage("Blabla");
            proposal.setDateFrom(dateFrom);
            proposal.setDateTo(dateTo);
            proposal.setRentalId(1);
            proposalViewModel.postProposal(proposal);
        }
    };

    private void changeImage(Boolean isPrevious)
    {
        int nbImages = itemSelected.getPictures().size();
        int iImage = 0;
        Log.d(TAG, "changeImage: " + iImage);

        if(isPrevious && iImage != 0)
        {
            iImage--;
        }
        if(!isPrevious && iImage < nbImages)
        {
            iImage++;
        }
        changeTextImage(iImage,nbImages);
        displayImage(iImage);
    }

    private void changeTextImage(int iImage, int nbImage)
    {
        countImageText.setText((iImage + 1)  + " / " + nbImage);
    }

    private void displayImage(int iImage)
    {
        if(itemSelected.getPictures().size() > 0)
        {
            Context context = getContext();
            RequestOptions requestOptions = new RequestOptions();
            // TODO : Modifier les drawable montrés par défaut en cas de non chargement de l'image
            requestOptions.placeholder(R.drawable.ic_add);
            requestOptions.error(R.drawable.ic_check);

            GlideApp.with(context)
                    .load(itemSelected.getPictures().get(iImage).getPath())
                    .apply(requestOptions)
                    .override(100, 200)
                    .into(detailsItemImage);
        }

    }





}
