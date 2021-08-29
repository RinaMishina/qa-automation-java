package com.tinkoff.edu.app.loan.controller;

import com.tinkoff.edu.app.loan.service.ILoanCalcService;
import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.models.LoanResponse;
import com.tinkoff.edu.app.loan.types.LoanType;
import com.tinkoff.edu.app.loan.types.ResponseType;

public class LoanCalcController {
    private final ILoanCalcService loanCalcService;

    public LoanCalcController(ILoanCalcService loanCalcService) {
        this.loanCalcService = loanCalcService;
    }

    public LoanResponse createRequest(LoanData request) {
        if (request == null) {
            return new LoanResponse(-1, ResponseType.DECLINED);
        }

        if (request.getMonth() <= 0) {
            return new LoanResponse(-1, ResponseType.DECLINED);
        }

        if (request.getAmount() <= 0) {
            return new LoanResponse(-1, ResponseType.DECLINED);
        }

        LoanServiceModel model = this.loanCalcService.createRequest(request);

        return new LoanResponse(model.getId(), model.getType());
    }
}