package pro.java.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter
public class IntegrationException extends RuntimeException {
    private final HttpStatusCode status;

    @Override
    public String getMessage() {
        return "Status: " + status;
    }
}
