package com.fwutech.oss.middleware.adapters.ciscodata.services.nbis;


import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.beans.msgbroker.ResponseEvent;
import com.fwutech.oss.middleware.commons.enums.*;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import com.fwutech.oss.middleware.commons.interfaces.IDispatcherService;
import com.fwutech.oss.middleware.commons.interfaces.msgbroker.producer.IResponseEventMessageProducer;
import com.fwutech.oss.middleware.commons.services.msgbroker.consumers.BaseRequestEventMessageConsumeService;
import com.fwutech.oss.middleware.commons.utils.ResponseUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.fwutech.oss.middleware.commons.interfaces.IMsgBrokerParams.CISCO_DATA_REQUEST_QUEUE_NAME;

@Service
public class CiscoDataRequestEventMessageConsumerService
        extends BaseRequestEventMessageConsumeService {

    private RequestEvent requestEvent;
    @Autowired
    private MessageConverter messageConverter;
    @Autowired
    @Qualifier(value = "ciscoDataCommandDispatcherService")
    private IDispatcherService ciscoDataCommandDispatcherService;
    @Autowired
    @Qualifier(value = "ciscoDataResponseEventMessageProduceService")
    private IResponseEventMessageProducer ciscoDataResponseEventMessageProduceService;

    public CiscoDataRequestEventMessageConsumerService() {
        super(NetworkType.DATA, ProtocolType.REST, VendorType.CISCO);
    }

    @Override
    public RequestEvent getEvent() {
        return requestEvent;
    }

    @RabbitListener(queues = CISCO_DATA_REQUEST_QUEUE_NAME,
            containerFactory = "listenerContainerFactory")
    public void consumeMessage(Message message) throws BusinessException {
        try {
            requestEvent = (RequestEvent) messageConverter.fromMessage(message);
            ciscoDataCommandDispatcherService.dispatchRequestEvent(requestEvent);
        } catch (BusinessException | MessageConversionException exception) {
            BusinessException businessException = exception instanceof BusinessException ? (BusinessException) exception :
                    new BusinessException(
                            MsgBrokerErrorCode.MESSAGE_CONVERSION_EXCEPTION, exception, requestEvent);
            ResponseEvent responseEvent = ResponseUtil.generateErrorResponseEvent(businessException);
            ciscoDataResponseEventMessageProduceService.produceMessage(responseEvent);
        } catch (Exception exception) {
            throw new BusinessException(MsgBrokerErrorCode.CONSUME_REQUEST_EXCEPTION, exception, requestEvent);
        } catch (Throwable throwable) {
            throw new BusinessException(CommonErrorCode.UNCAUGHT_ERROR_EXCEPTION, throwable, requestEvent);
        }
    }
}