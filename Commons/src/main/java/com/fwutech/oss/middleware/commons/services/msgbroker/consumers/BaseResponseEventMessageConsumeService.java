package com.fwutech.oss.middleware.commons.services.msgbroker.consumers;

import com.fwutech.oss.middleware.commons.enums.NetworkType;
import com.fwutech.oss.middleware.commons.enums.ProtocolType;
import com.fwutech.oss.middleware.commons.enums.VendorType;
import com.fwutech.oss.middleware.commons.interfaces.msgbroker.consumer.IResponseEventMessageConsumer;

public abstract class BaseResponseEventMessageConsumeService<Type>
        implements IResponseEventMessageConsumer {

    protected NetworkType networkType;
    protected ProtocolType protocolType;
    protected VendorType vendorType;

    public BaseResponseEventMessageConsumeService(
            NetworkType networkType,
            ProtocolType protocolType,
            VendorType vendorType) {
        this.networkType = networkType;
        this.protocolType = protocolType;
        this.vendorType = vendorType;
    }
}
