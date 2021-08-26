package com.tinkoff.edu.app.loan.repository.impl;

import com.tinkoff.edu.app.loan.repository.ILoanCalcRepository;

public class LoanCalcRepository implements ILoanCalcRepository {
    private static int requestId;

    public int save() {
        return ++requestId;
    }

    public static void setRequestId(int requestId) {
        LoanCalcRepository.requestId = requestId;
    }

    public static int getRequestId() {
        return requestId;
    }


}
