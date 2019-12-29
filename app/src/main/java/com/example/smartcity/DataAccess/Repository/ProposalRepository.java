package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import com.example.smartcity.DataAccess.InternetChecking;
import com.example.smartcity.DataAccess.Service.ProposalService;
import com.example.smartcity.Model.Proposal;
import com.example.smartcity.Utilitaries.RetrofitInstance;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProposalRepository implements ProposalDataAccess {

    private Context context;
    private InternetChecking internetChecking;
    public ProposalRepository(Context context)
    {

        this.context = context;
        this.internetChecking = new InternetChecking(context);
    }
    public void postProposal(Proposal proposal)
    {
        if(!internetChecking.isNetworkAvailable()) {} // Todo : Renvoie erreur pas de connection
        ProposalService service = RetrofitInstance.getRetrofitInstance(context).create(ProposalService.class);
        Call<Integer> call = service.postProposal(proposal);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response != null)
                {
                    Log.v("ONRESPONSE", new Gson().toJson(response.body()));
                }

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("ERROR", "Post failed");
            }
        });
    }
}
