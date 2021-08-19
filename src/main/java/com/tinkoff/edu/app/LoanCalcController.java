package com.tinkoff.edu.app;

public class LoanCalcController {
    private final LoanCalcService loanCalcService;

    public LoanCalcController(LoanCalcService loanCalcService) {
        this.loanCalcService = loanCalcService;
    }

    public LoanResponse createRequest(LoanRequest request) {
        LoanModel model = this.loanCalcService.createRequest();

        return new LoanResponse(model.getId(), model.getType(), request);
    }
}