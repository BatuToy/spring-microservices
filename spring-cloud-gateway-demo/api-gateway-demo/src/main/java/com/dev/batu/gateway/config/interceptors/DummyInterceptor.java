package com.dev.batu.gateway.config.interceptors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DummyInterceptor implements ClientHttpRequestInterceptor {

    private static final Log log = LogFactory.getLog(DummyInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("X-Request-ID", "2323");
        log.info(request.getURI().toString());
        return execution.execute(request, body);
    }
}
