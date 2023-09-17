package com.fwutech.oss.middleware.adapters.huaweiswitch.services.sbis;

import com.fwutech.oss.middleware.adapters.huaweiswitch.interfaces.traffic.ITrafficHuaweiSwitchAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.fwutech.oss.middleware.adapters.huaweiswitch.global.HuaweiSwitchApiRoute.*;

@Service
public class TrafficHuaweiSwitchAccessorService implements ITrafficHuaweiSwitchAccessor {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HttpHeaders httpHeaders;
    @Autowired
    String hostUrl;

    @Override
    public ResponseEntity<Object> getTrafficById(String Id, String startDate, String endDate)
            throws RestClientException {
        return restTemplate.exchange(
                hostUrl + COMC_API + TRAFFIC + HUAWEI + "/" + Id + "/" + startDate + "/" + endDate,
                HttpMethod.GET,
                new HttpEntity<>("", httpHeaders),
                Object.class);
    }
}