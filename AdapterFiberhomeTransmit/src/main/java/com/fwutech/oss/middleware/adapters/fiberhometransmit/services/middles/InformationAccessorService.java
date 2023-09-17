package com.fwutech.oss.middleware.adapters.fiberhometransmit.services.middles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fwutech.oss.middleware.adapters.fiberhometransmit.enums.FiberhomeTransmitErrorCode;
import com.fwutech.oss.middleware.adapters.fiberhometransmit.interfaces.inventory.IInformationFiberhomeTransmitAccessor;
import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.enums.CommonErrorCode;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import com.fwutech.oss.middleware.commons.globals.ApiParam;
import com.fwutech.oss.middleware.commons.globals.ApiRoute;
import com.fwutech.oss.middleware.commons.interfaces.inventory.IInformationAccessor;
import com.fwutech.oss.middleware.commons.utils.JsonUtil;
import com.fwutech.oss.middleware.commons.utils.StreamUtil;
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
import org.omg.CORBA.BAD_PARAM;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Qualifier(value = "fiberhomeTransmitInformationAccessorService")
public class InformationAccessorService implements IInformationAccessor {

    @Autowired
    private IInformationFiberhomeTransmitAccessor informationFiberhomeTransmitAccessorService;

    @Override
    public String getContainedCurrentPorts(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String portId = params.get(ApiRoute.PORT_ID);
            NameAndStringValue_T[] portNames = StreamUtil
                    .jsonStringToObject(portId, NameAndStringValue_T[].class);
            short[] layerRates = StreamUtil.toShortArray(params.get(ApiParam.LAYER_RATES), ",");
            TerminationPointList_THolder ports = informationFiberhomeTransmitAccessorService.getContainedCurrentTPs(
                    portNames, layerRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(ports.value);
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
    public String getPortById(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String portId = params.get(ApiRoute.PORT_ID);
            NameAndStringValue_T[] portNames = StreamUtil.jsonStringToObject(portId, NameAndStringValue_T[].class);
            TerminationPoint_THolder port = informationFiberhomeTransmitAccessorService.getTP(portNames);
            return JsonUtil.getJsonString(port.value);
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
    public String getAllNes(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            ManagedElementList_THolder Nes = informationFiberhomeTransmitAccessorService.getAllManagedElements(pageIndex, pageSize);
            return JsonUtil.getJsonString(Nes.value);
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
    public String getNeBoards(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String neId = params.get(ApiRoute.NE_ID);
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            NameAndStringValue_T[] neNames = StreamUtil.jsonStringToObject(neId, NameAndStringValue_T[].class);
            EquipmentOrHolderList_THolder equipmentOrHolderTs = informationFiberhomeTransmitAccessorService.
                    getAllEquipment(neNames, pageIndex, pageSize);
            return JsonUtil.getJsonString(equipmentOrHolderTs.value);
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
    public String getNe(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String neId = params.get(ApiRoute.NE_ID);
            NameAndStringValue_T[] neNames = StreamUtil.jsonStringToObject(neId, NameAndStringValue_T[].class);
            ManagedElement_THolder neHolder = informationFiberhomeTransmitAccessorService.getManagedElement(neNames);
            return JsonUtil.getJsonString(neHolder.value);
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
    public String getAllTopLevelSubnetworks(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            SubnetworkList_THolder subnetworkList = informationFiberhomeTransmitAccessorService.getAllTopLevelSubnetworks(pageIndex, pageSize);
            return JsonUtil.getJsonString(subnetworkList.value);
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
    public String getAllSubnetworkFiberCables(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String subnetworkId = params.get(ApiRoute.SUBNETWORK_ID);
            NameAndStringValue_T[] subnetworkNames = StreamUtil
                    .jsonStringToObject(subnetworkId, NameAndStringValue_T[].class);
            TopologicalLinkList_THolder topologicalLinkListTHolder = informationFiberhomeTransmitAccessorService
                    .getAllTopologicalLinks(subnetworkNames, pageIndex, pageSize);
            return JsonUtil.getJsonString(topologicalLinkListTHolder.value);
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
    public String getProtectionGroup(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String protectionGroupId = params.get(ApiRoute.PROTECTION_GROUP_ID);
            NameAndStringValue_T[] protectionGroupNames = StreamUtil
                    .jsonStringToObject(protectionGroupId, NameAndStringValue_T[].class);
            ProtectionGroup_THolder protectionGroup_tHolder = informationFiberhomeTransmitAccessorService
                    .getProtectionGroup(protectionGroupNames);
            return JsonUtil.getJsonString(protectionGroup_tHolder.value);
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
    public String getFlowDomain(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String flowDomainId = params.get(ApiRoute.FLOW_DOMAIN_ID);
            NameAndStringValue_T[] flowDomainNames = StreamUtil
                    .jsonStringToObject(flowDomainId, NameAndStringValue_T[].class);
            FlowDomain_THolder flowDomain = informationFiberhomeTransmitAccessorService.getFlowDomain(flowDomainNames);
            return JsonUtil.getJsonString(flowDomain.value);
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
    public String getAllTopLevelSubnetworkNames(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            NamingAttributesList_THolder subnetworkNames = informationFiberhomeTransmitAccessorService
                    .getAllTopLevelSubnetworkNames(pageIndex, pageSize);
            return JsonUtil.getJsonString(subnetworkNames.value);
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
    public String getContainedBoards(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String subrackId = params.get(ApiRoute.SUBRACK_ID);
            NameAndStringValue_T[] subrackNames = StreamUtil
                    .jsonStringToObject(subrackId, NameAndStringValue_T[].class);
            EquipmentOrHolderList_THolder equipmentOrHolderList_tHolder = informationFiberhomeTransmitAccessorService.
                    getContainedEquipment(subrackNames);
            return JsonUtil.getJsonString(equipmentOrHolderList_tHolder.value);
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
    public String getBoard(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String boardOrSubrackId = params.get(ApiRoute.BOARD_OR_SUBRACK_ID);
            NameAndStringValue_T[] boardOrSubrackNames = StreamUtil
                    .jsonStringToObject(boardOrSubrackId, NameAndStringValue_T[].class);
            EquipmentOrHolder_THolder equipmentOrHolderT = informationFiberhomeTransmitAccessorService
                    .getEquipment(boardOrSubrackNames);
            return JsonUtil.getJsonString(equipmentOrHolderT.value);
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
    public String getAllNeNames(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            NamingAttributesList_THolder namingAttributesListTHolder = informationFiberhomeTransmitAccessorService
                    .getAllManagedElementNames(pageIndex, pageSize);
            return JsonUtil.getJsonString(namingAttributesListTHolder.value);
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
    public String getAllPhysicalPortNames(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String physicalPortId = params.get(ApiRoute.PHYSICAL_PORT_ID);
            short[] portLayerRates = StreamUtil.toShortArray(params.get(ApiParam.PORT_LAYER_RATES), ",");
            short[] connectionLayerRates = StreamUtil.toShortArray(params.get(ApiParam.CONNECTION_LAYER_RATES), ",");
            NameAndStringValue_T[] physicalPortNames = StreamUtil
                    .jsonStringToObject(physicalPortId, NameAndStringValue_T[].class);
            NamingAttributesList_THolder namingAttributesListTHolder = informationFiberhomeTransmitAccessorService
                    .getAllPTPNames(physicalPortNames, portLayerRates, connectionLayerRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(namingAttributesListTHolder.value);
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
    public String getEms(RequestEvent requestEvent) throws BusinessException {
        try {
            EMS_THolder emsTHolder = informationFiberhomeTransmitAccessorService.getEMS();
            return JsonUtil.getJsonString(emsTHolder.value);
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
    public String getAllPhysicalPorts(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String neId = params.get(ApiRoute.NE_ID);
            NameAndStringValue_T[] neNames = StreamUtil
                    .jsonStringToObject(neId, NameAndStringValue_T[].class);
            short[] portLayerRates = StreamUtil.toShortArray(params.get(ApiParam.PORT_LAYER_RATES), ",");
            short[] connectionLayerRates = StreamUtil.toShortArray(params.get(ApiParam.CONNECTION_LAYER_RATES), ",");
            TerminationPointList_THolder portsHolder = informationFiberhomeTransmitAccessorService.getAllPTPs(neNames,
                    portLayerRates, connectionLayerRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(portsHolder.value);
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
    public String getContainedCurrentPortNames(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String portId = params.get(ApiRoute.PORT_ID);
            NameAndStringValue_T[] portNames = StreamUtil.jsonStringToObject(portId, NameAndStringValue_T[].class);
            short[] layerRates = StreamUtil.toShortArray(params.get(ApiParam.LAYER_RATES), ",");
            NamingAttributesList_THolder containedCurrentPortNamesHolder = informationFiberhomeTransmitAccessorService
                    .getContainedCurrentTPNames(portNames, layerRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(containedCurrentPortNamesHolder.value);
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
    public String getContainedPotentialPortNames(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String portId = params.get(ApiRoute.PORT_ID);
            NameAndStringValue_T[] portNames = StreamUtil.jsonStringToObject(portId, NameAndStringValue_T[].class);
            short[] layerRates = StreamUtil.toShortArray(params.get(ApiParam.LAYER_RATES), ",");
            NamingAttributesList_THolder containedPotentialPortNamesHolder = informationFiberhomeTransmitAccessorService
                    .getContainedPotentialTPNames(portNames, layerRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(containedPotentialPortNamesHolder.value);
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
    public String getContainedPotentialPorts(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String portId = params.get(ApiRoute.PORT_ID);
            NameAndStringValue_T[] portNames = StreamUtil.jsonStringToObject(portId, NameAndStringValue_T[].class);
            short[] layerRates = StreamUtil.toShortArray(params.get(ApiParam.LAYER_RATES), ",");
            TerminationPointList_THolder containedPotentialPortsHolder = informationFiberhomeTransmitAccessorService
                    .getContainedPotentialTPs(portNames, layerRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(containedPotentialPortsHolder.value);
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
    public String getContainingSubnetworkNames(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String neId = params.get(ApiRoute.NE_ID);
            NameAndStringValue_T[] neNames = StreamUtil.jsonStringToObject(neId, NameAndStringValue_T[].class);
            NamingAttributesList_THolder containingSubnetworkNamesHolder = informationFiberhomeTransmitAccessorService
                    .getContainingSubnetworkNames(neNames);
            return JsonUtil.getJsonString(containingSubnetworkNamesHolder.value);
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
    public String getAllLogicalPorts(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String neId = params.get(ApiRoute.NE_ID);
            NameAndStringValue_T[] neNames = StreamUtil
                    .jsonStringToObject(neId, NameAndStringValue_T[].class);
            short[] portLayerRates = StreamUtil.toShortArray(params.get(ApiParam.PORT_LAYER_RATES), ",");
            short[] connectionLayerRates = StreamUtil.toShortArray(params.get(ApiParam.CONNECTION_LAYER_RATES), ",");
            TerminationPointList_THolder allLogicalPortsHolder = informationFiberhomeTransmitAccessorService
                    .getAllFTPs(neNames, portLayerRates, connectionLayerRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(allLogicalPortsHolder.value);
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
    public String getSubnetworkConnectionById(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String subnetworkId = params.get(ApiRoute.SUBNETWORK_CONNECTION_ID);
            short[] connectionRates = StreamUtil.toShortArray(params.get(ApiParam.CONNECTION_RATES), ",");
            NameAndStringValue_T[] subnetworkNames = StreamUtil
                    .jsonStringToObject(subnetworkId, NameAndStringValue_T[].class);
            SubnetworkConnectionList_THolder subnetworkConnectionList_tHolder = informationFiberhomeTransmitAccessorService
                    .getAllSubnetworkConnections(subnetworkNames, connectionRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(subnetworkConnectionList_tHolder.value);
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
    public String getAllPhysicalPortsWithoutLogicalPorts(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String neId = params.get(ApiRoute.NE_ID);
            NameAndStringValue_T[] neNames = StreamUtil.jsonStringToObject(neId, NameAndStringValue_T[].class);
            short[] portLayerRates = StreamUtil.toShortArray(params.get(ApiParam.PORT_LAYER_RATES), ",");
            short[] connectionLayerRates = StreamUtil.toShortArray(params.get(ApiParam.CONNECTION_LAYER_RATES), ",");
            TerminationPointList_THolder allPhysicalPortsWithoutLogicalPortsHolder = informationFiberhomeTransmitAccessorService
                    .getAllPTPsWithoutFTPs(neNames, portLayerRates, connectionLayerRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(allPhysicalPortsWithoutLogicalPortsHolder.value);
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
    public String getAllSubnetworkFiberCableNames(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String subnetworkId = params.get(ApiRoute.SUBNETWORK_ID);
            NameAndStringValue_T[] subnetworkNames = StreamUtil.
                    jsonStringToObject(subnetworkId, NameAndStringValue_T[].class);
            NamingAttributesList_THolder allFiberCableNamesHolder = informationFiberhomeTransmitAccessorService
                    .getAllTopologicalLinkNames(subnetworkNames, pageIndex, pageSize);
            return JsonUtil.getJsonString(allFiberCableNamesHolder.value);
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
    public String getAllPortSubnetworkConnections(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String portId = params.get(ApiRoute.PORT_ID);
            NameAndStringValue_T[] portNames = StreamUtil.jsonStringToObject(portId, NameAndStringValue_T[].class);
            short[] connectionRates = StreamUtil.toShortArray(params.get(ApiParam.CONNECTION_RATES), ",");
            SubnetworkConnectionList_THolder allSubnetworkConnectionsWithPortHolder = informationFiberhomeTransmitAccessorService
                    .getAllSubnetworkConnectionsWithTP(portNames, connectionRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(allSubnetworkConnectionsWithPortHolder.value);
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
    public String getAllEdgePoints(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String subnetworkId = params.get(ApiRoute.SUBNETWORK_ID);
            NameAndStringValue_T[] subnetworkNames = StreamUtil.
                    jsonStringToObject(subnetworkId, NameAndStringValue_T[].class);
            short[] portLayerRates = StreamUtil.toShortArray(params.get(ApiParam.PORT_LAYER_RATES), ",");
            short[] connectionLayerRates = StreamUtil.toShortArray(params.get(ApiParam.CONNECTION_LAYER_RATES), ",");
            TerminationPointList_THolder allEdgePointsHolder = informationFiberhomeTransmitAccessorService
                    .getAllEdgePoints(subnetworkNames, portLayerRates, connectionLayerRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(allEdgePointsHolder.value);
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
    public String getUserLabelTrailManagements(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String userLabel = params.get(ApiRoute.USER_LABEL);
            SubnetworkConnectionList_THolder trialManagementsByUserLabelHolder = informationFiberhomeTransmitAccessorService
                    .getSNCsByUserLabel(userLabel);
            return JsonUtil.getJsonString(trialManagementsByUserLabelHolder.value);
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
    public String getAllBoardNames(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String neOrHolderId = params.get(ApiRoute.NE_OR_HOLDER_ID);
            NameAndStringValue_T[] neOrHolderNames = StreamUtil.
                    jsonStringToObject(neOrHolderId, NameAndStringValue_T[].class);
            NamingAttributesList_THolder allBoardsNamesHolder = informationFiberhomeTransmitAccessorService
                    .getAllEquipmentNames(neOrHolderNames, pageIndex, pageSize);
            return JsonUtil.getJsonString(allBoardsNamesHolder.value);
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
    public String getAllSupportedPhysicalPorts(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String boardId = params.get(ApiRoute.BOARD_ID);
            NameAndStringValue_T[] boardNames = StreamUtil.
                    jsonStringToObject(boardId, NameAndStringValue_T[].class);
            TerminationPointList_THolder allSupportedPhysicalPortsHolder = informationFiberhomeTransmitAccessorService
                    .getAllSupportedPTPs(boardNames, pageIndex, pageSize);
            return JsonUtil.getJsonString(allSupportedPhysicalPortsHolder.value);
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
    public String getTrailManagement(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String trailManagementId = params.get(ApiRoute.TRAIL_MANAGEMENT_ID);
            NameAndStringValue_T[] trailManagementNames = StreamUtil.jsonStringToObject(trailManagementId, NameAndStringValue_T[].class);
            SubnetworkConnection_THolder trailManagementHolder = informationFiberhomeTransmitAccessorService.getSNC(trailManagementNames);
            return JsonUtil.getJsonString(trailManagementHolder.value);
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
    public String getAllEdgePointNames(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String subnetworkId = params.get(ApiRoute.SUBNETWORK_ID);
            NameAndStringValue_T[] subnetworkNames = StreamUtil
                    .jsonStringToObject(subnetworkId, NameAndStringValue_T[].class);
            short[] layerRates = StreamUtil.toShortArray(params.get(ApiParam.LAYER_RATES), ",");
            short[] connectionLayerRates = StreamUtil.toShortArray(params.get(ApiParam.CONNECTION_LAYER_RATES), ",");
            NamingAttributesList_THolder allEdgePointNamesHolder = informationFiberhomeTransmitAccessorService
                    .getAllEdgePointNames(subnetworkNames, layerRates, connectionLayerRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(allEdgePointNamesHolder.value);
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
    public String getAllSubnetworkConnectionNames(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String subnetworkId = params.get(ApiRoute.SUBNETWORK_ID);
            NameAndStringValue_T[] subnetworkNames = StreamUtil.
                    jsonStringToObject(subnetworkId, NameAndStringValue_T[].class);
            short[] connectionRates = StreamUtil.toShortArray(params.get(ApiParam.CONNECTION_RATES), ",");
            NamingAttributesList_THolder allSubnetworkConnectionNamesHolder = informationFiberhomeTransmitAccessorService
                    .getAllSubnetworkConnectionNames(subnetworkNames, connectionRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(allSubnetworkConnectionNamesHolder.value);
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
    public String getAllPortSubnetworkConnectionNames(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String portId = params.get(ApiRoute.PORT_ID);
            NameAndStringValue_T[] portNames = StreamUtil.jsonStringToObject(portId, NameAndStringValue_T[].class);
            short[] connectionRates = StreamUtil.toShortArray(params.get(ApiParam.CONNECTION_RATES), ",");
            NamingAttributesList_THolder allSubnetworkConnectionNamesWithPortHolder = informationFiberhomeTransmitAccessorService
                    .getAllSubnetworkConnectionNamesWithTP(portNames, connectionRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(allSubnetworkConnectionNamesWithPortHolder.value);
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
    public String getAllSupportedPhysicalPortNames(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String boardId = params.get(ApiRoute.BOARD_ID);
            NameAndStringValue_T[] boardNames = StreamUtil
                    .jsonStringToObject(boardId, NameAndStringValue_T[].class);
            NamingAttributesList_THolder allSupportedPhysicalPortNamesHolder = informationFiberhomeTransmitAccessorService
                    .getAllSupportedPTPNames(boardNames, pageIndex, pageSize);
            return JsonUtil.getJsonString(allSupportedPhysicalPortNamesHolder.value);
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
    public String getAllProtectionGroups(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String neId = params.get(ApiRoute.NE_ID);
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            NameAndStringValue_T[] neNames = StreamUtil.jsonStringToObject(neId, NameAndStringValue_T[].class);
            ProtectionGroupList_THolder protectionGroup_tHolder =
                    informationFiberhomeTransmitAccessorService.getAllProtectionGroups(neNames, pageIndex, pageSize);
            return JsonUtil.getJsonString(protectionGroup_tHolder.value);
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
    public String getAllBoardProtectionGroups(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String neId = params.get(ApiRoute.NE_ID);
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            NameAndStringValue_T[] neNames = StreamUtil.jsonStringToObject(neId, NameAndStringValue_T[].class);
            EProtectionGroupList_THolder eProtectionGroup_tHolder =
                    informationFiberhomeTransmitAccessorService.getAllEProtectionGroups(neNames, pageIndex, pageSize);
            return JsonUtil.getJsonString(eProtectionGroup_tHolder.value);
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
    public String getAllSupportingBoards(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String boardId = params.get(ApiRoute.BOARD_ID);
            NameAndStringValue_T[] boardNames = StreamUtil.jsonStringToObject(boardId, NameAndStringValue_T[].class);
            EquipmentOrHolderList_THolder equipmentOrHolderList_tHolder =
                    informationFiberhomeTransmitAccessorService.getAllSupportingEquipment(boardNames);
            return JsonUtil.getJsonString(equipmentOrHolderList_tHolder.value);
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
    public String getAllSupportingBoardNames(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String boardId = params.get(ApiRoute.BOARD_ID);
            NameAndStringValue_T[] boardNames = StreamUtil.jsonStringToObject(boardId, NameAndStringValue_T[].class);
            NamingAttributesList_THolder namingAttributesList_tHolder =
                    informationFiberhomeTransmitAccessorService.getAllSupportingEquipmentNames(boardNames);
            return JsonUtil.getJsonString(namingAttributesList_tHolder.value);
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
    public String getMultiLayerSubnetwork(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            String subnetworkId = params.get(ApiRoute.SUBNETWORK_ID);
            NameAndStringValue_T[] subnetName = StreamUtil.jsonStringToObject(subnetworkId, NameAndStringValue_T[].class);
            MultiLayerSubnetwork_THolder namingAttributesList_tHolder =
                    informationFiberhomeTransmitAccessorService.getMultiLayerSubnetwork(subnetName);
            return JsonUtil.getJsonString(namingAttributesList_tHolder.value);
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
    public String getAllFlowDomains(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            FDList_THolder fdList_tHolder =
                    informationFiberhomeTransmitAccessorService.getAllFlowDomains(pageIndex, pageSize);
            return JsonUtil.getJsonString(fdList_tHolder.value);
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
    public String getAllCrossConnections(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String neId = params.get(ApiRoute.NE_ID);
            NameAndStringValue_T[] neNames = StreamUtil.
                    jsonStringToObject(neId, NameAndStringValue_T[].class);
            short[] connectionRates = StreamUtil.toShortArray(params.get(ApiParam.CONNECTION_RATES), ",");
            CrossConnectList_THolder crossConnectListTHolder = informationFiberhomeTransmitAccessorService.
                    getAllCrossConnections(neNames, connectionRates, pageIndex, pageSize);
            return JsonUtil.getJsonString(crossConnectListTHolder.value);
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
    public String getAllNesBySubnetName(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String subnetworkId = params.get(ApiRoute.SUBNETWORK_ID);
            NameAndStringValue_T[] subnetworkNames = StreamUtil.jsonStringToObject(subnetworkId, NameAndStringValue_T[].class);
            ManagedElementList_THolder Nes = informationFiberhomeTransmitAccessorService
                    .getAllManagedElements(subnetworkNames, pageIndex, pageSize);
            return JsonUtil.getJsonString(Nes.value);
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
    public String getAllNeNamesBySubnetName(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> params = requestEvent.getCommand().getParams();
            int pageIndex = Integer.parseInt(params.get(ApiParam.PAGE_INDEX));
            int pageSize = Integer.parseInt(params.get(ApiParam.PAGE_SIZE));
            String subnetworkId = params.get(ApiRoute.SUBNETWORK_ID);
            NameAndStringValue_T[] subnetName = StreamUtil.jsonStringToObject(subnetworkId, NameAndStringValue_T[].class);
            NamingAttributesList_THolder namingAttributesListTHolder = informationFiberhomeTransmitAccessorService
                    .getAllManagedElementNames(subnetName, pageIndex, pageSize);
            return JsonUtil.getJsonString(namingAttributesListTHolder.value);
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
