package com.fwutech.oss.middleware.adapters.fiberhometransmit.services.middles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fwutech.oss.middleware.adapters.fiberhometransmit.constants.FiberhomeTransmitConstant;
import com.fwutech.oss.middleware.adapters.fiberhometransmit.enums.FiberhomeTransmitErrorCode;
import com.fwutech.oss.middleware.adapters.fiberhometransmit.interfaces.configuration.IConfigurationFiberhomeTransmitAccessor;
import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.enums.CommonErrorCode;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import com.fwutech.oss.middleware.commons.globals.ApiParam;
import com.fwutech.oss.middleware.commons.globals.ApiRoute;
import com.fwutech.oss.middleware.commons.interfaces.configuration.IConfigurationAccessor;
import com.fwutech.oss.middleware.commons.utils.JsonUtil;
import com.fwutech.oss.middleware.commons.utils.StreamUtil;
import mtnm.fiberhome.com.extendedManagedElementManager.ClockLinkNetList_THolder;
import mtnm.tmforum.org.globaldefs.NameAndStringValue_T;
import mtnm.tmforum.org.globaldefs.ProcessingFailureException;
import mtnm.tmforum.org.subnetworkConnection.CrossConnect_T;
import mtnm.tmforum.org.subnetworkConnection.Route_THolder;
import mtnm.tmforum.org.trafficConditioningProfile.TCProfileList_THolder;
import mtnm.tmforum.org.trafficConditioningProfile.TCProfile_THolder;
import org.omg.CORBA.BAD_PARAM;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Qualifier(value = "fiberhomeTransmitConfigurationAccessorService")
public class ConfigurationAccessorService implements IConfigurationAccessor {
    @Autowired
    private IConfigurationFiberhomeTransmitAccessor configurationFiberhomeTransmitAccessor;

    @Override
    public String getAllCrossConnections(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String neId = params.get(ApiRoute.NE_ID);
            NameAndStringValue_T[] neNames = StreamUtil.
                    jsonStringToObject(neId, NameAndStringValue_T[].class);
            short[] connectionRates = StreamUtil.toShortArray(params.get("connectionRates"), ",");
            CrossConnect_T[] crossConnections = configurationFiberhomeTransmitAccessor.
                    getAllCrossConnections(neNames, connectionRates, pageIndex, pageSize).value;
            return JsonUtil.getJsonString(crossConnections);
        } catch (BusinessException ex) {
            ex.setRequestEvent(requestEvent);
            throw ex;
        } catch (ProcessingFailureException ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_PROCESSING_FAILURE_EXCEPTION,
                    ex.exceptionType != null ? ex.exceptionType.toString() : "", ex, requestEvent);
        } catch (JsonProcessingException ex) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, ex, requestEvent);
        } catch (NullPointerException | IllegalArgumentException ex) {
            throw new BusinessException(CommonErrorCode.BAD_PARAM_EXCEPTION, ex, requestEvent);
        } catch (COMM_FAILURE ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_COMM_FAILURE_EXCEPTION, ex, requestEvent);
        } catch (BAD_PARAM ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_BAD_PARAM_EXCEPTION, ex, requestEvent);
        } catch (Exception exception) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_GENERAL_EXCEPTION, exception, requestEvent);
        }
    }

    @Override
    public String getRoute(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            boolean includeHigherOrderCCs = Boolean.parseBoolean(
                    params.get(ApiParam.INCLUDE_HIGHER_ORDER_CCS) != null ? params.get(ApiParam.INCLUDE_HIGHER_ORDER_CCS) :
                            FiberhomeTransmitConstant.DEFAULT_INCLUDE_HIGHER_ORDER_CCS);
            String trailManagementId = params.get(ApiRoute.TRAIL_MANAGEMENT_ID);
            NameAndStringValue_T[] trailManagementNames = StreamUtil.
                    jsonStringToObject(trailManagementId, NameAndStringValue_T[].class);
            Route_THolder routeTHolder = configurationFiberhomeTransmitAccessor.
                    getRoute(trailManagementNames, includeHigherOrderCCs);
            return JsonUtil.getJsonString(routeTHolder.value);
        } catch (BusinessException ex) {
            ex.setRequestEvent(requestEvent);
            throw ex;
        } catch (ProcessingFailureException ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_PROCESSING_FAILURE_EXCEPTION,
                    ex.exceptionType != null ? ex.exceptionType.toString() : "", ex, requestEvent);
        } catch (JsonProcessingException ex) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, ex, requestEvent);
        } catch (NullPointerException | IllegalArgumentException ex) {
            throw new BusinessException(CommonErrorCode.BAD_PARAM_EXCEPTION, ex, requestEvent);
        } catch (COMM_FAILURE ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_COMM_FAILURE_EXCEPTION, ex, requestEvent);
        } catch (BAD_PARAM ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_BAD_PARAM_EXCEPTION, ex, requestEvent);
        } catch (Exception exception) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_GENERAL_EXCEPTION, exception, requestEvent);
        }
    }


    @Override
    public String getTrafficConditioningProfile(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String trafficConditioningProfileId = params.get(ApiRoute.TRAFFIC_CONDITIONING_PROFILE_ID);
            NameAndStringValue_T[] trafficConditioningProfileNames = StreamUtil.jsonStringToObject(trafficConditioningProfileId, NameAndStringValue_T[].class);
            TCProfile_THolder tcProfileT = configurationFiberhomeTransmitAccessor.getTCProfile(trafficConditioningProfileNames);
            return JsonUtil.getJsonString(tcProfileT.value);
        } catch (BusinessException ex) {
            ex.setRequestEvent(requestEvent);
            throw ex;
        } catch (ProcessingFailureException ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_PROCESSING_FAILURE_EXCEPTION,
                    ex.exceptionType != null ? ex.exceptionType.toString() : "", ex, requestEvent);
        } catch (JsonProcessingException ex) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, ex, requestEvent);
        } catch (NullPointerException | IllegalArgumentException ex) {
            throw new BusinessException(CommonErrorCode.BAD_PARAM_EXCEPTION, ex, requestEvent);
        } catch (COMM_FAILURE ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_COMM_FAILURE_EXCEPTION, ex, requestEvent);
        } catch (BAD_PARAM ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_BAD_PARAM_EXCEPTION, ex, requestEvent);
        } catch (Exception exception) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_GENERAL_EXCEPTION, exception, requestEvent);
        }
    }

    @Override
    public String getAllTrafficConditioningProfiles(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            TCProfileList_THolder tcProfileTs = configurationFiberhomeTransmitAccessor.getAllTCProfiles(pageIndex, pageSize);
            return JsonUtil.getJsonString(tcProfileTs.value);
        } catch (BusinessException ex) {
            ex.setRequestEvent(requestEvent);
            throw ex;
        } catch (ProcessingFailureException ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_PROCESSING_FAILURE_EXCEPTION,
                    ex.exceptionType != null ? ex.exceptionType.toString() : "", ex, requestEvent);
        } catch (JsonProcessingException ex) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, ex, requestEvent);
        } catch (NullPointerException | IllegalArgumentException ex) {
            throw new BusinessException(CommonErrorCode.BAD_PARAM_EXCEPTION, ex, requestEvent);
        } catch (COMM_FAILURE ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_COMM_FAILURE_EXCEPTION, ex, requestEvent);
        } catch (BAD_PARAM ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_BAD_PARAM_EXCEPTION, ex, requestEvent);
        } catch (Exception exception) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_GENERAL_EXCEPTION, exception, requestEvent);
        }
    }

    @Override
    public String getAllClockLinkNets(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String targetObjId = params.get(ApiRoute.TARGET_OBJ_ID);
            NameAndStringValue_T[] targetObjNames = StreamUtil
                    .jsonStringToObject(targetObjId, NameAndStringValue_T[].class);
            String linkType = params.get(ApiParam.LINK_TYPE) != null ? params.get(ApiParam.LINK_TYPE) : "";
            ClockLinkNetList_THolder allClockLinkNetsHolder = configurationFiberhomeTransmitAccessor
                    .getAllClockLinkNets(targetObjNames, linkType, pageIndex, pageSize);
            return JsonUtil.getJsonString(allClockLinkNetsHolder.value);
        } catch (BusinessException ex) {
            ex.setRequestEvent(requestEvent);
            throw ex;
        } catch (ProcessingFailureException ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_PROCESSING_FAILURE_EXCEPTION,
                    ex.exceptionType != null ? ex.exceptionType.toString() : "", ex, requestEvent);
        } catch (JsonProcessingException ex) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, ex, requestEvent);
        } catch (NullPointerException | IllegalArgumentException ex) {
            throw new BusinessException(CommonErrorCode.BAD_PARAM_EXCEPTION, ex, requestEvent);
        } catch (COMM_FAILURE ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_COMM_FAILURE_EXCEPTION, ex, requestEvent);
        } catch (BAD_PARAM ex) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_BAD_PARAM_EXCEPTION, ex, requestEvent);
        } catch (Exception exception) {
            throw new BusinessException(FiberhomeTransmitErrorCode.FIBERHOME_TRANSMIT_GENERAL_EXCEPTION, exception, requestEvent);
        }
    }
}
