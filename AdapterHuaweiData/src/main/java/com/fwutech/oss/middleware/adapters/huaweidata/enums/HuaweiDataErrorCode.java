package com.fwutech.oss.middleware.adapters.huaweidata.enums;

import com.fwutech.oss.middleware.commons.exceptions.IErrorCode;
import com.fwutech.oss.middleware.commons.globals.ResponseConstant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum HuaweiDataErrorCode implements IErrorCode {

    HUAWEI_DATA_BAD_PARAM_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 1,
            "HUAWEI DATA BAD PARAM EXCEPTION", "BAD PARAM EXCEPTION IN HUAWEI DATA ADAPTER"),
    REMOTE_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 2,
            "REMOTE_EXCEPTION", "REMOTE EXCEPTION IN HUAWEI DATA ADAPTER"),
    NOT_FOUND_ENTITY_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 3,
            "NOT FOUND ENTITY EXCEPTION", "NOT FOUND ENTITY EXCEPTION IN HUAWEI DATA ADAPTER"),
    MANAGEMENT_DOMAIN_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 4,
            "MANAGEMENT DOMAIN EXCEPTION", "MANAGEMENT DOMAIN IN HUAWEI DATA ADAPTER"),
    MANAGEMENT_DOMAINS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 5,
            "MANAGEMENT DOMAINS EXCEPTION", "MANAGEMENT DOMAINS EXCEPTION IN HUAWEI DATA ADAPTER"),
    TOPOLOGICAL_LINKS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 6,
            "TOPOLOGICAL LINKS EXCEPTION", "TOPOLOGICAL LINKS EXCEPTION IN HUAWEI DATA ADAPTER"),
    TOPOLOGICAL_LINK_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 7,
            "TOPOLOGICAL LINKS EXCEPTION", "TOPOLOGICAL LINKS EXCEPTION IN HUAWEI DATA ADAPTER"),
    MANAGED_ELEMENTS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 8,
            "MANAGED ELEMENTS EXCEPTION", "MANAGED ELEMENTS EXCEPTION IN HUAWEI DATA ADAPTER"),
    PHYSICAL_TERMINATION_POINTS_WITHOUT_FTPS_EXCEPTION(
            ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 9,
            "PHYSICAL TERMINATION POINTS WITHOUT FTPS EXCEPTION",
            "PHYSICAL TERMINATION POINTS WITHOUT FTPS EXCEPTION IN HUAWEI DATA ADAPTER"),
    FLOATING_TERMINATION_POINTS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 10,
            "FLOATING TERMINATION POINTS EXCEPTION",
            "FLOATING TERMINATION POINTS EXCEPTION IN HUAWEI DATA ADAPTER"),
    PHYSICAL_TERMINATION_POINT_NAMES_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 11,
            "PHYSICAL TERMINATION POINT NAMES EXCEPTION",
            "PHYSICAL TERMINATION POINT NAMES EXCEPTION IN HUAWEI DATA ADAPTER"),
    FLOW_DOMAIN_FRAGMENTS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 12,
            "FLOW DOMAIN FRAGMENTS EXCEPTION",
            "FLOW DOMAIN FRAGMENTS EXCEPTION IN HUAWEI DATA ADAPTER"),
    FLOW_DOMAIN_FRAGMENT_NAMES_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 13,
            "FLOW DOMAIN FRAGMENT NAMES EXCEPTION",
            "FLOW DOMAIN FRAGMENT NAMES EXCEPTION IN HUAWEI DATA ADAPTER"),
    INVENTORY_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 14,
            "INVENTORY EXCEPTION", "INVENTORY EXCEPTION IN HUAWEI DATA ADAPTER"),
    SUBNETWORK_CONNECTIONS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 15,
            "SUBNETWORK CONNECTIONS EXCEPTION",
            "SUBNETWORK CONNECTIONS EXCEPTION IN HUAWEI DATA ADAPTER"),
    TOPO_VIEW_NODES_INFO_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 16,
            "TOPO VIEW NODES INFO EXCEPTION", "TOPO VIEW NODES INFO EXCEPTION IN HUAWEI DATA ADAPTER"),
    MEMBER_TERMINATION_POINTS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 17,
            "MEMBER TERMINATION POINTS EXCEPTION", "MEMBER TERMINATION POINTS EXCEPTION IN HUAWEI DATA ADAPTER"),
    TERMINATION_POINT_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 18,
            "TERMINATION POINT EXCEPTION", "TERMINATION POINT EXCEPTION IN HUAWEI DATA ADAPTER"),
    MANAGED_ELEMENT_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 19,
            "MANAGED ELEMENT EXCEPTION", "MANAGED ELEMENT EXCEPTION IN HUAWEI DATA ADAPTER"),
    CONTAINED_EQUIPMENT_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 20,
            "CONTAINED EQUIPMENT EXCEPTION", "CONTAINED EQUIPMENT EXCEPTION IN HUAWEI DATA ADAPTER"),
    EQUIPMENT_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 21,
            "EQUIPMENT EXCEPTION", "EQUIPMENT EXCEPTION IN HUAWEI DATA ADAPTER"),
    MANAGED_ELEMENT_NAMES_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 22,
            "MANAGED ELEMENT NAMES EXCEPTION", "MANAGED ELEMENT NAMES EXCEPTION IN HUAWEI DATA ADAPTER"),
    MANAGED_ELEMENT_INFOS_BY_IPS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 23,
            "MANAGED ELEMENT INFOS BY IPS EXCEPTION", "MANAGED ELEMENT INFOS BY IPS EXCEPTION IN HUAWEI DATA ADAPTER"),
    EXPLICIT_PATHS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 24,
            "EXPLICIT PATHS EXCEPTION", "EXPLICIT PATHS EXCEPTION IN HUAWEI DATA ADAPTER"),
    SUBNETWORK_CONNECTION_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 25,
            "SUBNETWORK CONNECTION EXCEPTION", "SUBNETWORK CONNECTION EXCEPTION IN HUAWEI DATA ADAPTER"),
    SUBNETWORK_CONNECTION_NAMES_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 26,
            "SUBNETWORK CONNECTION NAMES EXCEPTION", "SUBNETWORK CONNECTION NAMES EXCEPTION IN HUAWEI DATA ADAPTER"),
    ACTIVE_ALARMS_COUNT_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 27,
            "ACTIVE ALARMS COUNT EXCEPTION", "ACTIVE ALARMS COUNT EXCEPTION IN HUAWEI DATA ADAPTER"),
    ROUTE_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 28,
            "ROUTE EXCEPTION", "ROUTE EXCEPTION IN HUAWEI DATA ADAPTER"),
    HUAWEI_DATA_PRODUCE_RESPONSE_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 29,
            "HUAWEI DATA PRODUCE RESPONSE EXCEPTION", "PRODUCE RESPONSE EXCEPTION IN HUAWEI DATA ADAPTER"),
    SUBNETWORK_CONNECTIONS_WITH_TP_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 30,
            "SUBNETWORK CONNECTIONS WITH TP EXCEPTION",
            "SUBNETWORK CONNECTIONS WITH TP EXCEPTION IN HUAWEI DATA ADAPTER"),
    TUNNEL_POLICIES_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 31,
            "TUNNEL POLICIES EXCEPTION", "TUNNEL POLICIES EXCEPTION IN HUAWEI DATA ADAPTER"),
    ALARMS_COUNT_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 32,
            "ALARMS COUNT EXCEPTION", "ALARMS COUNT EXCEPTION IN HUAWEI DATA ADAPTER"),
    TOP_LEVEL_MULTILAYER_SUBNETWORKS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 33,
            "TOP LEVEL MULTILAYER SUBNETWORKS EXCEPTION",
            "TOP LEVEL MULTILAYER SUBNETWORKS EXCEPTION IN HUAWEI DATA ADAPTER"),
    MULTILAYER_SUBNETWORK_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 34,
            "MULTILAYER SUBNETWORK EXCEPTION", "MULTILAYER SUBNETWORK EXCEPTION IN HUAWEI DATA ADAPTER"),
    TOPO_NODE_LIST_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 35,
            "TOPO NODE LIST EXCEPTION", "TOPO NODE LIST EXCEPTION IN HUAWEI DATA ADAPTER"),
    ROUTE_POLICIES_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 36,
            "ROUTE POLICIES EXCEPTION", "ROUTE POLICIES EXCEPTION IN HUAWEI DATA ADAPTER"),
    IP_PREFIXES_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 37,
            "IP PREFIXES EXCEPTION", "IP PREFIXES EXCEPTION IN HUAWEI DATA ADAPTER"),
    FLOW_DOMAINS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 38,
            "FLOW DOMAINS EXCEPTION", "FLOW DOMAINS EXCEPTION IN HUAWEI DATA ADAPTER"),
    IP_PREFIX_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 39,
            "IP PREFIX EXCEPTION", "IP PREFIX EXCEPTION IN HUAWEI DATA ADAPTER"),
    ROUTE_POLICY_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 40,
            "ROUTE POLICY EXCEPTION", "ROUTE POLICY EXCEPTION IN HUAWEI DATA ADAPTER"),
    MPLS_CONFIG_INFO_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 41,
            "MPLS CONFIG INFO EXCEPTION", "MPLS CONFIG INFO EXCEPTION IN HUAWEI DATA ADAPTER"),
    IN_USED_SERVICE_ID_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 42,
            "IN USED SERVICE ID EXCEPTION", "IN USED SERVICE ID EXCEPTION IN HUAWEI DATA ADAPTER"),
    DHCP_SNOOPING_CONFIG_INFO_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 43,
            "DHCP SNOOPING CONFIG INFO EXCEPTION", "DHCP SNOOPING CONFIG INFO EXCEPTION IN HUAWEI DATA ADAPTER"),
    PUBLIC_ROUTE_PROCESSES_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 44,
            "PUBLIC ROUTE PROCESSES EXCEPTION", "PUBLIC ROUTE PROCESSES EXCEPTION IN HUAWEI DATA ADAPTER"),
    PERFORMANCE_INSTANCE_BY_RESOURCE_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 45,
            "PERFORMANCE INSTANCE BY RESOURCE EXCEPTION", "PERFORMANCE INSTANCE BY RESOURCE EXCEPTION IN HUAWEI DATA ADAPTER"),
    MANAGED_DOMAIN_USERS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 46,
            "MANAGED DOMAIN USERS EXCEPTION", "MANAGED DOMAIN USERS EXCEPTION IN HUAWEI DATA ADAPTER"),
    FLOW_DOMAIN_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 47,
            "FLOW DOMAIN EXCEPTION", "FLOW DOMAIN EXCEPTION IN HUAWEI DATA ADAPTER"),
    ACTIVE_ALARMS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 48,
            "ACTIVE ALARMS EXCEPTION", "ACTIVE ALARMS EXCEPTION IN HUAWEI DATA ADAPTER"),
    THRESHOLD_CROSSING_ALERTS_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 49,
            "THRESHOLD CROSSING ALERTS EXEPTION", "THRESHOLD CROSSING ALERTS EXEPTION IN HUAWEI DATA ADAPTER"),
    HUAWEI_DATA_GENERAL_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 50,
            "HUAWEI DATA GENERAL EXCEPTION", "GENERAL EXCEPTION IN HUAWEI DATA ADAPTER"),
    CURRENT_PERFORMANCE_MONITORING_DATA_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 51,
            "CURRENT PERFORMANCE MONITORING DATA EXCEPTION", "CURRENT PERFORMANCE MONITORING DATA EXCEPTION IN HUAWEI DATA ADAPTER"),
    SYNC_ACTIVE_ALARM_FROM_NE_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 52,
            "SYNC ACTIVE ALARM FROM NE EXCEPTION", "SYNC ACTIVE ALARM FROM NE EXCEPTION IN HUAWEI DATA ADAPTER"),
    FLOW_DOMAIN_FRAGMENT_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 53,
            "FLOW DOMAIN FRAGMENT EXCEPTION", "FLOW DOMAIN FRAGMENT EXCEPTION IN HUAWEI DATA ADAPTER"),
    HISTORY_PERFORMANCE_MONITORING_DATA_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 54,
            "HISTORY PERFORMANCE MONITORING DATA EXCEPTION", "HISTORY PERFORMANCE MONITORING DATA EXCEPTION IN HUAWEI DATA ADAPTER"),
    SOAP_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 55,
            "SOAP EXCEPTION", "SOAP EXCEPTION IN HUAWEI DATA ADAPTER"),
    IO_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 56,
            "IO EXCEPTION", "IO EXCEPTION IN HUAWEI DATA ADAPTER"),
    HUAWEI_DATA_REQUEST_EXCEPTION(ResponseConstant.HUAWEI_DATA_ERROR_CODE_START_INDEX + 57,
            "HUAWEI DATA REQUEST EXCEPTION", "REQUEST EXCEPTION IN HUAWEI DATA ADAPTER");

    private int code;
    private String message;
    private String detail;

    HuaweiDataErrorCode(int code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDetail() {
        return detail;
    }

    public static List<HuaweiDataErrorCode> getCodesAsList() {
        return Stream.of(HuaweiDataErrorCode.values()).collect(Collectors.toList());
    }
}