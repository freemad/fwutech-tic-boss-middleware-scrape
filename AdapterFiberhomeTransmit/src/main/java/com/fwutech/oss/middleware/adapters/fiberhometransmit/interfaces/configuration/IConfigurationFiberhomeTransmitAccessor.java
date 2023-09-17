package com.fwutech.oss.middleware.adapters.fiberhometransmit.interfaces.configuration;

import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import mtnm.fiberhome.com.extendedManagedElementManager.ClockLinkNetList_THolder;
import mtnm.tmforum.org.globaldefs.NameAndStringValue_T;
import mtnm.tmforum.org.globaldefs.ProcessingFailureException;
import mtnm.tmforum.org.subnetworkConnection.CrossConnectList_THolder;
import mtnm.tmforum.org.subnetworkConnection.Route_THolder;
import mtnm.tmforum.org.trafficConditioningProfile.TCProfileList_THolder;
import mtnm.tmforum.org.trafficConditioningProfile.TCProfile_THolder;

public interface IConfigurationFiberhomeTransmitAccessor {
    CrossConnectList_THolder getAllCrossConnections(NameAndStringValue_T[] neNames, short[] connectionRates,
                                                    int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    Route_THolder getRoute(NameAndStringValue_T[] sncNames, boolean includeHigherOrderCCs)
            throws ProcessingFailureException, BusinessException;

    TCProfile_THolder getTCProfile(NameAndStringValue_T[] trafficConditioningProfileNames)
            throws ProcessingFailureException, BusinessException;

    TCProfileList_THolder getAllTCProfiles(int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    ClockLinkNetList_THolder getAllClockLinkNets(NameAndStringValue_T[] targetObjNames, String linkType,
                                                 int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;
}
