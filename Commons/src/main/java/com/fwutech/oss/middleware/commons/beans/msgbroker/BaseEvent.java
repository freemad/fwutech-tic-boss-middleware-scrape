package com.fwutech.oss.middleware.commons.beans.msgbroker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEvent implements Serializable {

    private Date timestamp;
}
