package com.tinkoff.edu.app.loan.models;

import com.tinkoff.edu.app.loan.types.ResponseType;

public class LoanServiceModel {
    private final int id;
    private final ResponseType type;

    public LoanServiceModel(int id, ResponseType type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public ResponseType getType() {
        return type;
    }

}
