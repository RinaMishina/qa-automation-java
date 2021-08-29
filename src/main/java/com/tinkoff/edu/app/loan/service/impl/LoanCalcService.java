package com.tinkoff.edu.app.loan.service.impl;

import com.tinkoff.edu.app.loan.repository.ILoanCalcRepository;
import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.types.LoanType;
import com.tinkoff.edu.app.loan.types.ResponseType;
import com.tinkoff.edu.app.loan.service.ILoanCalcService;

public class LoanCalcService implements ILoanCalcService {
    protected final ILoanCalcRepository loanCalcRepository;

    public LoanCalcService(ILoanCalcRepository loanCalcRepository) {
        this.loanCalcRepository = loanCalcRepository;
    }

    public LoanServiceModel createRequest(LoanData loanData) {
        int id = this.loanCalcRepository.save();
        ResponseType type = this.getType(loanData);

        return new LoanServiceModel(id, type);
    }

    private ResponseType getType(LoanData loanData) {
       if (loanData.getType().equals(LoanType.PERSON) & loanData.getMonth() <= 12 & loanData.getAmount() <= 10_000) {
           return ResponseType.APPROVED;
       }

       if (loanData.getType().equals(LoanType.PERSON) & loanData.getMonth() > 12 & loanData.getAmount() > 10_000) {
           return ResponseType.DECLINED;
       }

       if (loanData.getType().equals(LoanType.OOO) & loanData.getAmount() <= 10_000) {
           return ResponseType.DECLINED;
       }

       if (loanData.getType().equals(LoanType.OOO) & loanData.getAmount() > 10_000 & loanData.getMonth() >= 12) {
           return ResponseType.DECLINED;
       }

       if (loanData.getType().equals(LoanType.OOO) & loanData.getAmount() > 10_000 & loanData.getMonth() < 12) {
           return ResponseType.APPROVED;
       }

        return ResponseType.DECLINED;
    }
}
