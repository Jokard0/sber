package com.jokard0.sber.exception;

public class CarAlreadyParkedException extends RuntimeException {
    public CarAlreadyParkedException(String message) {
        super(message);
    }
}
