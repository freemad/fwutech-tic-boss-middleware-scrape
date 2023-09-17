package com.fwutech.oss.middleware.adapters.ewsdswitch.services.nbis;

import com.fwutech.oss.middleware.commons.beans.msgbroker.ResponseEvent;
import com.fwutech.oss.middleware.commons.enums.NetworkType;
import com.fwutech.oss.middleware.commons.enums.ProtocolType;
import com.fwutech.oss.middleware.commons.enums.VendorType;
import com.fwutech.oss.middleware.commons.services.msgbroker.producers.BaseResponseEventMessageProduceService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.fwutech.oss.middleware.commons.interfaces.IMsgBrokerParams.EWSD_SWITCH_RESPONSE_QUEUE_NAME;
import static com.fwutech.oss.middleware.commons.interfaces.IMsgBrokerParams.EXCHANGE_NAME;

@Service
@Qualifier(value = "ewsdSwitchResponseEventMessageProduceService")
public class EwsdSwitchResponseEventMessageProduceService<Type>
        extends BaseResponseEventMessageProduceService<Type> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public EwsdSwitchResponseEventMessageProduceService() {
        super(NetworkType.SWITCH, ProtocolType.REST, VendorType.EWSD);
    }

    @Override
    public void produceMessage(ResponseEvent<Type> responseEvent) {
        Message message = rabbitTemplate.getMessageConverter().toMessage(responseEvent, null);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, EWSD_SWITCH_RESPONSE_QUEUE_NAME, responseEvent);
    }
}
