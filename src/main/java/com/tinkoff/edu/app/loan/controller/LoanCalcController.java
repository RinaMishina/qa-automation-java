package com.tinkoff.edu.app.loan.controller;

import com.tinkoff.edu.app.loan.service.ILoanCalcService;
import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.models.LoanResponse;

public class LoanCalcController {
    private final ILoanCalcService loanCalcService;

    public LoanCalcController(ILoanCalcService loanCalcService) {
        this.loanCalcService = loanCalcService;
    }

    public LoanResponse createRequest(LoanData request) {
        LoanServiceModel model = this.loanCalcService.createRequest(request);

        return new LoanResponse(model.getId(), model.getType(), request);
    }
}