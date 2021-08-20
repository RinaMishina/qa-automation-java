package com.tinkoff.edu.app;

public class IpNotFriendlyService extends LoanCalcService {
    public IpNotFriendlyService(LoanCalcRepository loanCalcRepository) {
        super(loanCalcRepository);
    }

    @Override
    public LoanModel createRequest (LoanRequest loanRequest) {
        if (loanRequest.getType().equals(LoanType.IP)) return new LoanModel(-1, ResponseType.DENIED);

        int id = this.loanCalcRepository.save();
        return new LoanModel(id, ResponseType.APPROVED);
    }
}
