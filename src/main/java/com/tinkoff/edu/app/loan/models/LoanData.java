package com.tinkoff.edu.app.loan.models;

import com.tinkoff.edu.app.loan.types.LoanType;

public class LoanData {
    private final LoanType type;
    private final int months;
    private final int amount;
    private final String fio;

    public LoanData(LoanType type, int months, int amount, String fio) {
        this.type = type;
        this.months = months;
        this.amount = amount;
        this.fio = fio;
    }

    public int getMonth() {
        return months;
    }

    public int getAmount() {
        return amount;
    }

    public LoanType getType() {
        return type;
    }

    public String getFio() {
        return fio;
    }

}
