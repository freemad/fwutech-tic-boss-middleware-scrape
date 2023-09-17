package com.fwutech.oss.middleware.core.aspects;

import com.fwutech.oss.middleware.commons.annotations.Audited;
import com.fwutech.oss.middleware.commons.interfaces.aspects.IAspectOrder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Copyright 2020 (C) fwutech.com
 *
 * @author : Esmaeil Sadeghi {@link "mailto:e.sadeghi@fwutech.com"}
 */

@Aspect
@Component
@Order(IAspectOrder.AUDIT_ORDER)
public class AuditAspect {

    private final ApplicationContext applicationContext;

    public AuditAspect(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @AfterReturning(value = "@annotation(audited)", returning = "retVal")
    public void auditAfterStockServiceInvoked(JoinPoint joinPoint, Object retVal, Audited audited) throws Throwable {
        System.out.println("audit after stock service invoked");
        System.out.println("retVal: " + retVal);
        System.out.println("audited annotation value: " + audited.value());

        Class<?> clazz = audited.target();
        Object object = applicationContext.getBean(clazz);
        clazz.getMethod(joinPoint.getSignature().getName(), String.class).invoke(object, audited.value());
    }
}