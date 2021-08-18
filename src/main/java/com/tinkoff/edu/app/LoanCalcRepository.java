package com.tinkoff.edu.app;

public class LoanCalcRepository {

    private int requestId;

    public int save() {
        return ++requestId;
    }
}
