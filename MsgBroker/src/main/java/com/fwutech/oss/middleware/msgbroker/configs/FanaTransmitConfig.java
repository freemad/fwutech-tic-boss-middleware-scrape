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
public class FanaTransmitConfig implements IMsgBrokerParams {

    @Autowired
    DirectExchange normalExchange;
    @Value("${fwutech.oss.middleware.msgbroker.ttl.fana-transmit-corba-ttl}")
    private long FANA_TRANSMIT_CORBA_TTL;

    @Bean
    Queue fanaTransmitRequestQueue() {
        return QueueBuilder
                .durable(FANA_TRANSMIT_REQUEST_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE_NAME)
                .withArgument("x-message-ttl", FANA_TRANSMIT_CORBA_TTL)
                .build();
    }

    @Bean
    Queue fanaTransmitResponseQueue() {
        return QueueBuilder
                .durable(FANA_TRANSMIT_RESPONSE_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE_NAME)
                .build();
    }

    @Bean
    Binding fanaTransmitRequestQueueBinding() {
        return BindingBuilder
                .bind(fanaTransmitRequestQueue())
                .to(normalExchange)
                .with(FANA_TRANSMIT_REQUEST_QUEUE_NAME);
    }

    @Bean
    Binding fanaTransmitResponseQueueBinding() {
        return BindingBuilder
                .bind(fanaTransmitResponseQueue())
                .to(normalExchange)
                .with(FANA_TRANSMIT_RESPONSE_QUEUE_NAME);
    }
}
