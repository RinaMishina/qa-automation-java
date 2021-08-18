package com.tinkoff.edu.app;

public class LoanModel {
    private final int id;
    private final ResponseType type;

    public LoanModel(int id, ResponseType type) {
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
