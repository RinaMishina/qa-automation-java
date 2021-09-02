package com.tinkoff.edu.app.loan.service;

import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.types.ResponseType;

import java.util.UUID;

public interface ILoanCalcService {
    LoanServiceModel createRequest(LoanData loanData);
    LoanServiceModel getLoanModelById(UUID id);
    LoanServiceModel updateTypeById(UUID id, ResponseType type);
}
