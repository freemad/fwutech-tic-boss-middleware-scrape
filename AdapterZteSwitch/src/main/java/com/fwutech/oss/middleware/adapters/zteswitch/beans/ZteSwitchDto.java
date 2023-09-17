package com.fwutech.oss.middleware.adapters.zteswitch.beans;

import com.fwutech.oss.middleware.commons.dtos.BaseDto;
import com.fwutech.oss.middleware.commons.enums.NetworkType;
import com.fwutech.oss.middleware.commons.enums.ProtocolType;
import com.fwutech.oss.middleware.commons.enums.VendorType;

public class ZteSwitchDto<Type> extends BaseDto<Type> {
    private static final long serialVersionUID = 1055753233567905770L;

    public ZteSwitchDto() {
        super(VendorType.ZTE, NetworkType.SWITCH, ProtocolType.REST);
    }
}
