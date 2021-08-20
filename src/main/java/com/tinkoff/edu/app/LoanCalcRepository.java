package com.tinkoff.edu.app;

public class LoanCalcRepository implements ILoanCalcRepository {
    private int requestId;

    public int save() {
        return ++requestId;
    }
}
