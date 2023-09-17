package com.fwutech.oss.middleware.adapters.huaweidata.configs.inventory;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tmforum.www.mtop.mri.wsdl.fdr.v1_0.FlowDomainRetrievalLocator;
import org.tmforum.www.mtop.mri.wsdl.fdr.v1_0.FlowDomainRetrieval_RPC;


import javax.xml.rpc.ServiceException;

@Slf4j
@Configuration
@EnableAutoConfiguration
public class FlowDomainRetrievalConfiguration {

    @Value("${fwutech.oss.middleware.adapters.soap.connect.url}" + "/" +
            "${fwutech.oss.middleware.adapters.soap.services.wsdl-name.flow-domain-retrieval}")
    public String URL;

    @Value("${fwutech.oss.middleware.adapters.soap.services.wsdl-name.flow-domain-retrieval}")
    private String flowDomainRetrievalWSDDServiceName;

    @Bean
    FlowDomainRetrievalLocator flowDomainRetrievalLocator() {
        FlowDomainRetrievalLocator httpLocator = new FlowDomainRetrievalLocator();
        httpLocator.setFlowDomainRetrievalEndpointAddress(URL);
        httpLocator.setFlowDomainRetrievalWSDDServiceName(flowDomainRetrievalWSDDServiceName);
        return httpLocator;
    }

    @Bean
    FlowDomainRetrieval_RPC flowDomainRetrievalBindingStub(FlowDomainRetrievalLocator webServiceLocator) throws ServiceException {
        return webServiceLocator.getFlowDomainRetrieval();
    }
}
