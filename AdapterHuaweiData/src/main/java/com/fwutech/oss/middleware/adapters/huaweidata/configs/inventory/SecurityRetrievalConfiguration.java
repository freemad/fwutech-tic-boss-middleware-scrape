package com.fwutech.oss.middleware.adapters.huaweidata.configs.inventory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tmforum.www.mtop.vs.wsdl.sr.v1_0.SecurityRetrieval;
import org.tmforum.www.mtop.vs.wsdl.sr.v1_0.SecurityRetrievalHttpLocator;

import javax.xml.rpc.ServiceException;

@Slf4j
@Configuration
@EnableAutoConfiguration
public class SecurityRetrievalConfiguration {

    @Value("${fwutech.oss.middleware.adapters.soap.connect.url}" + "/" +
            "${fwutech.oss.middleware.adapters.soap.services.wsdl-name.security-retrieval}")
    public String URL;

    @Value("${fwutech.oss.middleware.adapters.soap.services.wsdl-name.security-retrieval}")
    private String securityRetrievalWSDDServiceName;

    @Bean
    SecurityRetrievalHttpLocator securityRetrievalHttpLocator() {
        SecurityRetrievalHttpLocator httpLocator = new SecurityRetrievalHttpLocator();
        httpLocator.setSecurityRetrieval_RPCEndpointAddress(URL);
        httpLocator.setSecurityRetrieval_RPCWSDDServiceName(securityRetrievalWSDDServiceName);
        return httpLocator;
    }

    @Bean
    SecurityRetrieval securityRetrievalBindingStub(SecurityRetrievalHttpLocator webServiceLocator)
            throws ServiceException {
        return webServiceLocator.getSecurityRetrieval_RPC();
    }
}
