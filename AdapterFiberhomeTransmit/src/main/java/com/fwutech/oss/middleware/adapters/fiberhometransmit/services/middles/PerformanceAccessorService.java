package com.fwutech.oss.middleware.adapters.fiberhometransmit.services.middles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fwutech.oss.middleware.adapters.fiberhometransmit.constants.FiberhomeTransmitConstant;
import com.fwutech.oss.middleware.adapters.fiberhometransmit.enums.FiberhomeTransmitErrorCode;
import com.fwutech.oss.middleware.adapters.fiberhometransmit.interfaces.performance.IPerformanceFiberhomeTransmitAccessor;
import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.enums.CommonErrorCode;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import com.fwutech.oss.middleware.commons.globals.ApiParam;
import com.fwutech.oss.middleware.commons.globals.ApiRoute;
import com.fwutech.oss.middleware.commons.interfaces.performance.IPerformanceAccessor;
import com.fwutech.oss.middleware.commons.utils.JsonUtil;
import com.fwutech.oss.middleware.commons.utils.StreamUtil;
import mtnm.tmforum.org.globaldefs.NameAndStringValue_T;
import mtnm.tmforum.org.globaldefs.ProcessingFailureException;
import mtnm.tmforum.org.performance.*;
import org.omg.CORBA.BAD_PARAM;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Qualifier(value = "fiberhomeTransmitPerformanceAccessorService")
public class PerformanceAccessorService implements IPerformanceAccessor {

    @Autowired
    private IPerformanceFiberhomeTransmitAccessor performanceFiberhomeTransmitAccessorService;

    @Override
    public String getAllCurrentPerformanceData(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String[] performanceParameterSelects = StreamUtil.toStringArray(params.get(ApiParam.PERFORMANCE_PARAMETER_SELECTS), ",");
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            PMTPSelect_T[] performancePortSelects = StreamUtil.jsonStringToObject(params.get(ApiParam.PERFORMANCE_PORT_SELECTS), PMTPSelect_T[].class);
            PMDataList_THolder performanceDataHolder = performanceFiberhomeTransmitAccessorService.getAllCurrentPMData(performancePortSelects,
                    performanceParameterSelects, pageIndex, pageSize);
            return JsonUtil.getJsonString(performanceDataHolder.value);
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
    public String getThresholdCrossingAlertPortParameter(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String portId = params.get(ApiRoute.PORT_ID);
            NameAndStringValue_T[] portNames = StreamUtil.jsonStringToObject(portId, NameAndStringValue_T[].class);
            short layerRate = params.get(ApiParam.LAYER_RATE) != null ? Short.parseShort(params.get(ApiParam.LAYER_RATE)) :
                    FiberhomeTransmitConstant.DEFAULT_LAYER_RATE;
            String granularity = params.get(ApiParam.GRANULARITY) != null ? params.get(ApiParam.GRANULARITY) : "";
            TCAParameters_THolder thresholdCrossingAlertPortParameterHolder = performanceFiberhomeTransmitAccessorService
                    .getTCATPParameter(portNames, layerRate, granularity);
            return JsonUtil.getJsonString(thresholdCrossingAlertPortParameterHolder.value);
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
    public String getNePerformanceCapabilities(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String neId = params.get(ApiRoute.NE_ID);
            NameAndStringValue_T[] neNames = StreamUtil.jsonStringToObject(neId, NameAndStringValue_T[].class);
            short layerRate = params.get(ApiParam.LAYER_RATE) != null ? Short.parseShort(params.get(ApiParam.LAYER_RATE)) :
                    FiberhomeTransmitConstant.DEFAULT_LAYER_RATE;
            PMParameterList_THolder nePerformanceCapabilitiesHolder = performanceFiberhomeTransmitAccessorService
                    .getMEPMCapabilities(neNames, layerRate);
            return JsonUtil.getJsonString(nePerformanceCapabilitiesHolder.value);
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
    public String getHoldingTime(RequestEvent requestEvent) throws BusinessException {
        try {
            HoldingTime_THolder holdingTimeHolder = performanceFiberhomeTransmitAccessorService.getHoldingTime();
            return JsonUtil.getJsonString(holdingTimeHolder.value);
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
