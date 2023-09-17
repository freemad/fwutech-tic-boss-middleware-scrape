package com.fwutech.oss.middleware.msgbroker.configs;

import com.fwutech.oss.middleware.commons.interfaces.IMsgBrokerParams;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BaseRmqConfiguration.class)
public class EwsdSwitchConfig implements IMsgBrokerParams {


    @Autowired
    DirectExchange normalExchange;
    @Value("${fwutech.oss.middleware.msgbroker.ttl.ewsd-switch-rest-ttl}")
    private long EWSD_SWITCH_REST_TTL;

    @Bean
    Queue ewsdSwitchRequestQueue() {
        return QueueBuilder
                .durable(EWSD_SWITCH_REQUEST_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE_NAME)
                .withArgument("x-message-ttl", EWSD_SWITCH_REST_TTL)
                .build();
    }

    @Bean
    Queue ewsdSwitchResponseQueue() {
        return QueueBuilder
                .durable(EWSD_SWITCH_RESPONSE_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE_NAME)
                .build();
    }

    @Bean
    Binding ewsdSwitchRequestQueueBinding() {
        return BindingBuilder
                .bind(ewsdSwitchRequestQueue())
                .to(normalExchange)
                .with(EWSD_SWITCH_REQUEST_QUEUE_NAME);
    }

    @Bean
    Binding ewsdSwitchResponseQueueBinding() {
        return BindingBuilder
                .bind(ewsdSwitchResponseQueue())
                .to(normalExchange)
                .with(EWSD_SWITCH_RESPONSE_QUEUE_NAME);
    }
}

