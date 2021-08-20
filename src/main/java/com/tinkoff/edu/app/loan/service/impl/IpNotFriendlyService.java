package com.tinkoff.edu.app.loan.service.impl;

import com.tinkoff.edu.app.loan.models.LoanData;
import com.tinkoff.edu.app.loan.models.LoanServiceModel;
import com.tinkoff.edu.app.loan.repository.impl.LoanCalcRepository;
import com.tinkoff.edu.app.loan.types.LoanType;
import com.tinkoff.edu.app.loan.types.ResponseType;

public class IpNotFriendlyService extends LoanCalcService {
    public IpNotFriendlyService(LoanCalcRepository loanCalcRepository) {
        super(loanCalcRepository);
    }

    @Override
    public LoanServiceModel createRequest(LoanData loanData) {
        if (loanData.getType().equals(LoanType.IP)) {
            return new LoanServiceModel(-1, ResponseType.DENIED);
        }

        int id = this.loanCalcRepository.save();

        return new LoanServiceModel(id, ResponseType.APPROVED);
    }
}
