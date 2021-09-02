package com.tinkoff.edu.app.loan.repository;

import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.types.ResponseType;

import java.util.UUID;

public interface ILoanCalcRepository {
    LoanServiceModel save(LoanData loanData, ResponseType responseType);
    LoanServiceModel getById(UUID id);
    LoanServiceModel updateType(UUID id, ResponseType type);
}
