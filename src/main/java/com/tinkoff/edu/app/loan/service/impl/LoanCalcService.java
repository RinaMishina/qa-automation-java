package com.tinkoff.edu.app.loan.service.impl;

import com.tinkoff.edu.app.loan.repository.ILoanCalcRepository;
import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.types.LoanType;
import com.tinkoff.edu.app.loan.types.ResponseType;
import com.tinkoff.edu.app.loan.service.ILoanCalcService;

import java.util.UUID;

public class LoanCalcService implements ILoanCalcService {
    protected final ILoanCalcRepository loanCalcRepository;

    public LoanCalcService(ILoanCalcRepository loanCalcRepository) {
        this.loanCalcRepository = loanCalcRepository;
    }

    public LoanServiceModel createRequest(LoanData loanData) {
        ResponseType type = this.getType(loanData);

        try {
            return this.loanCalcRepository.save(loanData, type);
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    public LoanServiceModel getLoanModelById(UUID id) {
        return this.loanCalcRepository.getById(id);
    }

    public LoanServiceModel updateTypeById(UUID id, ResponseType type) {
        return this.loanCalcRepository.updateType(id, type);
    }

    private ResponseType getType(LoanData loanData) {
       switch (loanData.getType()) {
           case IP:
               return getIpStatus();
           case OOO:
               return getOooStatus(loanData);
           case PERSON:
               return getPersonStatus(loanData);
           default:
               return ResponseType.DECLINED;
       }
    }

    private ResponseType getPersonStatus(LoanData loanData) {
        if (loanData.getType().equals(LoanType.PERSON) & loanData.getMonth() <= 12 & loanData.getAmount() <= 10_000) {
            return ResponseType.APPROVED;
        }
        return ResponseType.DECLINED;
    }

    private ResponseType getOooStatus(LoanData loanData) {
        if (loanData.getType().equals(LoanType.OOO) & loanData.getAmount() > 10_000 & loanData.getMonth() < 12) {
            return ResponseType.APPROVED;
        }
        return ResponseType.DECLINED;
    }

    private ResponseType getIpStatus() {
        return ResponseType.DECLINED;
    }
}
