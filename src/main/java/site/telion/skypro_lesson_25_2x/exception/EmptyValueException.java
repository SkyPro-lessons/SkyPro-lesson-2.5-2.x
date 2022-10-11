package site.telion.skypro_lesson_25_2x.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyValueException extends RuntimeException {
    public EmptyValueException() {
    }

    public EmptyValueException(String message) {
        super(message);
    }

    public EmptyValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyValueException(Throwable cause) {
        super(cause);
    }

    public EmptyValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
