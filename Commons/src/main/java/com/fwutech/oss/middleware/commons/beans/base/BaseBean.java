package com.fwutech.oss.middleware.commons.beans.base;

import com.fwutech.oss.middleware.commons.enums.BeanType;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseBean implements Serializable {
    private static final long serialVersionUID = -3589304080955444733L;

    protected static BeanType beanType;

    public BaseBean() {
    }

    public BaseBean(BeanType beanType) {
        BaseBean.beanType = beanType;
    }

    public BeanType getBeanType() {
        return beanType;
    }
}
