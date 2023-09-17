package com.fwutech.oss.middleware.core.aspects;

import com.fwutech.oss.middleware.commons.beans.msgbroker.ResponseEvent;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import com.fwutech.oss.middleware.commons.interfaces.aspects.*;
import com.fwutech.oss.middleware.commons.utils.ResponseUtil;
import com.fwutech.oss.middleware.core.intefaces.ICallbackService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(IAspectOrder.HANDLE_EXCEPTION_ORDER)
public class CoreHandleExceptionAspect
        implements IExceptionPointcut, IHandleExceptionAspect,
        IControllersPointcut, ICallbackPointcut,
        IMessagingPointcut {

    @Autowired
    private ICallbackService callbackService;

    @Override
    @Pointcut("@annotation(com.fwutech.oss.middleware.commons.annotations.Handled)")
    public void handledPointcut() {
    }

    @Override
    @Pointcut("execution(* com.fwutech.oss.middleware.core" +
            ".services.sbis.consumers.ResponseEventMessageConsumerService.consumeMessage(..))")
    public void consumeMessagePointcut() {
    }

    @Override
    @Pointcut("execution(* com.fwutech.oss.middleware.core" +
            ".services.sbis.producers.*.produceMessage(..))")
    public void produceMessagePointcut() {
    }

    @Override
    @Pointcut("execution(* com.fwutech.oss.middleware.core.services.nbis.CallbackService.sendResultToAgent(..))")
    public void callbackPointcut() {
    }

    @Override
    @Pointcut("execution(* com.fwutech.oss.middleware.core.controllers.*.*(..))")
    public void controllersPointcut() {
    }

    @Override
    @AfterThrowing(value = "handledPointcut()", throwing = "ex")
    public void handleException(JoinPoint joinPoint, Throwable ex) {
        // TODO: 3/28/21 do what ever is needed to handle the exception, except logging
        if (ex instanceof BusinessException) {
            // the exceptioned business event is produced into the appropriate queue
            ResponseEvent responseEvent = ResponseUtil.generateErrorResponseEvent((BusinessException) ex);
            callbackService.sendResultToAgent(responseEvent);
        }
        // else do nothing, because it would be logged in log aspect
    }
}
