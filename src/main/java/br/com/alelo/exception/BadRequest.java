package br.com.alelo.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequest extends ExceptionAlelo {

    private static final long serialVersionUID = 1L;
    
    public BadRequest(final String message) {
        super(BAD_REQUEST, message);
    }
}
