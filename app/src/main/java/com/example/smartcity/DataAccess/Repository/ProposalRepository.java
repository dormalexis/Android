package com.example.smartcity.DataAccess.Repository;

import android.content.Context;
import android.util.Log;

import com.example.smartcity.DataAccess.Service.ProposalService;
import com.example.smartcity.Model.Proposal;
import com.example.smartcity.Utilitaries.RetrofitInstance;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProposalRepository implements ProposalDataAccess {
    public void postProposal(Proposal proposal)
    {
        ProposalService service = RetrofitInstance.getRetrofitInstance().create(ProposalService.class);
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
