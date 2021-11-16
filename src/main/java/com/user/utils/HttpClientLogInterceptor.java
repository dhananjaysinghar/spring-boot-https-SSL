package com.user.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.time.Instant;

@Slf4j
public class HttpClientLogInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        log.debug("HTTP Request URL: {}", request.getURI());
        log.debug("HTTP Method: {}", request.getMethod());
        long startTimeMillis = Instant.now().toEpochMilli();
        ClientHttpResponse response = execution.execute(request, body);
        long endTimeMillis = Instant.now().toEpochMilli();
        log.debug("HTTP Response time: {} milliseconds", (endTimeMillis - startTimeMillis));
        return response;
    }
}
