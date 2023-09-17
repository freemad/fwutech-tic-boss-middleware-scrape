package com.fwutech.oss.middleware.core.services.sbis.producers;

import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.enums.NetworkType;
import com.fwutech.oss.middleware.commons.enums.ProtocolType;
import com.fwutech.oss.middleware.commons.enums.VendorType;
import com.fwutech.oss.middleware.commons.services.msgbroker.producers.BaseRequestEventMessageProduceService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.fwutech.oss.middleware.commons.interfaces.IMsgBrokerParams.*;
@Service
@Qualifier(value = "zteSwitchRequestEventMessageProduceService")
public class ZteSwitchRequestEventMessageProduceService extends BaseRequestEventMessageProduceService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ZteSwitchRequestEventMessageProduceService() {
        super(NetworkType.SWITCH, ProtocolType.REST, VendorType.ZTE);
    }

    @Override
    public void produceMessage(RequestEvent requestEvent) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ZTE_SWITCH_REQUEST_QUEUE_NAME, requestEvent);
    }
}
