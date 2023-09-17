package com.fwutech.oss.middleware.core.services.sbis.consumers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fwutech.oss.middleware.commons.beans.msgbroker.ResponseEvent;
import com.fwutech.oss.middleware.commons.dtos.BaseDto;
import com.fwutech.oss.middleware.commons.enums.NetworkType;
import com.fwutech.oss.middleware.commons.enums.ProtocolType;
import com.fwutech.oss.middleware.commons.enums.VendorType;
import com.fwutech.oss.middleware.commons.services.msgbroker.consumers.BaseResponseEventMessageConsumeService;
import com.fwutech.oss.middleware.core.services.nbis.CallbackService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fwutech.oss.middleware.commons.interfaces.IMsgBrokerParams.*;

@Service
public class ResponseEventMessageConsumerService<Type>
        extends BaseResponseEventMessageConsumeService<Type> {

    @Autowired
    private CallbackService callbackService;

    private ResponseEvent<Type> responseEvent;
    @Autowired
    private MessageConverter messageConverter;

    public ResponseEventMessageConsumerService() {
        super(NetworkType.TRANSMIT, ProtocolType.CORBA, VendorType.FIBERHOME);
    }

    @Override
    public ResponseEvent<Type> getEvent() {
        return responseEvent;
    }

    @RabbitListener(queues = FIBERHOME_TRANSMIT_RESPONSE_QUEUE_NAME)
    public void consumeMessageFiberhomeTransmit(Message message) {
        consumeMessage(message);
    }

    @RabbitListener(queues = HUAWEI_DATA_RESPONSE_QUEUE_NAME)
    public void consumeMessageHuaweiData(Message message) {
        consumeMessage(message);
    }

    @RabbitListener(queues = FANA_TRANSMIT_RESPONSE_QUEUE_NAME)
    public void consumeMessageFanaTransmit(Message message) {
        consumeMessage(message);
    }

    @RabbitListener(queues = HUAWEI_TRANSMIT_RESPONSE_QUEUE_NAME)
    public void consumeMessageHuaweiTransmit(Message message) {
        consumeMessage(message);
    }

    @RabbitListener(queues = CISCO_DATA_RESPONSE_QUEUE_NAME)
    public void consumeMessageCiscoData(Message message) {
        consumeMessage(message);
    }

    @RabbitListener(queues = NCE_DATA_RESPONSE_QUEUE_NAME)
    public void consumeMessageNceData(Message message) {
        consumeMessage(message);
    }

    @RabbitListener(queues = HUAWEI_SWITCH_RESPONSE_QUEUE_NAME)
    public void consumeMessageHuaweiSwitch(Message message) {
        consumeMessage(message);
    }

    @RabbitListener(queues = HUAWEI_EV_SWITCH_RESPONSE_QUEUE_NAME)
    public void consumeMessageHuaweiEvSwitch(Message message) {
        consumeMessage(message);
    }

    @RabbitListener(queues = EWSD_SWITCH_RESPONSE_QUEUE_NAME)
    public void consumeMessageEwsdSwitch(Message message) {
        consumeMessage(message);
    }

    @RabbitListener(queues = NEAX_SWITCH_RESPONSE_QUEUE_NAME)
    public void consumeMessageNeaxSwitch(Message message) {
        consumeMessage(message);
    }

    @RabbitListener(queues = ZTE_SWITCH_RESPONSE_QUEUE_NAME)
    public void consumeMessageZteSwitch(Message message) {
        consumeMessage(message);
    }

    public void consumeMessage(Message message) {
        try {
            // TODO: 26.04.21 refactoring code
            if (!HUAWEI_TRANSMIT_RESPONSE_QUEUE_NAME.equals(message.getMessageProperties().getReceivedRoutingKey())) {
                responseEvent = (ResponseEvent<Type>) messageConverter.fromMessage(message);
                callbackService.sendResultToAgent(responseEvent);
            } else {
                String messageReceived = new String(message.getBody(), "UTF-8");
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                responseEvent = (ResponseEvent<Type>) objectMapper.readValue(messageReceived, ResponseEvent.class);
                VendorType vendorType = VendorType.HUAWEI;
                NetworkType networkType = NetworkType.TRANSMIT;
                ProtocolType protocolType = ProtocolType.RPC;
                if (responseEvent.getResult().isSucceeded()) {
                    BaseDto baseDto = new BaseDto(vendorType, networkType, protocolType);
                    baseDto.setBeanObj(responseEvent.getResult().getData().getBeanObj());
                    responseEvent.getResult().setData(baseDto);
                    callbackService.sendResultToAgent(responseEvent);
                } else {
                    throw new MessageConversionException("Rpc exception message conversion exception");
                }
            }
//        } catch (MessageConversionException | JsonProcessingException | UnsupportedEncodingException exception) {
//            BusinessException businessException = new BusinessException(
//                    MsgBrokerErrorCode.MESSAGE_CONVERSION_EXCEPTION, exception, responseEvent.getRequest());
//            ResponseEvent responseEvent = ResponseUtil.generateErrorResponseEvent(businessException);
//            callbackService.sendResultToAgent(responseEvent);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
