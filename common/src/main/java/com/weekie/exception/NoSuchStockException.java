package com.weekie.exception;

public class NoSuchStockException extends BaseException{
    public NoSuchStockException() {
    }

    public NoSuchStockException(String msg) {
        super(msg);
    }
}
