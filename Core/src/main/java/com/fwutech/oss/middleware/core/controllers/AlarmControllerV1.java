package com.fwutech.oss.middleware.core.controllers;

import com.fwutech.oss.middleware.commons.beans.base.Agent;
import com.fwutech.oss.middleware.commons.beans.base.Command;
import com.fwutech.oss.middleware.commons.beans.base.Receipt;
import com.fwutech.oss.middleware.commons.enums.CommandType;
import com.fwutech.oss.middleware.commons.globals.ApiParam;
import com.fwutech.oss.middleware.commons.globals.ApiRoute;
import com.fwutech.oss.middleware.commons.responses.Result;
import com.fwutech.oss.middleware.core.globals.DefaultParamValue;
import com.fwutech.oss.middleware.core.intefaces.IAuthenticationService;
import com.fwutech.oss.middleware.core.intefaces.IEnrichmentService;
import com.fwutech.oss.middleware.core.utils.Base62Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AlarmControllerV1 extends BaseControllerV1 {

    @Autowired
    private IAuthenticationService authenticationService;
    @Autowired
    private IEnrichmentService enrichmentService;

    /**
     * combined-params
     */
    @GetMapping(value = ApiRoute.ALARMS)
    public Result<Receipt> getAllEmsAndNeActiveAlarms(
            RequestEntity<String> request,
            @RequestHeader(value = ApiParam.NEXT_PAGE_TOKEN, required = false) String nextPageToken,
            @PathVariable(name = ApiRoute.VENDOR_ID) Integer vendorId,
            @PathVariable(name = ApiRoute.NETWORK_ID) Integer networkId,
            @RequestParam(value = ApiParam.EXCLUDE_PROB_CAUSES, required = false) String excludeProbCauses,
            @RequestParam(value = ApiParam.EXCLUDE_SEVERITIES, required = false) String excludeSeverities,
            @RequestParam(value = ApiParam.HD_PARAMS, required = false) String hdParams,
            @RequestParam(value = ApiParam.ND_PARAMS, required = false) String ndParams,
            @RequestParam(name = ApiParam.FULL, required = false, defaultValue = DefaultParamValue.CISCO_DATA_DEFAULT_DOT_FULL) String full,
            @RequestParam(name = ApiParam.PAGE_INDEX, required = false, defaultValue = DefaultParamValue.CISCO_DATA_DEFAULT_PAGE_INDEX) String pageIndex,
            @RequestParam(name = ApiParam.PAGE_SIZE, required = false, defaultValue = DefaultParamValue.CISCO_DATA_DEFAULT_PAGE_SIZE) String pageSize,
            @RequestParam(name = ApiParam.GROUP, required = false) String group,
            @RequestParam(name = ApiParam.SORT, required = false) String sort,
            @RequestParam(name = ApiParam.INSTANCE, required = false) String instance) {
        Result<Receipt> result = wrapInBusinessException(() -> {
            Map<String, String> params = new HashMap<>();
            params.put(ApiParam.EXCLUDE_PROB_CAUSES, excludeProbCauses);
            params.put(ApiParam.EXCLUDE_SEVERITIES, excludeSeverities);
            params.put(ApiParam.HD_PARAMS, hdParams != null ? Base62Util.decode(hdParams) : null);
            params.put(ApiParam.ND_PARAMS, ndParams != null ? Base62Util.decode(ndParams) : null);
            params.put(ApiParam.PAGE_SIZE, pageSize);
            params.put(ApiParam.PAGE_INDEX, pageIndex);
            params.put(ApiParam.FULL, full);
            params.put(ApiParam.GROUP, group);
            params.put(ApiParam.SORT, sort);
            params.put(ApiParam.INSTANCE, instance);
            Agent agent = authenticationService.authenticate(request);
            return enrichmentService.enrichRequest(request, agent,
                    new Command(CommandType.ALARM_GET_ALL_EMS_AND_NE_ACTIVE_ALARMS, params),
                    vendorId, networkId);
        });
        return result;
    }

    @GetMapping(value = ApiRoute.NES + "/{neId}" + ApiRoute.ALARMS)
    public Result<Receipt> getAllActiveAlarms(
            RequestEntity<String> request,
            @PathVariable(name = ApiRoute.VENDOR_ID) Integer vendorId,
            @PathVariable(name = ApiRoute.NETWORK_ID) Integer networkId,
            @PathVariable(name = ApiRoute.NE_ID) String neId,
            @RequestParam(value = ApiParam.EXCLUDE_PROB_CAUSES, required = false) String excludeProbCauses,
            @RequestParam(value = ApiParam.EXCLUDE_SEVERITIES, required = false) String excludeSeverities,
            @RequestParam(value = ApiParam.PAGE_SIZE, required = false,
                    defaultValue = DefaultParamValue.HUAWEI_DATA_ALARM_MIN_PAGE_SIZE) String pageSize,
            @RequestParam(value = ApiParam.PAGE_INDEX, required = false,
                    defaultValue = DefaultParamValue.HUAWEI_DATA_DEFAULT_PAGE_INDEX) String pageIndex) {
        Result<Receipt> result = wrapInBusinessException(() -> {
            Map<String, String> params = new HashMap<>();
            params.put(ApiRoute.NE_ID, Base62Util.decode(neId));
            params.put(ApiParam.EXCLUDE_PROB_CAUSES, excludeProbCauses);
            params.put(ApiParam.EXCLUDE_SEVERITIES, excludeSeverities);
            params.put(ApiParam.PAGE_SIZE, pageSize);
            params.put(ApiParam.PAGE_INDEX, pageIndex);
            Agent agent = authenticationService.authenticate(request);
            return enrichmentService.enrichRequest(
                    request,
                    agent,
                    new Command(CommandType.ALARM_GET_ALL_ACTIVE_ALARMS, params),
                    vendorId, networkId);
        });
        return result;
    }

    @GetMapping(value = ApiRoute.NES + "/{neId}" + ApiRoute.HISTORY_ALARMS)
    public Result<Receipt> getNeHistoryAlarms(
            RequestEntity<String> request,
            @PathVariable(name = ApiRoute.VENDOR_ID) Integer vendorId,
            @PathVariable(name = ApiRoute.NETWORK_ID) Integer networkId,
            @PathVariable(name = ApiRoute.NE_ID) String neId,
            @RequestParam(name = ApiParam.EXCLUDE_PROB_CAUSES, required = false) String excludeProbCauses,
            @RequestParam(value = ApiParam.EXCLUDE_SEVERITIES, required = false) String excludeSeverities,
            @RequestParam(value = ApiParam.BEGIN_TIME, required = false) String
                    beginTime, //such as 20070301T130000000000Z
            @RequestParam(value = ApiParam.END_TIME, required = false) String endTime,
            @RequestParam(value = ApiParam.PAGE_SIZE, required = false,
                    defaultValue = DefaultParamValue.HUAWEI_DATA_ALARM_MIN_PAGE_SIZE) String pageSize,
            @RequestParam(value = ApiParam.PAGE_INDEX, required = false,
                    defaultValue = DefaultParamValue.HUAWEI_DATA_DEFAULT_PAGE_INDEX) String pageIndex) {
        Result<Receipt> result = wrapInBusinessException(() -> {
            Map<String, String> params = new HashMap<>();
            params.put(ApiParam.PAGE_SIZE, pageSize);
            params.put(ApiParam.PAGE_INDEX, pageIndex);
            params.put(ApiRoute.NE_ID, Base62Util.decode(neId));
            params.put(ApiParam.EXCLUDE_PROB_CAUSES, excludeProbCauses);
            params.put(ApiParam.EXCLUDE_SEVERITIES, excludeSeverities);
            params.put(ApiParam.BEGIN_TIME, beginTime);
            params.put(ApiParam.END_TIME, endTime);
            Agent agent = authenticationService.authenticate(request);
            return enrichmentService.enrichRequest(request,
                    agent,
                    new Command(CommandType.ALARM_GET_NE_HISTORY_ALARMS, params),
                    vendorId, networkId);
        });
        return result;
    }

    /**
     * combined-params
     */
    @GetMapping(value = ApiRoute.ACTIVE_ALARM_COUNT)
    public Result<Receipt> getActiveAlarmsCount(
            RequestEntity<String> request,
            @PathVariable(name = ApiRoute.VENDOR_ID) Integer vendorId,
            @PathVariable(name = ApiRoute.NETWORK_ID) Integer networkId,
            @RequestParam(value = ApiParam.HD_PARAMS, required = false) String hdParams,
            @RequestParam(value = ApiParam.ND_PARAMS, required = false) String ndParams) {
        Result<Receipt> result = wrapInBusinessException(() -> {
            Map<String, String> params = new HashMap<>();
            params.put(ApiParam.HD_PARAMS, hdParams != null ? Base62Util.decode(hdParams) : null);
            params.put(ApiParam.ND_PARAMS, ndParams != null ? Base62Util.decode(ndParams) : null);
            Agent agent = authenticationService.authenticate(request);
            return enrichmentService.enrichRequest(
                    request,
                    agent,
                    new Command(CommandType.ALARM_GET_ACTIVE_ALARMS_COUNT, params),
                    vendorId, networkId);
        });
        return result;
    }

    @GetMapping(value = ApiRoute.EMS_SYSTEM_ACTIVE_ALARMS)
    public Result<Receipt> getAllEmsSystemActiveAlarms(
            RequestEntity<String> request,
            @PathVariable(name = ApiRoute.VENDOR_ID) Integer vendorId,
            @PathVariable(name = ApiRoute.NETWORK_ID) Integer networkId,
            @RequestParam(value = ApiParam.PAGE_SIZE, required = false,
                    defaultValue = DefaultParamValue.HUAWEI_DATA_ALARM_MIN_PAGE_SIZE) String pageSize,
            @RequestParam(value = ApiParam.PAGE_INDEX, required = false,
                    defaultValue = DefaultParamValue.HUAWEI_DATA_DEFAULT_PAGE_INDEX) String pageIndex,
            @RequestParam(value = ApiParam.EXCLUDE_SEVERITIES, required = false,
                    defaultValue = "") String excludeSeverities) {
        Result<Receipt> result = wrapInBusinessException(() -> {
            Map<String, String> params = new HashMap<>();
            params.put(ApiParam.PAGE_SIZE, pageSize);
            params.put(ApiParam.PAGE_INDEX, pageIndex);
            params.put(ApiParam.EXCLUDE_SEVERITIES, excludeSeverities);
            Agent agent = authenticationService.authenticate(request);
            return enrichmentService.enrichRequest(
                    request,
                    agent,
                    new Command(CommandType.ALARM_GET_ALL_EMS_SYSTEM_ACTIVE_ALARMS, params),
                    vendorId, networkId);
        });
        return result;
    }

    /**
     * combined-params
     */
    @GetMapping(value = ApiRoute.ALARMS_COUNT)
    public Result<Receipt> getAlarmsCount(
            RequestEntity<String> request,
            @PathVariable(name = ApiRoute.VENDOR_ID) Integer vendorId,
            @PathVariable(name = ApiRoute.NETWORK_ID) Integer networkId,
            @RequestParam(name = ApiParam.HD_PARAMS, required = false) String hdParams,
            @RequestParam(name = ApiParam.ND_PARAMS, required = false) String ndParams) {
        Result<Receipt> result = wrapInBusinessException(() -> {
            Map<String, String> params = new HashMap<>();
            params.put(ApiParam.HD_PARAMS, hdParams != null ? Base62Util.decode(hdParams) : null);
            params.put(ApiParam.ND_PARAMS, ndParams != null ? Base62Util.decode(ndParams) : null);
            Agent agent = authenticationService.authenticate(request);
            return enrichmentService.enrichRequest(request, agent,
                    new Command(CommandType.ALARM_GET_ALARMS_COUNT, params),
                    vendorId, networkId);
        });
        return result;
    }


    /**
     * combined-params
     */
    @GetMapping(value = ApiRoute.THRESHOLD_CROSSING_ALERTS)
    public Result<Receipt> getThresholdCrossingAlerts(
            RequestEntity<String> request,
            @PathVariable(name = ApiRoute.VENDOR_ID) Integer vendorId,
            @PathVariable(name = ApiRoute.NETWORK_ID) Integer networkId,
            @RequestParam(value = ApiParam.HD_PARAMS, required = false) String hdParams,
            @RequestParam(value = ApiParam.ND_PARAMS, required = false) String ndParams) {
        Result<Receipt> result = wrapInBusinessException(() -> {
            Map<String, String> params = new HashMap<>();
            params.put(ApiParam.HD_PARAMS, hdParams != null ? Base62Util.decode(hdParams) : null);
            params.put(ApiParam.ND_PARAMS, ndParams != null ? Base62Util.decode(ndParams) : null);
            Agent agent = authenticationService.authenticate(request);
            return enrichmentService.enrichRequest(
                    request,
                    agent,
                    new Command(CommandType.ALARM_THRESHOLD_CROSSING_ALERTS, params),
                    vendorId, networkId);
        });
        return result;
    }

    @GetMapping(value = ApiRoute.SYNC_ACTIVE_ALARM_FROM_NE)
    public Result<Receipt> syncActiveAlarmFromNe(
            RequestEntity<String> request,
            @PathVariable(name = ApiRoute.VENDOR_ID) Integer vendorId,
            @PathVariable(name = ApiRoute.NETWORK_ID) Integer networkId,
            @RequestParam(value = ApiParam.HD_PARAMS, required = false) String hdParams,
            @RequestParam(value = ApiParam.ND_PARAMS, required = false) String ndParams) {
        Result<Receipt> result = wrapInBusinessException(() -> {
            Map<String, String> params = new HashMap<>();
            params.put(ApiParam.HD_PARAMS, hdParams != null ? Base62Util.decode(hdParams) : null);
            params.put(ApiParam.ND_PARAMS, ndParams != null ? Base62Util.decode(ndParams) : null);
            Agent agent = authenticationService.authenticate(request);
            return enrichmentService.enrichRequest(request, agent,
                    new Command(CommandType.ALARM_SYNC_ACTIVE_ALARM_FROM_NE, params),
                    vendorId, networkId);
        });
        return result;
    }

    @GetMapping(value = ApiRoute.ALARMS + "/{alarmId}")
    public Result<Receipt> getAlarmsById(
            RequestEntity<String> request,
            @PathVariable(name = ApiRoute.VENDOR_ID) Integer vendorId,
            @PathVariable(name = ApiRoute.NETWORK_ID) Integer networkId,
            @PathVariable(name = ApiRoute.ALARM_ID) String alarmId,
            @RequestParam(value = ApiParam.BEGIN_TIME, required = false) String beginTime,
            @RequestParam(value = ApiParam.END_TIME, required = false) String endTime) {
        Result<Receipt> result = wrapInBusinessException(() -> {
            Map<String, String> params = new HashMap<>();
            params.put(ApiRoute.ALARM_ID, alarmId);
            params.put(ApiParam.BEGIN_TIME, beginTime);
            params.put(ApiParam.END_TIME, endTime);
            Agent agent = authenticationService.authenticate(request);
            return enrichmentService.enrichRequest(request, agent,
                    new Command(CommandType.ALARM_GET_ALARM_BY_ID, params),
                    vendorId, networkId);
        });
        return result;
    }

    @GetMapping(value = ApiRoute.EVENTS)
    public Result<Receipt> getAllEvents(
            RequestEntity<String> request,
            @PathVariable(name = ApiRoute.VENDOR_ID) Integer vendorId,
            @PathVariable(name = ApiRoute.NETWORK_ID) Integer networkId,
            @RequestParam(name = ApiParam.FULL, required = false, defaultValue = DefaultParamValue.CISCO_DATA_DEFAULT_DOT_FULL) String full,
            @RequestParam(name = ApiParam.PAGE_INDEX, required = false, defaultValue = DefaultParamValue.CISCO_DATA_DEFAULT_PAGE_INDEX) String pageIndex,
            @RequestParam(name = ApiParam.PAGE_SIZE, required = false, defaultValue = DefaultParamValue.CISCO_DATA_DEFAULT_PAGE_SIZE) String pageSize,
            @RequestParam(name = ApiParam.GROUP, required = false) String group,
            @RequestParam(name = ApiParam.SORT, required = false) String sort,
            @RequestParam(name = ApiParam.INSTANCE, required = false) String instance) {
        Result<Receipt> result = wrapInBusinessException(() -> {
            Map<String, String> params = new HashMap<>();
            params.put(ApiParam.PAGE_SIZE, pageSize);
            params.put(ApiParam.PAGE_INDEX, pageIndex);
            params.put(ApiParam.FULL, full);
            params.put(ApiParam.PAGE_INDEX, pageIndex);
            params.put(ApiParam.PAGE_SIZE, pageSize);
            params.put(ApiParam.GROUP, group);
            params.put(ApiParam.SORT, sort);
            params.put(ApiParam.INSTANCE, instance);
            Agent agent = authenticationService.authenticate(request);
            return enrichmentService.enrichRequest(request, agent,
                    new Command(CommandType.ALARM_GET_ALL_EVENTS, params),
                    vendorId, networkId);
        });
        return result;
    }

    @GetMapping(value = ApiRoute.EVENTS + "/{eventId}")
    public Result<Receipt> getEventById(
            RequestEntity<String> request,
            @PathVariable(name = ApiRoute.VENDOR_ID) Integer vendorId,
            @PathVariable(name = ApiRoute.NETWORK_ID) Integer networkId,
            @PathVariable(name = ApiRoute.EVENT_ID) String eventId) {
        Result<Receipt> result = wrapInBusinessException(() -> {
            Map<String, String> params = new HashMap<>();
            params.put(ApiRoute.EVENT_ID, eventId);
            Agent agent = authenticationService.authenticate(request);
            return enrichmentService.enrichRequest(request, agent,
                    new Command(CommandType.ALARM_GET_EVENT_BY_ID, params),
                    vendorId, networkId);
        });
        return result;
    }

    @GetMapping(value = ApiRoute.GROUPS + ApiRoute.ALARM_SUMMARY)
    public Result<Receipt> getAllGroupAlarmSummary(
            RequestEntity<String> request,
            @PathVariable(name = ApiRoute.VENDOR_ID) Integer vendorId,
            @PathVariable(name = ApiRoute.NETWORK_ID) Integer networkId) {
        Result<Receipt> result = wrapInBusinessException(() -> {
            Map<String, String> params = new HashMap<>();
            Agent agent = authenticationService.authenticate(request);
            return enrichmentService.enrichRequest(request, agent,
                    new Command(CommandType.ALARM_GET_ALL_GROUP_ALARM_SUMMARY, params),
                    vendorId, networkId);
        });
        return result;
    }

    @GetMapping(value = ApiRoute.GROUPS + ApiRoute.DEVICE_GROUPS)
    public Result<Receipt> getAllAlarmDeviceGroups(
            RequestEntity<String> request,
            @PathVariable(name = ApiRoute.VENDOR_ID) Integer vendorId,
            @PathVariable(name = ApiRoute.NETWORK_ID) Integer networkId,
            @RequestParam(name = ApiParam.NO_ALARMS, required = false) String noAlarms,
            @RequestParam(name = ApiParam.PAGE_INDEX, required = false, defaultValue = DefaultParamValue.CISCO_DATA_DEFAULT_PAGE_INDEX) String pageIndex,
            @RequestParam(name = ApiParam.PAGE_SIZE, required = false, defaultValue = DefaultParamValue.CISCO_DATA_DEFAULT_PAGE_SIZE) String pageSize,
            @RequestParam(name = ApiParam.SORT, required = false) String sort) {
        Result<Receipt> result = wrapInBusinessException(() -> {
            Map<String, String> params = new HashMap<>();
            params.put(ApiParam.PAGE_SIZE, pageSize);
            params.put(ApiParam.PAGE_INDEX, pageIndex);
            params.put(ApiParam.NO_ALARMS, noAlarms);
            params.put(ApiParam.SORT, sort);
            Agent agent = authenticationService.authenticate(request);
            return enrichmentService.enrichRequest(request, agent,
                    new Command(CommandType.
                            ALARM_GET_ALL_DEVICE_GROUPS, params),
                    vendorId, networkId);
        });
        return result;
    }
}
