package com.tinkoff.edu.app.loan.models;

import com.tinkoff.edu.app.loan.types.LoanType;
import com.tinkoff.edu.app.loan.types.ResponseType;

import java.util.UUID;

public class LoanServiceModel {
    private final UUID id;
    private ResponseType type;
    private final String fio;
    private final LoanType loanType;

    public LoanServiceModel(UUID id, ResponseType type, String fio, LoanType loanType) {
        this.id = id;
        this.type = type;
        this.fio = fio;
        this.loanType = loanType;
    }

    public UUID getId() {
        return id;
    }

    public ResponseType getType() {
        return type;
    }

    public String getFio() {
        return fio;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }
}
