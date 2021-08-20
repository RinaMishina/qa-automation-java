package com.tinkoff.edu.app.loan.service.impl;

import com.tinkoff.edu.app.loan.repository.ILoanCalcRepository;
import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.types.ResponseType;
import com.tinkoff.edu.app.loan.service.ILoanCalcService;

public class LoanCalcService implements ILoanCalcService {
    protected final ILoanCalcRepository loanCalcRepository;

    public LoanCalcService(ILoanCalcRepository loanCalcRepository) {
        this.loanCalcRepository = loanCalcRepository;
    }

    public LoanServiceModel createRequest(LoanData loanData) {
        int id = this.loanCalcRepository.save();

        return new LoanServiceModel(id, ResponseType.APPROVED);
    }
}
