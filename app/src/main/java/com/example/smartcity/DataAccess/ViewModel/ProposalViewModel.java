package com.example.smartcity.DataAccess.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.smartcity.DataAccess.Repository.ProposalDataAccess;
import com.example.smartcity.DataAccess.Repository.ProposalRepository;
import com.example.smartcity.Model.Proposal;

public class ProposalViewModel extends ViewModel {
    private ProposalDataAccess proposalDataAccess;

    public ProposalViewModel()
    {
        proposalDataAccess = new ProposalRepository(); //TODO ça fait du couplage
    }

    public void postProposal(Proposal proposal) {
        proposalDataAccess.postProposal(proposal);
    }
}
