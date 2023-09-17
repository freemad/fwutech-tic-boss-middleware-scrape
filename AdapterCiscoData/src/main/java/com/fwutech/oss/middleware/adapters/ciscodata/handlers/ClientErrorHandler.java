package com.fwutech.oss.middleware.adapters.ciscodata.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class ClientErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode().is4xxClientError()
                || clientHttpResponse.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        // TODO: 5/17/21 modify log to be handled in aspect
        log.error(ClientErrorHandler.class.getCanonicalName() + " | HTTP Status Code: " + clientHttpResponse.getStatusCode().value());
    }
}