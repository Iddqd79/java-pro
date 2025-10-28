package pro.java.clients;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;

public class ExecutorFeignClientInterceptor implements RequestInterceptor {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExecutorFeignClientInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("FeignClient: {} {}", requestTemplate.method(), requestTemplate.url());
    }
}
