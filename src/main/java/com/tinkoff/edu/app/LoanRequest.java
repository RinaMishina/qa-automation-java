package com.tinkoff.edu.app;

public class LoanRequest {
    public LoanType getType() {
        return type;
    }

    private final LoanType type;
    private final int months;
    private final int amount;

    public LoanRequest(LoanType type, int months, int amount) {
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

    public String toString() {
        return "request: {"
                + this.type + ", "
                + this.getAmount()
                + " for " + this.getMonth()
                + " months}";
    }
}
