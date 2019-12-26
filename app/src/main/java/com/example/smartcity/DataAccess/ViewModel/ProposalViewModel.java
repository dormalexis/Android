package com.example.smartcity.DataAccess.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.smartcity.DataAccess.Repository.ProposalDataAccess;
import com.example.smartcity.DataAccess.Repository.ProposalRepository;
import com.example.smartcity.Model.Proposal;

public class ProposalViewModel extends ViewModel {
    private ProposalDataAccess proposalDataAccess;

    public ProposalViewModel(Context context)
    {
        proposalDataAccess = new ProposalRepository(context); //TODO ça fait du couplage
    }

    public void postProposal(Proposal proposal) {
        proposalDataAccess.postProposal(proposal);
    }
}
