package com.tinkoff.edu.app.loan.models;

import com.tinkoff.edu.app.loan.types.LoanType;

public class LoanData {
    private final LoanType type;
    private final int months;
    private final int amount;

    public LoanData(LoanType type, int months, int amount) {
        this.type = type;
        this.months = months;
        this.amount = amount;
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


    public String toString() {
        return "request: {"
                + this.type + ", "
                + this.getAmount()
                + " for " + this.getMonth()
                + " months}";
    }
}
