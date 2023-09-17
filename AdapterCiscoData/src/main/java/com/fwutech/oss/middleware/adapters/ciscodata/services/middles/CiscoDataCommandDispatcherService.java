package com.fwutech.oss.middleware.adapters.ciscodata.services.middles;

import com.fwutech.oss.middleware.adapters.ciscodata.beans.CiscoDataDto;
import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.beans.msgbroker.ResponseEvent;
import com.fwutech.oss.middleware.commons.dtos.BaseDto;
import com.fwutech.oss.middleware.commons.enums.CommonErrorCode;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import com.fwutech.oss.middleware.commons.interfaces.IDispatcherService;
import com.fwutech.oss.middleware.commons.interfaces.alarm.IAlarmAccessor;
import com.fwutech.oss.middleware.commons.interfaces.configuration.IConfigurationAccessor;
import com.fwutech.oss.middleware.commons.interfaces.inventory.IInformationAccessor;
import com.fwutech.oss.middleware.commons.interfaces.msgbroker.producer.IResponseEventMessageProducer;
import com.fwutech.oss.middleware.commons.interfaces.performance.IPerformanceAccessor;
import com.fwutech.oss.middleware.commons.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value = "ciscoDataCommandDispatcherService")
public class CiscoDataCommandDispatcherService
        implements IDispatcherService {

    @Autowired
    @Qualifier(value = "ciscoDataResponseEventMessageProduceService")
    private IResponseEventMessageProducer ciscoDataResponseEventMessageProduceService;
    @Autowired
    @Qualifier(value = "ciscoDataAlarmAccessorService")
    private IAlarmAccessor ciscoDataAlarmAccessorService;
    @Autowired
    @Qualifier(value = "ciscoDataConfigurationAccessorService")
    private IConfigurationAccessor ciscoDataConfigurationAccessorService;
    @Autowired
    @Qualifier(value = "ciscoDataInformationAccessorService")
    private IInformationAccessor ciscoDataInformationAccessorService;
    @Autowired
    @Qualifier(value = "ciscoDataPerformanceAccessorService")
    private IPerformanceAccessor ciscoDataPerformanceAccessorService;

    public ResponseEvent<BaseDto> dispatchRequestEvent(RequestEvent requestEvent) throws BusinessException {
        Object result = null;
        switch (requestEvent.getCommand().getCommandType()) {
            case INVENTORY_GET_ALL_DEVICES:
                result = ciscoDataInformationAccessorService.getAllDevices(requestEvent);
                break;
            case INVENTORY_GET_DEVICE_BY_ID:
                result = ciscoDataInformationAccessorService.getDeviceById(requestEvent);
                break;
            case INVENTORY_GET_INVENTORY_DETAILS:
                result = ciscoDataInformationAccessorService.getInventoryDetails(requestEvent);
                break;
            case INVENTORY_GET_INVENTORY_DETAILS_BY_ID:
                result = ciscoDataInformationAccessorService.getInventoryDetailById(requestEvent);
                break;
            case INVENTORY_GET_ALL_CLI_TEMPLATES_CONFIGS_DEVICE_TYPES:
                result = ciscoDataInformationAccessorService.getAllCliTemplatesConfigsDeviceTypes(requestEvent);
                break;
            case INVENTORY_GET_CLIENT_DETAILS:
                result = ciscoDataInformationAccessorService.getClientDetails(requestEvent);
                break;
            case INVENTORY_GET_CLIENT_DETAIL_BY_ID:
                result = ciscoDataInformationAccessorService.getClientDetailById(requestEvent);
                break;
            case INVENTORY_GET_CLIENT_SESSIONS:
                result = ciscoDataInformationAccessorService.getClientSessions(requestEvent);
                break;
            case INVENTORY_GET_CLIENT_SESSION_BY_ID:
                result = ciscoDataInformationAccessorService.getClientSessionById(requestEvent);
                break;
            case INVENTORY_GET_CLIENTS_SUMMARY:
                result = ciscoDataInformationAccessorService.getClientSummary(requestEvent);
                break;
            case INVENTORY_GET_CLIENT_SUMMARY_BY_ID:
                result = ciscoDataInformationAccessorService.getClientSummaryById(requestEvent);
                break;
            case INVENTORY_GET_All_DEVICE_GROUPS:
                result = ciscoDataInformationAccessorService.getAllInventoryDeviceGroups(requestEvent);
                break;
            case INVENTORY_GET_DEVICE_GROUP_BY_ID:
                result = ciscoDataInformationAccessorService.getInventoryDeviceGroupById(requestEvent);
                break;
            case INVENTORY_GET_All_GROUP_SPECIFICATIONS:
                result = ciscoDataInformationAccessorService.getAllGroupSpecifications(requestEvent);
                break;
            case INVENTORY_GET_GROUP_SPECIFICATION_BY_ID:
                result = ciscoDataInformationAccessorService.getGroupSpecificationById(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_METRICS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesMetrics(requestEvent);
                break;
            case ALARM_GET_ALL_EMS_AND_NE_ACTIVE_ALARMS:
                result = ciscoDataAlarmAccessorService.getAllEmsAndNeActiveAlarms(requestEvent);
                break;
            case ALARM_GET_ALARM_BY_ID:
                result = ciscoDataAlarmAccessorService.getAlarmById(requestEvent);
                break;
            case ALARM_GET_ALL_EVENTS:
                result = ciscoDataAlarmAccessorService.getAllEvents(requestEvent);
                break;
            case ALARM_GET_EVENT_BY_ID:
                result = ciscoDataAlarmAccessorService.getEventById(requestEvent);
                break;
            case INVENTORY_GET_ALL_CLIENT_COUNTS:
                result = ciscoDataInformationAccessorService.getAllClientCounts(requestEvent);
                break;
            case INVENTORY_GET_CLIENT_COUNT_BY_ID:
                result = ciscoDataInformationAccessorService.getClientCountById(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_CLIENT_TRAFFICS:
                result = ciscoDataPerformanceAccessorService.getAllClientTraffics(requestEvent);
                break;
            case PERFORMANCE_GET_CLIENT_TRAFFIC_BY_ID:
                result = ciscoDataPerformanceAccessorService.getClientTrafficById(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_METRICS_OUTDISCARDS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesMetricsOutdiscards(requestEvent);
                break;
            case INVENTORY_GET_ALL_GROUP_SITES:
                result = ciscoDataInformationAccessorService.getAllGroupSites(requestEvent);
                break;
            case ALARM_GET_ALL_GROUP_ALARM_SUMMARY:
                result = ciscoDataAlarmAccessorService.getAllGroupAlarmSummary(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_METRICS_OUTERRORS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesMetricsOuterrors(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_METRICS_INDISCARDS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesMetricsIndiscards(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_METRICS_TX:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesMetricsTx(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_METRICS_TOPNAPPLICATION:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesMetricsTopnapplication(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_METRICS_RX:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesMetricsRx(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_METRICS_QOSCLASSMAP:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesMetricsQosclassmap(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_METRICS_AVAILABILITY:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesMetricsAvailability(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_METRICS_TOPAPPLICATIONTRAFFICOVERTIME:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesMetricsTopapplicationtrafficovertime(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_METRICS_INERRORS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesMetricsInerrors(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_METRICS_QOSCLASSMAPTOPTREND:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesMetricsQosclassmaptoptrend(requestEvent);
                break;
            case ALARM_GET_ALL_DEVICE_GROUPS:
                result = ciscoDataAlarmAccessorService.getAllAlarmDeviceGroups(requestEvent);
                break;
            case INVENTORY_GET_ALL_USER_DEFINED_GROUPS:
                result = ciscoDataInformationAccessorService.getAllUserDefinedGroups(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_DEVICES_METRICS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsDevicesMetrics(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_DEVICES_METRICS_AVAILABILITY:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsDevicesMetricsAvailability(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_APPLICATION_TRAFFIC_ANALYSIS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsApplicationTrafficAnalysis(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_DETAILS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesDetails(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_DEVICES_METRICS_CPUUTILIZATION:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsDevicesMetricsCpuutilization(requestEvent);
                break;
            case INVENTORY_GET_ALL_BULK_SANITIZED_CONFIG_ARCHIVES:
                result = ciscoDataInformationAccessorService.getAllBulkSanitizedConfigArchives(requestEvent);
                break;
            case INVENTORY_GET_BULK_SANITIZED_CONFIG_ARCHIVE_BY_ID:
                result = ciscoDataInformationAccessorService.getBulkSanitizedConfigArchiveById(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_DISCARDS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesDiscards(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_ERRORS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesErrors(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_UTILS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesUtils(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_HISTORICAL_CLIENT_COUNTS:
                result = ciscoDataPerformanceAccessorService.getAllHistoricalClientCounts(requestEvent);
                break;
            case PERFORMANCE_GET_HISTORICAL_CLIENT_COUNT_BY_ID:
                result = ciscoDataPerformanceAccessorService.getHistoricalClientCountById(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_INFO_DISKS:
                result = ciscoDataPerformanceAccessorService.getAllInfoDisks(requestEvent);
                break;
            case INVENTORY_GET_ALL_SERVICE_DOMAINS:
                result = ciscoDataInformationAccessorService.getAllServiceDomains(requestEvent);
                break;
            case INVENTORY_GET_SERVICE_DOMAIN_BY_ID:
                result = ciscoDataInformationAccessorService.getServiceDomainById(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_DEVICE_CPU_UTIL_SUMMARIES:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsDeviceCpuUtilSummaries(requestEvent);
                break;
            case INVENTORY_GET_ALL_VD_ASSOCIATED_DYNAMIC_GROUPS:
                result = ciscoDataInformationAccessorService.getAllVdAssociatedDynamicGroups(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_DEVICES_AVAILABILITIES:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsDevicesAvailabilities(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_DEVICES_HEALTH_INFO:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsDevicesHealthInfo(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_DEVICES_MEMORY_UTIL_TRENDS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsDevicesMemoryUtilTrends(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_DEVICES_AVAILABILITY_MESSAGES:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsDevicesAvailabilityMessages(requestEvent);
                break;
            case INVENTORY_GET_ALL_VD_ASSOCIATED_DEVICES:
                result = ciscoDataInformationAccessorService.getAllVdAssociatedDevices(requestEvent);
                break;
            case INVENTORY_GET_VD_ASSOCIATED_DEVICE_BY_ID:
                result = ciscoDataInformationAccessorService.getVdAssociatedDeviceById(requestEvent);
                break;
            case INVENTORY_GET_ALL_APPLICATIONS:
                result = ciscoDataInformationAccessorService.getAllApplications(requestEvent);
                break;
            case INVENTORY_GET_APPLICATION_BY_ID:
                result = ciscoDataInformationAccessorService.getApplicationById(requestEvent);
                break;
            case INVENTORY_GET_VD_ASSOCIATED_DYNAMIC_GROUP_BY_ID:
                result = ciscoDataInformationAccessorService.getVdAssociatedDynamicGroupById(requestEvent);
                break;
            case PERFORMANCE_GET_INFO_VERSIONS:
                result = ciscoDataPerformanceAccessorService.getInfoVersions(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_CLIENTS_DISTRIBUTIONS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsClientsDistributions(requestEvent);
                break;
            case PERFORMANCE_GET_INFO_UPTIMES:
                result = ciscoDataPerformanceAccessorService.getInfoUptimes(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_DEVICES_CPU_UTIL_TRENDS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsServicesDevicesCpuUtilTrends(requestEvent);
                break;
            case INVENTORY_GET_ALL_CLI_TEMPLATES_CONFIGS_FOLDERS:
                result = ciscoDataInformationAccessorService.getAllCliTemplatesConfigsFolders(requestEvent);
                break;
            case INVENTORY_GET_ALL_STATISTICS_RESOURCE_TYPES:
                result = ciscoDataInformationAccessorService.getAllStatisticsResourceTypes(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_DEVICES_AVAILABILITY_SUMMARIES:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsDevicesAvailabilitySummaries(requestEvent);
                break;
            case INVENTORY_GET_ALL_API_HEALTH_RECORDS:
                result = ciscoDataInformationAccessorService.getAllApiHealthRecords(requestEvent);
                break;
            case INVENTORY_GET_API_HEALTH_RECORD_BY_ID:
                result = ciscoDataInformationAccessorService.getApiHealthRecordById(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_AVAILABILITY_SUMMARIES:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesAvailabilitySummaries(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_HISTORICAL_CLIENT_TRAFFICS:
                result = ciscoDataPerformanceAccessorService.getAllHistoricalClientTraffics(requestEvent);
                break;
            case PERFORMANCE_GET_HISTORICAL_CLIENT_TRAFFIC_BY_ID:
                result = ciscoDataPerformanceAccessorService.getHistoricalClientTrafficById(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_STATUS_SUMMARIES:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesStatusSummaries(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_UTIL_SUMMARIES:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesUtilSummaries(requestEvent);
                break;
            case INVENTORY_GET_ALL_JOB_SUMMARIES:
                result = ciscoDataInformationAccessorService.getAllJobSummaries(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_DEVICES_REACHABILITY_STATUS:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsDevicesReachabilityStatus(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_DEVICES_PORT_SUMMARIES:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsDevicesPortSummaries(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES_AVAILABILITIES:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfacesAvailabilities(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_CLIENTS_DISTRIBUTIONS_COUNT_BY_EAP_TYPES:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsClientsDistributionsCountByEapTypes(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_CLIENTS_DISTRIBUTIONS_COUNT_BY_SWITCHES:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsClientsDistributionsCountBySwitches(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_CLIENTS_DISTRIBUTIONS_COUNT_BY_AUTH_TYPES:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsClientsDistributionsCountByAuthTypes(requestEvent);
                break;
            case PERFORMANCE_GET_ALL_STATISTICS_INTERFACES:
                result = ciscoDataPerformanceAccessorService.getAllStatisticsInterfaces(requestEvent);
                break;
            case PERFORMANCE_GET_INFO_COREDUMPS:
                result = ciscoDataPerformanceAccessorService.getInfoCoredumps(requestEvent);
                break;
            case PERFORMANCE_GET_INFO_LICENSES:
                result = ciscoDataPerformanceAccessorService.getInfoLicenses(requestEvent);
                break;
            default:
                throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
        }

        ResponseEvent responseEvent = ResponseUtil.generateSuccessResponseEvent(requestEvent, result,
                new CiscoDataDto<>());
        ciscoDataResponseEventMessageProduceService.produceMessage(responseEvent);
        return responseEvent;
    }
}
