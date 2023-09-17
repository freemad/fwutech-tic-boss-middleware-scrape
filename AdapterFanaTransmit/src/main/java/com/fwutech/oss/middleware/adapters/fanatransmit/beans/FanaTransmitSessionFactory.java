package com.fwutech.oss.middleware.adapters.fanatransmit.beans;

import com.fwutech.oss.middleware.adapters.fanatransmit.enums.FanaTransmitManagerType;
import com.fwutech.oss.middleware.adapters.fanatransmit.interfaces.IFanaTransmitConnectService;
import com.fwutech.oss.middleware.adapters.fanatransmit.utils.DeviceUtil;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import mtnm.tmforum.org.common.Common_IHolder;
import mtnm.tmforum.org.emsMgr.EMSMgr_I;
import mtnm.tmforum.org.emsMgr.EMS_THolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanaTransmitSessionFactory {

    @Autowired
    private CorbaConnect corbaConnect;
    @Autowired
    private IFanaTransmitConnectService fanaTransmitConnectService;

    public CorbaConnect checkCorbaConnect() throws BusinessException {
        try {
            corbaConnect.getEmsSession().ping();
            Common_IHolder commonIHolder = DeviceUtil.getManager(corbaConnect.getEmsSession(), FanaTransmitManagerType.EMS);
            EMSMgr_I emsMgr = DeviceUtil.getEmsMgr(commonIHolder);
            EMS_THolder emsHolder = new EMS_THolder();
            emsMgr.getEMS(emsHolder);
            return corbaConnect;
        } catch (Exception e) {
            CorbaConnect corbaConnect = fanaTransmitConnectService.connect();
            this.corbaConnect = corbaConnect;
            return corbaConnect;
        }
    }

    public CorbaConnect getCorbaConnect() {
        return corbaConnect;
    }
}
