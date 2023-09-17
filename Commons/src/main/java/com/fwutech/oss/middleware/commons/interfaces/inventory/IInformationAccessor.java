package com.fwutech.oss.middleware.commons.interfaces.inventory;

import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.enums.CommonErrorCode;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;

public interface IInformationAccessor {

    default String getContainedCurrentPorts(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getPortById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllNes(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getNeBoards(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getNe(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllTopLevelSubnetworks(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllSubnetworkFiberCables(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getMultiLayerSubnetwork(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllFlowDomains(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getProtectionGroup(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getFlowDomain(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllTopLevelSubnetworkNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getContainedBoards(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getBoard(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllNeNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllPhysicalPortNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getEms(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllPhysicalPorts(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getContainedCurrentPortNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getContainedPotentialPortNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getContainedPotentialPorts(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getContainingSubnetworkNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllSubnetworkConnectionNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllCrossConnections(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllNesBySubnetName(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllNeNamesBySubnetName(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    //Fana Data

    default String getAllTopLevelFiberCables(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    //Huawei data

    default String getAllPhysicalPortsWithoutLogicalPorts(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllSubnetworkFiberCableNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllPortSubnetworkConnections(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllEdgePoints(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getUserLabelTrailManagements(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllBoardNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllSupportedPhysicalPorts(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getTrailManagement(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllEdgePointNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllManagementDomains(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getManagementDomainById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getFiberCableById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllLogicalPorts(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllFlowDomainFragments(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllFlowDomainFragmentNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getInventory(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllSubnetworkConnections(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllTopologyNodes(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllPortSubnetworkConnectionNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllSupportedPhysicalPortNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllProtectionGroups(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllBoardProtectionGroups(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllSupportingBoards(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllSupportingBoardNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllNeInfosByIPs(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllExplicitPaths(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getSubnetworkConnectionById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllTunnelPolicies(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllTopLevelMultiLayerSubnetworks(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);

    }

    default String getAllSubnetworkTopologyNodes(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllRoutePolicies(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllIPPrefixes(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getIPPrefix(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getRoutePolicy(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getMPLSConfigInfo(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllInUsedServiceID(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getDHCPSnoopingConfigInfo(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllPublicRouteProcesses(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getManagedDomainUsers(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getFlowDomainFragment(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllDevices(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getDeviceById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getInventoryDetails(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getInventoryDetailById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllCliTemplatesConfigsDeviceTypes(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getClientDetails(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getClientDetailById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getClientSessions(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getClientSessionById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getClientSummary(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getClientSummaryById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllInventoryDeviceGroups(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getInventoryDeviceGroupById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllGroupSpecifications(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getGroupSpecificationById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllClientCounts(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getClientCountById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllGroupSites(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllUserDefinedGroups(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllBulkSanitizedConfigArchives(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getBulkSanitizedConfigArchiveById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllServiceDomains(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getServiceDomainById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllVdAssociatedDynamicGroups(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getVdAssociatedDynamicGroupById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllVdAssociatedDevices(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getVdAssociatedDeviceById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllApplications(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getApplicationById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }


    default String getAllCliTemplatesConfigsFolders(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllStatisticsResourceTypes(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllApiHealthRecords(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getApiHealthRecordById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllJobSummaries(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getInventoryById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getSwitches(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getSubnetworkFiberCable(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getTopLevelSubnetworkFiberCableById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllPhysicalPortNamesWithoutLogicalPorts(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllTopLevelSubnetworkFiberCableNames(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getSwitchById(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }

    default String getAllSubInterfaces(RequestEvent requestEvent) throws BusinessException {
        throw new BusinessException(CommonErrorCode.NOT_IMPLEMENTED, requestEvent);
    }
}