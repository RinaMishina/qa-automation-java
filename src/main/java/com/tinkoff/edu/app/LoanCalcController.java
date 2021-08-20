package com.tinkoff.edu.app;

public class LoanCalcController {
    private final ILoanCalcService loanCalcService;

    public LoanCalcController(ILoanCalcService loanCalcService) {
        this.loanCalcService = loanCalcService;
    }

    public LoanResponse createRequest(LoanRequest request) {
        LoanModel model = this.loanCalcService.createRequest(request);

        return new LoanResponse(model.getId(), model.getType(), request);
    }
}