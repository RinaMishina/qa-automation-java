package com.tinkoff.edu.app.loan.controller;

import com.tinkoff.edu.app.loan.service.ILoanCalcService;
import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.models.LoanResponse;
import com.tinkoff.edu.app.loan.service.impl.AmountException;
import com.tinkoff.edu.app.loan.types.ResponseType;

import java.util.UUID;

public class LoanCalcController {
    private final ILoanCalcService loanCalcService;

    public LoanCalcController(ILoanCalcService loanCalcService) {
        this.loanCalcService = loanCalcService;
    }

    public LoanResponse createRequest(LoanData request) {
        try {
            LoanServiceModel model = this.loanCalcService.createRequest(request);

            return new LoanResponse(model.getId(), model.getType());
        } catch (Exception message) {
            return new LoanResponse(null, ResponseType.DECLINED);
        }
    }

    public LoanResponse getById(UUID id) {
        LoanServiceModel model = this.loanCalcService.getLoanModelById(id);

        if (model == null) {
            return new LoanResponse(null, ResponseType.DECLINED);
        }

        return new LoanResponse(model.getId(), model.getType());
    }

    public LoanResponse updateById(UUID id, ResponseType type) {
        LoanServiceModel model = this.loanCalcService.updateTypeById(id, type);

        if (model == null) {
            return new LoanResponse(null, ResponseType.DECLINED);
        }

        return new LoanResponse(model.getId(), model.getType());
    }
}