package com.mw.xcode.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CurrencyNotFoundException extends RuntimeException{

    public CurrencyNotFoundException(String message) {
        super(message);
    }
}
