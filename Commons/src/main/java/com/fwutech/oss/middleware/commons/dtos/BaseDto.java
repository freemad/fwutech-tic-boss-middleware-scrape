package com.fwutech.oss.middleware.commons.dtos;

import com.fwutech.oss.middleware.commons.beans.base.AdapterType;
import com.fwutech.oss.middleware.commons.enums.NetworkType;
import com.fwutech.oss.middleware.commons.enums.ProtocolType;
import com.fwutech.oss.middleware.commons.enums.VendorType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BaseDto<Type> implements Serializable {
    private static final long serialVersionUID = -3908769109490292684L;

    protected static AdapterType adapterType;
    private Type beanObj;

    public BaseDto(VendorType vendorType,
                   NetworkType networkType,
                   ProtocolType protocolType) {
        BaseDto.adapterType = new AdapterType(vendorType, networkType, protocolType);
    }

    public NetworkType getNetworkType() {
        return adapterType.getNetworkType();
    }


    public VendorType getVendorType() {
        return adapterType.getVendorType();
    }

    public ProtocolType getProtocolType() {
        return adapterType.getProtocolType();
    }
}
