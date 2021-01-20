package com.example.codese_spring.eNUmm;

public enum State {
    ChuaThanhToan(0);
    private int code;

    State(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
