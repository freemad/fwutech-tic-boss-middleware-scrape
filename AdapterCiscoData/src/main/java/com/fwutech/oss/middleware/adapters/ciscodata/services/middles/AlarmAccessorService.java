package com.fwutech.oss.middleware.adapters.ciscodata.services.middles;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fwutech.oss.middleware.adapters.ciscodata.enums.CiscoDataErrorCode;
import com.fwutech.oss.middleware.adapters.ciscodata.global.CiscoDataApiParam;
import com.fwutech.oss.middleware.adapters.ciscodata.interfaces.alarm.IAlarmCiscoDataAccessor;
import com.fwutech.oss.middleware.adapters.ciscodata.utils.CiscoDataParamUtil;
import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.enums.CommonErrorCode;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import com.fwutech.oss.middleware.commons.globals.ApiParam;
import com.fwutech.oss.middleware.commons.globals.ApiRoute;
import com.fwutech.oss.middleware.commons.interfaces.alarm.IAlarmAccessor;
import com.fwutech.oss.middleware.commons.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Qualifier(value = "ciscoDataAlarmAccessorService")
public class AlarmAccessorService implements IAlarmAccessor {

    @Autowired
    private IAlarmCiscoDataAccessor alarmCiscoDataAccessorService;

    @Override
    public String getAllEmsAndNeActiveAlarms(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> rawMap = requestEvent.getCommand().getParams();
            Map<String, String> filteredParams = new HashMap<>();
            filteredParams.put(ApiParam.FULL, CiscoDataApiParam.DOT_FULL);
            filteredParams.put(ApiParam.SORT, CiscoDataApiParam.DOT_SORT);
            filteredParams.put(ApiParam.GROUP, CiscoDataApiParam.DOT_GROUP);
            filteredParams.put(ApiParam.INSTANCE, CiscoDataApiParam.DOT_INSTANCE);
            Map<String, String> qParams = CiscoDataParamUtil.getCiscoDataQueryParams(rawMap, filteredParams);
            ResponseEntity<Object> responseEntity = alarmCiscoDataAccessorService.getAllAlarms(qParams);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return JsonUtil.getJsonString(responseEntity.getBody(), JsonAutoDetect.Visibility.PUBLIC_ONLY);
            }
            throw new BusinessException(CiscoDataErrorCode.HTTP_REQUEST_EXCEPTION, responseEntity.getStatusCode().toString(), requestEvent);
        } catch (BusinessException e) {
            throw e;
        } catch (RestClientException e) {
            throw new BusinessException(CiscoDataErrorCode.REST_CONNECTION_EXCEPTION, e, requestEvent);
        } catch (JsonProcessingException e) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, e, requestEvent);
        } catch (NullPointerException | IllegalArgumentException | ParseException e) {
            throw new BusinessException(CommonErrorCode.BAD_PARAM_EXCEPTION, e, requestEvent);
        } catch (Exception e) {
            throw new BusinessException(CiscoDataErrorCode.CISCO_DATA_GENERAL_EXCEPTION, e, requestEvent);
        }
    }

    @Override
    public String getAlarmById(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> rawMap = requestEvent.getCommand().getParams();
            String alarmId = rawMap.get(ApiRoute.ALARM_ID);
            ResponseEntity<Object> responseEntity = alarmCiscoDataAccessorService.getAlarmById(alarmId);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return JsonUtil.getJsonString(responseEntity.getBody(), JsonAutoDetect.Visibility.PUBLIC_ONLY);
            }
            throw new BusinessException(CiscoDataErrorCode.HTTP_REQUEST_EXCEPTION, responseEntity.getStatusCode().toString(), requestEvent);
        } catch (BusinessException e) {
            throw e;
        } catch (RestClientException e) {
            throw new BusinessException(CiscoDataErrorCode.REST_CONNECTION_EXCEPTION, e, requestEvent);
        } catch (JsonProcessingException e) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, e, requestEvent);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new BusinessException(CommonErrorCode.BAD_PARAM_EXCEPTION, e, requestEvent);
        } catch (Exception e) {
            throw new BusinessException(CiscoDataErrorCode.CISCO_DATA_GENERAL_EXCEPTION, e, requestEvent);
        }
    }

    @Override
    public String getAllEvents(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> rawMap = requestEvent.getCommand().getParams();
            Map<String, String> filteredParams = new HashMap<>();
            filteredParams.put(ApiParam.FULL, CiscoDataApiParam.DOT_FULL);
            filteredParams.put(ApiParam.SORT, CiscoDataApiParam.DOT_SORT);
            filteredParams.put(ApiParam.GROUP, CiscoDataApiParam.DOT_GROUP);
            filteredParams.put(ApiParam.INSTANCE, CiscoDataApiParam.DOT_INSTANCE);
            Map<String, String> qParams = CiscoDataParamUtil.getCiscoDataQueryParams(rawMap, filteredParams);
            ResponseEntity<Object> responseEntity = alarmCiscoDataAccessorService.getAllEvents(qParams);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return JsonUtil.getJsonString(responseEntity.getBody(), JsonAutoDetect.Visibility.PUBLIC_ONLY);
            }
            throw new BusinessException(CiscoDataErrorCode.HTTP_REQUEST_EXCEPTION, responseEntity.getStatusCode().toString(), requestEvent);
        } catch (BusinessException e) {
            throw e;
        } catch (RestClientException e) {
            throw new BusinessException(CiscoDataErrorCode.REST_CONNECTION_EXCEPTION, e, requestEvent);
        } catch (JsonProcessingException e) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, e, requestEvent);
        } catch (NullPointerException | IllegalArgumentException | ParseException e) {
            throw new BusinessException(CommonErrorCode.BAD_PARAM_EXCEPTION, e, requestEvent);
        } catch (Exception e) {
            throw new BusinessException(CiscoDataErrorCode.CISCO_DATA_GENERAL_EXCEPTION, e, requestEvent);
        }
    }

    @Override
    public String getEventById(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> rawMap = requestEvent.getCommand().getParams();
            String eventId = rawMap.get(ApiRoute.EVENT_ID);
            ResponseEntity<Object> responseEntity = alarmCiscoDataAccessorService.getEventById(eventId);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return JsonUtil.getJsonString(responseEntity.getBody(), JsonAutoDetect.Visibility.PUBLIC_ONLY);
            }
            throw new BusinessException(CiscoDataErrorCode.HTTP_REQUEST_EXCEPTION, responseEntity.getStatusCode().toString(), requestEvent);
        } catch (BusinessException e) {
            throw e;
        } catch (RestClientException e) {
            throw new BusinessException(CiscoDataErrorCode.REST_CONNECTION_EXCEPTION, e, requestEvent);
        } catch (JsonProcessingException e) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, e, requestEvent);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new BusinessException(CommonErrorCode.BAD_PARAM_EXCEPTION, e, requestEvent);
        } catch (Exception e) {
            throw new BusinessException(CiscoDataErrorCode.CISCO_DATA_GENERAL_EXCEPTION, e, requestEvent);
        }
    }

    @Override
    public String getAllGroupAlarmSummary(RequestEvent requestEvent) throws BusinessException {
        try {
            ResponseEntity<Object> responseEntity = alarmCiscoDataAccessorService.getAllGroupAlarmSummary();
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return JsonUtil.getJsonString(responseEntity.getBody(), JsonAutoDetect.Visibility.PUBLIC_ONLY);
            }
            throw new BusinessException(CiscoDataErrorCode.HTTP_REQUEST_EXCEPTION, responseEntity.getStatusCode().toString(), requestEvent);
        } catch (BusinessException e) {
            throw e;
        } catch (RestClientException e) {
            throw new BusinessException(CiscoDataErrorCode.REST_CONNECTION_EXCEPTION, e, requestEvent);
        } catch (JsonProcessingException e) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, e, requestEvent);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new BusinessException(CommonErrorCode.BAD_PARAM_EXCEPTION, e, requestEvent);
        } catch (Exception e) {
            throw new BusinessException(CiscoDataErrorCode.CISCO_DATA_GENERAL_EXCEPTION, e, requestEvent);
        }
    }

    @Override
    public String getAllAlarmDeviceGroups(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> rawMap = requestEvent.getCommand().getParams();
            Map<String, String> filteredParams = new HashMap<>();
            filteredParams.put(ApiParam.SORT, CiscoDataApiParam.DOT_SORT);
            filteredParams.put(ApiParam.NO_ALARMS, CiscoDataApiParam.NO_ALARMS);
            Map<String, String> qParams = CiscoDataParamUtil.getCiscoDataQueryParams(rawMap, filteredParams);
            ResponseEntity<Object> responseEntity = alarmCiscoDataAccessorService.getAllAlarmDeviceGroups(qParams);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return JsonUtil.getJsonString(responseEntity.getBody(), JsonAutoDetect.Visibility.PUBLIC_ONLY);
            }
            throw new BusinessException(CiscoDataErrorCode.HTTP_REQUEST_EXCEPTION, responseEntity.getStatusCode().toString(), requestEvent);
        } catch (BusinessException e) {
            throw e;
        } catch (RestClientException e) {
            throw new BusinessException(CiscoDataErrorCode.REST_CONNECTION_EXCEPTION, e, requestEvent);
        } catch (JsonProcessingException e) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, e, requestEvent);
        } catch (NullPointerException | IllegalArgumentException | ParseException e) {
            throw new BusinessException(CommonErrorCode.BAD_PARAM_EXCEPTION, e, requestEvent);
        } catch (Exception e) {
            throw new BusinessException(CiscoDataErrorCode.CISCO_DATA_GENERAL_EXCEPTION, e, requestEvent);
        }
    }
}
