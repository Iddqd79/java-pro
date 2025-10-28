package pro.java.clients;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.springframework.http.HttpStatusCode;
import pro.java.exceptions.IntegrationException;

public class ExecutorFeignClientErrorDecoder implements ErrorDecoder {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExecutorFeignClientErrorDecoder.class);


    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 500) {
            throw new IntegrationException(HttpStatusCode.valueOf(response.status()));
        }
        return new Default().decode(s, response);
    }
}
