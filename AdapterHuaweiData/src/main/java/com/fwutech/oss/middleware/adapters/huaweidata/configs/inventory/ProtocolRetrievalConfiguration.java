package com.fwutech.oss.middleware.adapters.huaweidata.configs.inventory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tmforum.www.mtop.mri.wsdl.protocol.v1_0.ProtocolRetrievalLocator;
import org.tmforum.www.mtop.mri.wsdl.protocol.v1_0.ProtocolRetrieval_RPC;


import javax.xml.rpc.ServiceException;

@Slf4j
@Configuration
@EnableAutoConfiguration
public class ProtocolRetrievalConfiguration {

    @Value("${fwutech.oss.middleware.adapters.soap.connect.url}" + "/" +
            "${fwutech.oss.middleware.adapters.soap.services.wsdl-name.protocol-retrieval}")
    public String protocolRetrievalPortalURL;

    @Value("${fwutech.oss.middleware.adapters.soap.services.wsdl-name.protocol-retrieval}")
    private String protocolRetrievalWSDDServiceName;

    @Bean
    ProtocolRetrievalLocator protocolRetrievalLocator() {
        ProtocolRetrievalLocator locator = new ProtocolRetrievalLocator();
        locator.setProtocolRetrievalEndpointAddress(protocolRetrievalPortalURL);
        locator.setProtocolRetrievalWSDDServiceName(protocolRetrievalWSDDServiceName);
        return locator;
    }

    @Bean
    ProtocolRetrieval_RPC protocolRetrievalBindingStub(ProtocolRetrievalLocator webServiceLocator)
            throws ServiceException {
        return webServiceLocator.getProtocolRetrieval();
    }
}
