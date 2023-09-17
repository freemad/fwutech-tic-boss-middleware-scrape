package com.fwutech.oss.middleware.core.configs;

import com.fwutech.oss.middleware.core.globals.ConxConstant;
import lombok.SneakyThrows;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class ApacheHttpClientConfig {

    @Value("${fwutech.oss.middleware.core.connect.max-total-conx}")
    private int MAX_TOTAL_CONX;
    @Value("${fwutech.oss.middleware.core.connect.max-route-conx}")
    private int MAX_ROUTE_CONX;
    @Value("${fwutech.oss.middleware.core.connect.default-keep-alive-time}")
    private int DEFAULT_KEEP_ALIVE_TIME;
    @Value("${fwutech.oss.middleware.core.connect.idle-conx-wait-time}")
    private int IDLE_CONX_WAIT_TIME;
    @Value("${fwutech.oss.middleware.core.connect.conx-timeout}")
    private int CONX_TIMEOUT;
    @Value("${fwutech.oss.middleware.core.connect.request-timeout}")
    private int REQUEST_TIMEOUT;
    @Value("${fwutech.oss.middleware.core.connect.socket-timeout}")
    private int SOCKET_TIMEOUT;


    @Bean
    public SSLContext sslContext() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        return new SSLContextBuilder()
                .loadTrustMaterial(null, TrustSelfSignedStrategy.INSTANCE)
                .build();
    }

    @SneakyThrows
    @Bean
    public SSLConnectionSocketFactory sslConnectionSocketFactory() {
//        SSLContext sslContext = SSLContexts.createSystemDefault();
        return new SSLConnectionSocketFactory(
                sslContext(),
                null,
//                new String[] { "TLSv1", "SSLv3" },
                null,
                NoopHostnameVerifier.INSTANCE);
    }

    @Bean
    public Registry<ConnectionSocketFactory> socketFactoryRegistry() {
        return RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", sslConnectionSocketFactory())
                .build();
    }

    @Bean
    public PoolingHttpClientConnectionManager poolingConnectionManager() {
        PoolingHttpClientConnectionManager poolingConnectionManager =
                new PoolingHttpClientConnectionManager(socketFactoryRegistry());
        // set total amount of connections across all HTTP routes
        poolingConnectionManager.setMaxTotal(MAX_TOTAL_CONX);
        // set maximum amount of connections for each http route in pool
        poolingConnectionManager.setDefaultMaxPerRoute(MAX_ROUTE_CONX);
        return poolingConnectionManager;
    }

    @Bean
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return (httpResponse, httpContext) -> {
            HeaderIterator headerIterator = httpResponse.headerIterator(HTTP.CONN_KEEP_ALIVE);
            HeaderElementIterator elementIterator = new BasicHeaderElementIterator(headerIterator);
            while (elementIterator.hasNext()) {
                HeaderElement element = elementIterator.nextElement();
                String param = element.getName();
                String value = element.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    return Long.parseLong(value) * 1000; // convert to ms
                }
            }
            return DEFAULT_KEEP_ALIVE_TIME;
        };
    }

    @Bean
    public Runnable idleConnectionMonitor(PoolingHttpClientConnectionManager pool) {
        return new Runnable() {
            @Override
            @Scheduled(fixedDelay = ConxConstant.IDLE_CONX_DISPOSE_INTERVAL)
            public void run() {
                // only if connection pool is initialised
                if (pool != null) {
                    pool.closeExpiredConnections();
                    pool.closeIdleConnections(IDLE_CONX_WAIT_TIME, TimeUnit.MILLISECONDS);
                    // TODO: 5/16/21 Log the closing conx count
                }
            }
        };
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("idleMonitor");
        scheduler.setPoolSize(5);
        return scheduler;
    }

    @SneakyThrows
    @Bean
    public CloseableHttpClient httpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONX_TIMEOUT)
                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        return HttpClients
                .custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(poolingConnectionManager())
                .setKeepAliveStrategy(connectionKeepAliveStrategy())
                .build();
    }
}