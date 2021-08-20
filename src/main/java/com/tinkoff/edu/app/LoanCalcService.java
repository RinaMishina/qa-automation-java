package com.tinkoff.edu.app;

public class LoanCalcService implements ILoanCalcService {
    protected final ILoanCalcRepository loanCalcRepository;

    public LoanCalcService(ILoanCalcRepository loanCalcRepository) {
        this.loanCalcRepository = loanCalcRepository;
    }

    public LoanModel createRequest(LoanRequest loanRequest) {
        int id = this.loanCalcRepository.save();

        return new LoanModel(id, ResponseType.APPROVED);
    }
}
