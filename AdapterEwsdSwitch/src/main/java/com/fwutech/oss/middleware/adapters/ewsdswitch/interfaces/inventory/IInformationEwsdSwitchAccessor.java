package com.fwutech.oss.middleware.adapters.ewsdswitch.interfaces.inventory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

public interface IInformationEwsdSwitchAccessor {

    ResponseEntity<Object> getSwitches()
            throws RestClientException;

    ResponseEntity<Object> getInventoryById(String Id)
            throws RestClientException;

    ResponseEntity<Object> getInventoryDetailsById(String Id)
            throws RestClientException;

    ResponseEntity<Object> getSwitchById(String Id)
            throws RestClientException;
}
