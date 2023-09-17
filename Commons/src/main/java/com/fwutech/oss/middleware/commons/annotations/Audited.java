package com.fwutech.oss.middleware.commons.annotations;

import java.lang.annotation.*;

/**
 * Copyright 2020 (C) fwutech.com
 *
 * @author : Esmaeil Sadeghi {@link "mailto:e.sadeghi@fwutech.com"}
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Audited {
    String value() default "";

    Class<?> target();
}