package com.fwutech.oss.middleware.adapters.huaweidata.configs.inventory;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tmforum.www.mtop.mri.wsdl.eir.v1_0.EquipmentInventoryRetrievalHttpLocator;
import org.tmforum.www.mtop.mri.wsdl.eir.v1_0.EquipmentInventoryRetrieval_RPC;
import org.tmforum.www.mtop.mri.wsdl.tlr.v1_0.TopologicalLinkRetrievalHttpLocator;
import org.tmforum.www.mtop.mri.wsdl.tlr.v1_0.TopologicalLinkRetrieval_RPC;

import javax.xml.rpc.ServiceException;

@Slf4j
@Configuration
@EnableAutoConfiguration
public class EquipmentInventoryRetrievalConfiguration {


    @Value("${fwutech.oss.middleware.adapters.soap.connect.url}" + "/" +
            "${fwutech.oss.middleware.adapters.soap.services.wsdl-name.equipment-inventory-retrieval}")
    public String URL;

    @Value("${fwutech.oss.middleware.adapters.soap.services.wsdl-name.equipment-inventory-retrieval}")
    private String equipmentInventoryWSDDServiceName;

    @Bean
    EquipmentInventoryRetrievalHttpLocator equipmentInventoryRetrievalHttpLocator() {
        EquipmentInventoryRetrievalHttpLocator httpLocator = new EquipmentInventoryRetrievalHttpLocator();
        httpLocator.setEquipmentInventoryRetrieval_RPCEndpointAddress(URL);
        httpLocator.setEquipmentInventoryRetrieval_RPCWSDDServiceName(equipmentInventoryWSDDServiceName);
        return httpLocator;
    }

    @Bean
    EquipmentInventoryRetrieval_RPC equipmentInventoryRetrievalBindingStub(EquipmentInventoryRetrievalHttpLocator webServiceLocator) throws ServiceException {
        return webServiceLocator.getEquipmentInventoryRetrieval_RPC();
    }
}
