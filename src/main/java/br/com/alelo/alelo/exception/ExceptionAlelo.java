package br.com.alelo.alelo.exception;

import java.io.Serializable;

import br.com.alelo.alelo.enums.ExceptionsMessagesEnum;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionAlelo extends RuntimeException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String message;
    private HttpStatus status;

    public ExceptionAlelo(HttpStatus status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public static void checkThrow(final boolean expression, final ExceptionsMessagesEnum exceptionsMessagesEnum) {
        if (expression) {
            exceptionsMessagesEnum.raise();
        }
    }

}
