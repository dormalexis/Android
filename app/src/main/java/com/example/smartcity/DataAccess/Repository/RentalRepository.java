package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.ItemService;
import com.example.smartcity.DataAccess.Service.RentalService;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Rental;
import com.example.smartcity.Utilitaries.ApiCodeTrad;
import com.example.smartcity.Utilitaries.ApiResponseErrorCode;
import com.example.smartcity.Utilitaries.RetrofitInstance;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentalRepository implements RentalDataAccess {

    private MutableLiveData<ApiResponse<List<Rental>>> rentalsLive;
    private MutableLiveData<ApiResponse> rentalPost;
    private Context context;
    private InternetChecking internetChecking;

    public RentalRepository(Context context)
    {
        this.rentalsLive = new MutableLiveData<>();
        this.internetChecking = new InternetChecking(context);
        this.context = context;
    }

    public MutableLiveData<ApiResponse<List<Rental>>> getRentals() {
        if(!internetChecking.isNetworkAvailable()) {
            rentalsLive.setValue(new ApiResponse<>(ApiResponseErrorCode.NETWORKFAIL));
            return rentalsLive;
        }

        RentalService service = RetrofitInstance.getRetrofitInstance(context).create(RentalService.class);
        Call<List<Rental>> call = service.getRentals();
        call.enqueue(new Callback<List<Rental>>() {
            @Override
            public void onResponse(Call<List<Rental>> call, Response<List<Rental>> response) {
                if(response.isSuccessful())
                {
                    rentalsLive.setValue(new ApiResponse<>(response.body()));
                }
                else
                {
                    rentalsLive.setValue(new ApiResponse<>(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }

            }

            @Override
            public void onFailure(Call<List<Rental>> call, Throwable t) {
                rentalsLive.setValue(new ApiResponse<>(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return rentalsLive;
    }

    public MutableLiveData<ApiResponse<List<Rental>>> getValidations()
    {
        if(!internetChecking.isNetworkAvailable()) {
            rentalsLive.setValue(new ApiResponse<>(ApiResponseErrorCode.NETWORKFAIL));
            return rentalsLive;
        }

        RentalService service = RetrofitInstance.getRetrofitInstance(context).create(RentalService.class);
        Call<List<Rental>> call = service.getValidations();
        call.enqueue(new Callback<List<Rental>>() {
            @Override
            public void onResponse(Call<List<Rental>> call, Response<List<Rental>> response) {
                if(response.isSuccessful())
                {
                    rentalsLive.setValue(new ApiResponse<>(response.body()));
                }
                else
                {
                    rentalsLive.setValue(new ApiResponse<>(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }

            }

            @Override
            public void onFailure(Call<List<Rental>> call, Throwable t) {
                rentalsLive.setValue(new ApiResponse<>(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return rentalsLive;

    }

    public MutableLiveData<ApiResponse> postRental(Rental rental) {
        if(!internetChecking.isNetworkAvailable()) {
            rentalPost.setValue(new ApiResponse<>(ApiResponseErrorCode.NETWORKFAIL));
            return rentalPost;
        }
        RentalService service = RetrofitInstance.getRetrofitInstance(context).create(RentalService.class);
        Call<Void> call = service.postRental(rental);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response){
                if (response.isSuccessful()) {
                    rentalPost.setValue(new ApiResponse<>(response.body()));
                } else {
                    rentalPost.setValue(new ApiResponse<>(ApiCodeTrad.codeErrorToApiResponse(response.code())));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                rentalPost.setValue(new ApiResponse<>(ApiResponseErrorCode.SERVEURERROR));
            }
        });
        return rentalPost;
    }
}
