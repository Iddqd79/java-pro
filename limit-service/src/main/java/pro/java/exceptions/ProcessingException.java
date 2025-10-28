package pro.java.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ProcessingException extends RuntimeException {
    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
