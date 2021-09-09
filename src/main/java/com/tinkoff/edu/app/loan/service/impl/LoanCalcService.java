package com.tinkoff.edu.app.loan.service.impl;

import com.tinkoff.edu.app.loan.repository.ILoanCalcRepository;
import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.types.LoanType;
import com.tinkoff.edu.app.loan.types.ResponseType;
import com.tinkoff.edu.app.loan.service.ILoanCalcService;

import java.util.List;
import java.util.UUID;

public class LoanCalcService implements ILoanCalcService {
    protected final ILoanCalcRepository loanCalcRepository;

    public LoanCalcService(ILoanCalcRepository loanCalcRepository) {
        this.loanCalcRepository = loanCalcRepository;
    }

    public LoanServiceModel createRequest(LoanData loanData) throws AmountException {
        this.verifyLoanData(loanData);
        ResponseType type = this.getType(loanData);

        try {
            return this.loanCalcRepository.save(loanData, type);
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    private void verifyLoanData(LoanData loanData) throws AmountException {
        if (loanData == null) {
            throw new IllegalArgumentException("request is empty");
        }

        if (loanData.getMonth() <= 0 || loanData.getMonth() >= 100) {
            throw new IllegalArgumentException("months in request are invalid");
        }

        if (loanData.getAmount() <= 0 || loanData.getAmount() > 1_000_000) {
            throw new AmountException("amount in request is invalid");
        }

        if (loanData.getFio() == null) {
            throw new IllegalArgumentException("fio is empty");
        }

        if (loanData.getFio().length() < 10 || loanData.getFio().length() > 100) {
            throw new IllegalArgumentException("fio not in length range");
        }

        if (!loanData.getFio().matches("[ а-яА-Я-]+")) {
            throw new IllegalArgumentException("fio contains prohibited symbols");
        }
    }

    public LoanServiceModel getLoanModelById(UUID id) {
        return this.loanCalcRepository.getById(id);
    }

    public LoanServiceModel updateTypeById(UUID id, ResponseType type) {
        return this.loanCalcRepository.updateType(id, type);
    }

    public List<LoanServiceModel> getAllByLoanType(LoanType loanType) {
        return this.loanCalcRepository.getAllByLoanType(loanType);
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
