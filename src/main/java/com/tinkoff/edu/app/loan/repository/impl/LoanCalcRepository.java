package com.tinkoff.edu.app.loan.repository.impl;

import com.tinkoff.edu.app.loan.repository.ILoanCalcRepository;

public class LoanCalcRepository implements ILoanCalcRepository {
    private int requestId;

    public int save() {
        return ++requestId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

}