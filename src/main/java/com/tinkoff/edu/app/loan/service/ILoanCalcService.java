package com.tinkoff.edu.app.loan.service;

import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.models.LoanData;

public interface ILoanCalcService {
    LoanServiceModel createRequest(LoanData loanData);
}
