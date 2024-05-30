package kg.booster.rental_service.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BadParamsException extends RuntimeException {

    public BadParamsException(String message) {
        super(message);
    }

}
