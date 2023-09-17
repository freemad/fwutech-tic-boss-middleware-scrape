package com.fwutech.oss.middleware.adapters.ncedata.configs.inventory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tmforum.www.mtop.mri.wsdl.mlsnr.v1_0.MultiLayerSubnetworkRetrievalHttpLocator;
import org.tmforum.www.mtop.mri.wsdl.mlsnr.v1_0.MultiLayerSubnetworkRetrieval_RPC;

import javax.xml.rpc.ServiceException;

@Slf4j
@Configuration
@EnableAutoConfiguration
public class MultiLayerSubnetworkRetrievalConfiguration {

    @Value("${fwutech.oss.middleware.adapters.soap.connect.url}" + "/" +
            "${fwutech.oss.middleware.adapters.soap.services.wsdl-name.multiLayer-subnetwork-retrieval}")
    public String multiLayerSubnetworkRetrievalPortalURL;

    @Value("${fwutech.oss.middleware.adapters.soap.services.wsdl-name.multiLayer-subnetwork-retrieval}")
    private String multiLayerSubnetworkRetrievalWSDDServiceName;

    @Bean
    MultiLayerSubnetworkRetrievalHttpLocator multiLayerSubnetworkRetrievalHttpLocator() {
        MultiLayerSubnetworkRetrievalHttpLocator httpLocator = new MultiLayerSubnetworkRetrievalHttpLocator();
        httpLocator.setMultiLayerSubnetworkRetrieval_RPCEndpointAddress(multiLayerSubnetworkRetrievalPortalURL);
        httpLocator.setMultiLayerSubnetworkRetrieval_RPCWSDDServiceName(multiLayerSubnetworkRetrievalWSDDServiceName);
        return httpLocator;
    }

    @Bean
    MultiLayerSubnetworkRetrieval_RPC multiLayerSubnetworkRetrievalBindingStub(
            MultiLayerSubnetworkRetrievalHttpLocator webServiceLocator) throws ServiceException {
        return webServiceLocator.getMultiLayerSubnetworkRetrieval_RPC();
    }

}
