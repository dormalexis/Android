package com.example.smartcity.DataAccess.Service;

import com.example.smartcity.Model.Proposal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProposalService {

    @POST("Proposal")
    Call<Integer> postProposal(@Body Proposal proposal);
}
