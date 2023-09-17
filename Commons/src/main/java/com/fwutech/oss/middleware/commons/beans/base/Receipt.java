package com.fwutech.oss.middleware.commons.beans.base;

import com.fwutech.oss.middleware.commons.enums.BeanType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Receipt extends BaseBean {
    private static final long serialVersionUID = 7750959939814079430L;

    private String uuid;
    private Date timestamp;

    public Receipt() {
        super(BeanType.RECEIPT);
    }

    public Receipt(String uuid) {
        super(BeanType.RECEIPT);
        this.uuid = uuid;
        timestamp = new Date();
    }
}
