package com.weekie.exception;

public class QuantityNotEnoughException extends BaseException{
    public QuantityNotEnoughException() {
    }

    public QuantityNotEnoughException(String msg) {
        super(msg);
    }
}
