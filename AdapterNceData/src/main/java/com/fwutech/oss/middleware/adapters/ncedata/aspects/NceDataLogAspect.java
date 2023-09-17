package com.fwutech.oss.middleware.adapters.ncedata.aspects;

import com.fwutech.oss.middleware.commons.interfaces.aspects.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.json.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(IAspectOrder.LOG_ORDER)
public class NceDataLogAspect
        implements IModuleName,
        IMessagingPointcut,
        ILogPointcut, ILogAfterThrowingAspect, ILogAfter, ILogBefore {

    @Override
    @Pointcut("execution(* com.fwutech.oss.middleware.adapters.ncedata.services.nbis" +
            ".NceDataRequestEventMessageConsumerService.consumeMessage(..))")
    public void consumeMessagePointcut() {
    }

    @Override
    @Pointcut("execution(* com.fwutech.oss.middleware.adapters.ncedata.services.nbis" +
            ".NceDataResponseEventMessageProduceService.produceMessage(..))")
    public void produceMessagePointcut() {
    }

    @Override
    @Pointcut("@annotation(com.fwutech.oss.middleware.commons.annotations.Logged)")
    public void logPointcut() {
    }

    @Override
    public String getModuleName() {
        return "AdapterNceData";
    }

    @Before(value = "logPointcut() || produceMessagePointcut() || consumeMessagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        JSONObject jsonObject = prepareBeforeLog(joinPoint);
        log.info(jsonObject.toString());
    }

    @AfterReturning(value = "logPointcut()", returning = "returnValue")
    public void logAfter(JoinPoint joinPoint, Object returnValue) {
        JSONObject jsonObject = prepareAfterLog(joinPoint, returnValue);
        log.info(jsonObject.toString());
    }

    @Override
    @AfterThrowing(value = "logPointcut() || consumeMessagePointcut() || produceMessagePointcut()",
            throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        JSONObject jsonObject = prepareAfterThrowingLog(joinPoint, ex);
        log.error(jsonObject.toString());
    }
}
