package com.fwutech.oss.middleware.commons.enums;

import lombok.Getter;

/**
 * Copyright 2020 (C) fwutech.com
 *
 * @author : Esmaeil Sadeghi {@link "mailto:e.sadeghi@fwutech.com"}
 */
@Getter
public enum AuditAction {

    CREATE("CREATED"), // CREATED NEW RECORD
    EDIT("UPDATE"), // UPDATE RECORD
    DELETE("DELETED"); // DELETED RECORD

    private final String action;

    AuditAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return action;
    }
}
