package com.fwutech.oss.middleware.adapters.fanatransmit.utils;

import com.fwutech.oss.middleware.adapters.fanatransmit.enums.FanaTransmitManagerType;
import mtnm.tmforum.org.common.Common_IHolder;
import mtnm.tmforum.org.emsMgr.EMSMgr_I;
import mtnm.tmforum.org.emsMgr.EMSMgr_IHelper;
import mtnm.tmforum.org.emsMgr.EMS_T;
import mtnm.tmforum.org.emsMgr.EMS_THolder;
import mtnm.tmforum.org.emsSession.EmsSession_I;
import mtnm.tmforum.org.equipment.EquipmentInventoryMgr_I;
import mtnm.tmforum.org.equipment.EquipmentInventoryMgr_IHelper;
import mtnm.tmforum.org.flowDomain.FlowDomainMgr_I;
import mtnm.tmforum.org.flowDomain.FlowDomainMgr_IHelper;
import mtnm.tmforum.org.globaldefs.ProcessingFailureException;
import mtnm.tmforum.org.managedElementManager.ManagedElementMgr_I;
import mtnm.tmforum.org.managedElementManager.ManagedElementMgr_IHelper;
import mtnm.tmforum.org.multiLayerSubnetwork.MultiLayerSubnetworkMgr_I;
import mtnm.tmforum.org.multiLayerSubnetwork.MultiLayerSubnetworkMgr_IHelper;
import mtnm.tmforum.org.performance.PerformanceManagementMgr_I;
import mtnm.tmforum.org.performance.PerformanceManagementMgr_IHelper;
import mtnm.tmforum.org.protection.ProtectionMgr_I;
import mtnm.tmforum.org.protection.ProtectionMgr_IHelper;
import mtnm.tmforum.org.trafficConditioningProfile.TCProfileMgr_I;
import mtnm.tmforum.org.trafficConditioningProfile.TCProfileMgr_IHelper;

// TODO: 27.12.20 Bean
public class DeviceUtil {

    public static EMSMgr_I getEmsMgr(Common_IHolder EmsMgrHolder) {
        return EMSMgr_IHelper.narrow(EmsMgrHolder.value);
    }

    public static EMS_THolder getEmsHolder(EMSMgr_I emsMgr) {
        EMS_THolder emsHolder = new EMS_THolder();
        try {
            emsMgr.getEMS(emsHolder);
        } catch (ProcessingFailureException e) {
            e.printStackTrace();
        }
        return emsHolder;
    }

    public static EMS_T getEms(EMS_THolder emsHolder) {
        return emsHolder.value;
    }

    public static EquipmentInventoryMgr_I getEquipmentInventoryMgr(Common_IHolder inventoryMgrHolder) {
        return EquipmentInventoryMgr_IHelper.narrow(inventoryMgrHolder.value);
    }

    public static ManagedElementMgr_I getManagedElementMgr(Common_IHolder MgrHolder) {
        return ManagedElementMgr_IHelper.narrow(MgrHolder.value);
    }

    public static MultiLayerSubnetworkMgr_I getMultiLayerSubnetworkMgr(Common_IHolder subnetworkConnection) {
        return MultiLayerSubnetworkMgr_IHelper.narrow(subnetworkConnection.value);
    }

    public static Common_IHolder getManager(EmsSession_I emsSession, FanaTransmitManagerType fanaTransmitManagerType) {
        Common_IHolder mgrHolder = new Common_IHolder();
        try {
            emsSession.getManager(fanaTransmitManagerType.getType(), mgrHolder);
        } catch (ProcessingFailureException e) {
            e.printStackTrace();
        }
        return mgrHolder;
    }

    public static PerformanceManagementMgr_I getPerformanceMgr(Common_IHolder performanceMgrHolder) {
        return PerformanceManagementMgr_IHelper.narrow(performanceMgrHolder.value);
    }

    public static TCProfileMgr_I getTCProfileMgr(Common_IHolder tCProfileMgrHolder) {
        return TCProfileMgr_IHelper.narrow(tCProfileMgrHolder.value);
    }

    public static ProtectionMgr_I getProtectionMgr(Common_IHolder protectionMgrHolder) {
        return ProtectionMgr_IHelper.narrow(protectionMgrHolder.value);
    }

    public static FlowDomainMgr_I getFlowDomainMgr(Common_IHolder flowDomainMgrHolder) {
        return FlowDomainMgr_IHelper.narrow(flowDomainMgrHolder.value);
    }
}
