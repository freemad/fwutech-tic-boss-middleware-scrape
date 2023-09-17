package com.fwutech.oss.middleware.adapters.fiberhometransmit.interfaces.inventory;

import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import mtnm.tmforum.org.common.CapabilityList_THolder;
import mtnm.tmforum.org.emsMgr.EMS_THolder;
import mtnm.tmforum.org.equipment.EquipmentOrHolderList_THolder;
import mtnm.tmforum.org.equipment.EquipmentOrHolder_THolder;
import mtnm.tmforum.org.flowDomain.FDList_THolder;
import mtnm.tmforum.org.flowDomain.FlowDomain_THolder;
import mtnm.tmforum.org.globaldefs.NameAndStringValue_T;
import mtnm.tmforum.org.globaldefs.NamingAttributesList_THolder;
import mtnm.tmforum.org.globaldefs.ProcessingFailureException;
import mtnm.tmforum.org.managedElement.ManagedElementList_THolder;
import mtnm.tmforum.org.managedElement.ManagedElement_THolder;
import mtnm.tmforum.org.multiLayerSubnetwork.MultiLayerSubnetwork_THolder;
import mtnm.tmforum.org.multiLayerSubnetwork.SubnetworkList_THolder;
import mtnm.tmforum.org.protection.EProtectionGroupList_THolder;
import mtnm.tmforum.org.protection.ProtectionGroupList_THolder;
import mtnm.tmforum.org.protection.ProtectionGroup_THolder;
import mtnm.tmforum.org.subnetworkConnection.CrossConnectList_THolder;
import mtnm.tmforum.org.subnetworkConnection.SubnetworkConnectionList_THolder;
import mtnm.tmforum.org.subnetworkConnection.SubnetworkConnection_THolder;
import mtnm.tmforum.org.terminationPoint.TerminationPointList_THolder;
import mtnm.tmforum.org.terminationPoint.TerminationPoint_THolder;
import mtnm.tmforum.org.topologicalLink.TopologicalLinkList_THolder;

public interface IInformationFiberhomeTransmitAccessor {

    TerminationPointList_THolder getContainedCurrentTPs(NameAndStringValue_T[] terminationPointNames,
                                                        short[] layerRates, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    TerminationPoint_THolder getTP(NameAndStringValue_T[] terminationPointName) throws ProcessingFailureException, BusinessException;

    ManagedElementList_THolder getAllManagedElements(int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    EquipmentOrHolderList_THolder getAllEquipment(NameAndStringValue_T[] neNames, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    ManagedElement_THolder getManagedElement(NameAndStringValue_T[] neNames) throws ProcessingFailureException, BusinessException;

    SubnetworkList_THolder getAllTopLevelSubnetworks(int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    TopologicalLinkList_THolder getAllTopologicalLinks(NameAndStringValue_T[] subnetworkId, int pageIndex, int pageSize)
            throws ProcessingFailureException, BusinessException;

    SubnetworkConnectionList_THolder getAllSubnetworkConnections(NameAndStringValue_T[] subnetworkNames, short[] connectionRates,
                                                                 int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    MultiLayerSubnetwork_THolder getMultiLayerSubnetwork(NameAndStringValue_T[] subnetName) throws ProcessingFailureException, BusinessException;

    FDList_THolder getAllFlowDomains(int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    ProtectionGroup_THolder getProtectionGroup(NameAndStringValue_T[] protectionGroupNames) throws ProcessingFailureException, BusinessException;

    FlowDomain_THolder getFlowDomain(NameAndStringValue_T[] fDName) throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getAllTopLevelSubnetworkNames(int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    EquipmentOrHolderList_THolder getContainedEquipment(NameAndStringValue_T[] equipmentHolderNames) throws ProcessingFailureException, BusinessException;

    EquipmentOrHolder_THolder getEquipment(NameAndStringValue_T[] equipmentOrHolderNames) throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getAllManagedElementNames(int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getAllPTPNames(NameAndStringValue_T[] subnetworkNames, short[] tpLayerRates,
                                                short[] connectionLayerRates, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    EMS_THolder getEMS() throws ProcessingFailureException, BusinessException;

    TerminationPointList_THolder getAllPTPs(NameAndStringValue_T[] neNames, short[] terminationPointLayerRates,
                                            short[] connectionLayerRates, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getContainedCurrentTPNames(NameAndStringValue_T[] terminationPointNames,
                                                            short[] layerRates, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getContainedPotentialTPNames(NameAndStringValue_T[] terminationPointNames,
                                                              short[] layerRates, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    TerminationPointList_THolder getContainedPotentialTPs(NameAndStringValue_T[] terminationPointNames,
                                                          short[] layerRates, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getContainingSubnetworkNames(NameAndStringValue_T[] neNames) throws ProcessingFailureException, BusinessException;

    TerminationPointList_THolder getAllFTPs(NameAndStringValue_T[] neNames, short[] terminationPointLayerRates,
                                            short[] connectionLayerRates, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    TerminationPointList_THolder getAllPTPsWithoutFTPs(NameAndStringValue_T[] neNames, short[] terminationPointLayerRates,
                                                       short[] connectionLayerRates, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getAllTopologicalLinkNames(NameAndStringValue_T[] subnetworkNames,
                                                            int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    SubnetworkConnectionList_THolder getAllSubnetworkConnectionsWithTP(NameAndStringValue_T[] terminationPointNames,
                                                                       short[] connectionRates,
                                                                       int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    TerminationPointList_THolder getAllEdgePoints(NameAndStringValue_T[] subnetworkNames, short[] terminationPointLayerRates,
                                                  short[] connectionLayerRates, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    SubnetworkConnectionList_THolder getSNCsByUserLabel(String userLabel) throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getAllEquipmentNames(NameAndStringValue_T[] neOrHolderNames,
                                                      int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    TerminationPointList_THolder getAllSupportedPTPs(NameAndStringValue_T[] equipmentNames, int pageIndex, int pageSize)
            throws ProcessingFailureException, BusinessException;

    SubnetworkConnection_THolder getSNC(NameAndStringValue_T[] sncNames) throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getAllEdgePointNames(NameAndStringValue_T[] subnetworkNames, short[] layerRates,
                                                      short[] connectionLayerRates, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getAllSubnetworkConnectionNames(NameAndStringValue_T[] subnetworkNames,
                                                                 short[] connectionRates, int pageIndex, int pageSize)
            throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getAllSubnetworkConnectionNamesWithTP(NameAndStringValue_T[] terminationPointNames,
                                                                       short[] connectionRates, int pageIndex, int pageSize)
            throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getAllSupportedPTPNames(NameAndStringValue_T[] equipmentNames, int pageIndex, int pageSize)
            throws ProcessingFailureException, BusinessException;

    ProtectionGroupList_THolder getAllProtectionGroups(NameAndStringValue_T[] neNames, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    EProtectionGroupList_THolder getAllEProtectionGroups(NameAndStringValue_T[] neNames, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    EquipmentOrHolderList_THolder getAllSupportingEquipment(NameAndStringValue_T[] physicalTerminationPointOrMatrixFlowDomainNames) throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getAllSupportingEquipmentNames(NameAndStringValue_T[] physicalTerminationPointOrMatrixFlowDomainNames) throws ProcessingFailureException, BusinessException;

    CrossConnectList_THolder getAllCrossConnections(NameAndStringValue_T[] neNames, short[] connectionRates,
                                                    int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    ManagedElementList_THolder getAllManagedElements(NameAndStringValue_T[] subnetName, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    NamingAttributesList_THolder getAllManagedElementNames(NameAndStringValue_T[] subnetName, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    CapabilityList_THolder getCapabilities() throws ProcessingFailureException, BusinessException;
}