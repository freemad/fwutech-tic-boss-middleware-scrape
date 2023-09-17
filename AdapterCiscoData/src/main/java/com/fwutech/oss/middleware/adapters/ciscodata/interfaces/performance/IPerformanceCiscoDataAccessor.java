package com.fwutech.oss.middleware.adapters.ciscodata.interfaces.performance;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.Map;

public interface IPerformanceCiscoDataAccessor {

    ResponseEntity<Object> getAllStatisticsInterfacesMetrics(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllClientTraffics(Map<String, String> qParams) throws RestClientException;

    ResponseEntity<Object> getClientTrafficById(String trafficId) throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesMetricsOutdiscards(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesMetricsOuterrors(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesMetricsIndiscards(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesMetricsTx(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesMetricsTopnapplication(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesMetricsRx(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesMetricsQosclassmap(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesMetricsAvailability(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesMetricsTopapplicationtrafficovertime(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesMetricsInerrors(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesMetricsQosclassmaptoptrend(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsDevicesMetrics(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsDevicesMetricsAvailability(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsApplicationTrafficAnalysis(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsDevicesMetricsCpuutilization(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesDiscards(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesErrors(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesUtils(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllHistoricalClientCounts(Map<String, String> qParams)
            throws RestClientException;


    ResponseEntity<Object> getHistoricalClientCountById(String clientCountId)
            throws RestClientException;


    ResponseEntity<Object> getAllInfoDisks() throws RestClientException;

    ResponseEntity<Object> getAllStatisticsDeviceCpuUtilSummaries(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsDevicesAvailabilities(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsDevicesHealthInfo(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsDevicesMemoryUtilTrends(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsDevicesAvailabilityMessages(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsClientsDistributions() throws RestClientException;

    ResponseEntity<Object> getAllStatisticsServicesDevicesCpuUtilTrends(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsDevicesAvailabilitySummaries(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllHistoricalClientTraffics(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getHistoricalClientTrafficById(String historicalClientTrafficId)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesDetails(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesAvailabilitySummaries(Map<String, String> qParams) throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesStatusSummaries(Map<String, String> qParams) throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesUtilSummaries(Map<String, String> qParams) throws RestClientException;

    ResponseEntity<Object> getAllStatisticsDevicesReachabilityStatus(Map<String, String> qParams) throws RestClientException;

    ResponseEntity<Object> getAllStatisticsDevicesPortSummaries(Map<String, String> qParams) throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfacesAvailabilities(Map<String, String> qParams) throws RestClientException;

    ResponseEntity<Object> getAllStatisticsClientsDistributionsCountByEapTypes(Map<String, String> qParams) throws RestClientException;

    ResponseEntity<Object> getAllStatisticsClientsDistributionsCountBySwitches(Map<String, String> qParams) throws RestClientException;

    ResponseEntity<Object> getAllStatisticsClientsDistributionsCountByAuthTypes(Map<String, String> qParams) throws RestClientException;

    ResponseEntity<Object> getAllStatisticsInterfaces(Map<String, String> qParams)
            throws RestClientException;

    ResponseEntity<Object> getInfoVersions() throws RestClientException;

    ResponseEntity<Object> getInfoUptimes() throws RestClientException;

    ResponseEntity<Object> getInfoCoredumps() throws RestClientException;

    ResponseEntity<Object> getInfoLicenses() throws RestClientException;
}
