package com.example.smartcity.DataAccess.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.RentalService;
import com.example.smartcity.Model.ApiResponse;
import com.example.smartcity.Model.Rental;
import com.example.smartcity.Model.RentalDTO;
import com.example.smartcity.Utilitaries.RetrofitInstance;
import com.example.smartcity.Utilitaries.StatusCode;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.smartcity.Utilitaries.App.getContext;

public class RentalRepository implements RentalDataAccess {

    private MutableLiveData<ApiResponse<List<RentalDTO>>> rentalsRenterHistoric;
    private MutableLiveData<ApiResponse<List<RentalDTO>>> rentalsRenterInProgress;
    private MutableLiveData<ApiResponse<List<RentalDTO>>> rentalsOwnerHistoric;
    private MutableLiveData<ApiResponse<List<RentalDTO>>> rentalsOwnerInProgress;
    private MutableLiveData<ApiResponse<List<RentalDTO>>> rentals;
    private MutableLiveData<ApiResponse> rentalValidated;
    private MutableLiveData<ApiResponse> rentalPost;
    private InternetChecking internetChecking;

    public RentalRepository()
    {
        this.rentalsRenterHistoric = new MutableLiveData<>();
        this.rentalsRenterInProgress = new MutableLiveData<>();
        this.rentalsOwnerHistoric = new MutableLiveData<>();
        this.rentalsOwnerInProgress = new MutableLiveData<>();
        this.rentalValidated =  new MutableLiveData<>();

        this.rentalPost = new MutableLiveData<>();
        this.internetChecking = new InternetChecking();
    }

    public MutableLiveData<ApiResponse<List<RentalDTO>>> getRentalsRenterHistoric() {
        if(!internetChecking.isNetworkAvailable()) {
            rentalsRenterHistoric.setValue(new ApiResponse(StatusCode.NETWORKFAIL));
            return rentalsRenterHistoric;
        }

        RentalService service = RetrofitInstance.getRetrofitInstance(getContext()).create(RentalService.class);
        Call<List<RentalDTO>> call = service.getRentalsRenterHistoric();
        call.enqueue(new Callback<List<RentalDTO>>() {
            @Override
            public void onResponse(Call<List<RentalDTO>> call, Response<List<RentalDTO>> response) {
                if(response.isSuccessful())
                {
                    rentalsRenterHistoric.setValue(new ApiResponse(response.body()));
                }
                else
                {
                    rentalsRenterHistoric.setValue(new ApiResponse(response.code()));
                }

            }

            @Override
            public void onFailure(Call<List<RentalDTO>> call, Throwable t) {
                rentalsRenterHistoric.setValue(new ApiResponse(StatusCode.INTERNALSERVERERROR));
            }
        });
        return rentalsRenterHistoric;
    }

    public MutableLiveData<ApiResponse<List<RentalDTO>>> getRentalsRenterInProgress() {
        if(!internetChecking.isNetworkAvailable()) {
            rentalsOwnerInProgress.setValue(new ApiResponse(StatusCode.NETWORKFAIL));
            return rentalsOwnerInProgress;
        }

        RentalService service = RetrofitInstance.getRetrofitInstance(getContext()).create(RentalService.class);
        Call<List<RentalDTO>> call = service.getRentalsRenterInProgress();
        call.enqueue(new Callback<List<RentalDTO>>() {
            @Override
            public void onResponse(Call<List<RentalDTO>> call, Response<List<RentalDTO>> response) {
                if(response.isSuccessful())
                {
                    rentalsRenterInProgress.setValue(new ApiResponse(response.body()));
                }
                else
                {
                    rentalsRenterInProgress.setValue(new ApiResponse(response.code()));
                }

            }

            @Override
            public void onFailure(Call<List<RentalDTO>> call, Throwable t) {
                rentalsRenterInProgress.setValue(new ApiResponse(StatusCode.INTERNALSERVERERROR));
            }
        });
        return rentalsRenterInProgress;
    }

    public MutableLiveData<ApiResponse<List<RentalDTO>>> getRentalsOwnerHistoric() {
        if(!internetChecking.isNetworkAvailable()) {
            rentalsOwnerHistoric.setValue(new ApiResponse(StatusCode.NETWORKFAIL));
            return rentalsOwnerHistoric;
        }

        RentalService service = RetrofitInstance.getRetrofitInstance(getContext()).create(RentalService.class);
        Call<List<RentalDTO>> call = service.getRentalsOwnerHistoric();
        call.enqueue(new Callback<List<RentalDTO>>() {
            @Override
            public void onResponse(Call<List<RentalDTO>> call, Response<List<RentalDTO>> response) {
                if(response.isSuccessful())
                {
                    rentalsOwnerHistoric.setValue(new ApiResponse(response.body()));
                }
                else
                {
                    rentalsOwnerHistoric.setValue(new ApiResponse(response.code()));
                }

            }

            @Override
            public void onFailure(Call<List<RentalDTO>> call, Throwable t) {
                rentalsOwnerHistoric.setValue(new ApiResponse(StatusCode.INTERNALSERVERERROR));
            }
        });
        return rentalsOwnerHistoric;
    }

    public MutableLiveData<ApiResponse<List<RentalDTO>>> getRentals() {
        if(!internetChecking.isNetworkAvailable()) {
            rentals.setValue(new ApiResponse(StatusCode.NETWORKFAIL));
            return rentals;
        }

        RentalService service = RetrofitInstance.getRetrofitInstance(getContext()).create(RentalService.class);
        Call<List<RentalDTO>> call = service.getRentals();
        call.enqueue(new Callback<List<RentalDTO>>() {
            @Override
            public void onResponse(Call<List<RentalDTO>> call, Response<List<RentalDTO>> response) {
                if(response.isSuccessful())
                {
                    rentals.setValue(new ApiResponse(response.body()));
                }
                else
                {
                    rentals.setValue(new ApiResponse(response.code()));
                }

            }

            @Override
            public void onFailure(Call<List<RentalDTO>> call, Throwable t) {
                rentals.setValue(new ApiResponse(StatusCode.INTERNALSERVERERROR));
            }
        });
        return rentals;
    }

    public MutableLiveData<ApiResponse<List<RentalDTO>>> getRentalsOwnerInProgress() {
        if(!internetChecking.isNetworkAvailable()) {
            rentalsOwnerInProgress.setValue(new ApiResponse(StatusCode.NETWORKFAIL));
            return rentalsOwnerInProgress;
        }

        RentalService service = RetrofitInstance.getRetrofitInstance(getContext()).create(RentalService.class);
        Call<List<RentalDTO>> call = service.getRentalsOwnerInProgress();
        call.enqueue(new Callback<List<RentalDTO>>() {
            @Override
            public void onResponse(Call<List<RentalDTO>> call, Response<List<RentalDTO>> response) {
                if(response.isSuccessful())
                {
                    rentalsOwnerInProgress.setValue(new ApiResponse(response.body()));
                }
                else
                {
                    rentalsOwnerInProgress.setValue(new ApiResponse(response.code()));
                }

            }

            @Override
            public void onFailure(Call<List<RentalDTO>> call, Throwable t) {
                rentalsOwnerInProgress.setValue(new ApiResponse(StatusCode.INTERNALSERVERERROR));
            }
        });
        return rentalsOwnerInProgress;
    }

    public MutableLiveData<ApiResponse> postRental(Rental rental) {
        if(!internetChecking.isNetworkAvailable()) {
            rentalPost.setValue(new ApiResponse(StatusCode.NETWORKFAIL));
            return rentalPost;
        }
        RentalService service = RetrofitInstance.getRetrofitInstance(getContext()).create(RentalService.class);
        Call<RentalDTO> call = service.postRental(rental);

        call.enqueue(new Callback<RentalDTO>() {
            @Override
            public void onResponse(Call<RentalDTO> call, Response<RentalDTO> response){
                if (response.isSuccessful()) {
                    rentalPost.setValue(new ApiResponse(response.body()));
                } else {
                    rentalPost.setValue(new ApiResponse(response.code()));
                }
            }

            @Override
            public void onFailure(Call<RentalDTO> call, Throwable t) {
                rentalPost.setValue(new ApiResponse(StatusCode.INTERNALSERVERERROR));
            }
        });
        return rentalPost;
    }

    public MutableLiveData<ApiResponse> validRental(int id, boolean isValid) {
        if(!internetChecking.isNetworkAvailable()) {
            rentalValidated.setValue(new ApiResponse(StatusCode.NETWORKFAIL));
            return rentalValidated;
        }
        RentalService service = RetrofitInstance.getRetrofitInstance(getContext()).create(RentalService.class);
        Call<Void> call = service.validRental(id, isValid);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response){
                if (response.isSuccessful()) {
                    rentalValidated.setValue(new ApiResponse(response.body()));
                } else {
                    Log.i("Alexis", "" + response.code());
                    rentalValidated.setValue(new ApiResponse(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                rentalValidated.setValue(new ApiResponse(StatusCode.INTERNALSERVERERROR));
            }
        });
        return rentalValidated;
    }
}
