package com.fwutech.oss.middleware.adapters.fanatransmit.beans.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class EventType implements Serializable {
    public static final long serialVersionUID = 3133450332109991974L;

    private String domainName;
    private String typeName;
}
