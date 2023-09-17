package com.fwutech.oss.middleware.adapters.huaweievswitch.configs;

import com.fwutech.oss.middleware.adapters.huaweievswitch.handlers.ClientErrorHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestTemplateConfig {

    @Value("${fwutech.oss.middleware.adapters.huawei-ev-switch.connect.host-url}")
    private String hostUrl;
    @Value("${fwutech.oss.middleware.adapters.huawei-ev-switch.connect.username}")
    private String username;
    @Value("${fwutech.oss.middleware.adapters.huawei-ev-switch.connect.password}")
    private String password;
    private final CloseableHttpClient httpClient;

    @Autowired
    public RestTemplateConfig(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .requestFactory(this::clientHttpRequestFactory)
                .errorHandler(new ClientErrorHandler())
                .build();
    }

    @Bean
    public String hostUrl() {
        return hostUrl;
    }

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(username, password);
        return headers;
    }
}