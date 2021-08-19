package com.tinkoff.edu.app;

public class LoanCalcService {
    private final LoanCalcRepository loanCalcRepository;

    public LoanCalcService(LoanCalcRepository loanCalcRepository) {
        this.loanCalcRepository = loanCalcRepository;
    }

    public LoanModel createRequest() {
        int id = this.loanCalcRepository.save();

        return new LoanModel(id, ResponseType.APPROVED);
    }
}
