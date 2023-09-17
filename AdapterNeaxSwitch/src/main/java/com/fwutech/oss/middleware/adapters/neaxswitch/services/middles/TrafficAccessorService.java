package com.fwutech.oss.middleware.adapters.neaxswitch.services.middles;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fwutech.oss.middleware.adapters.neaxswitch.enums.NeaxSwitchErrorCode;
import com.fwutech.oss.middleware.adapters.neaxswitch.interfaces.traffic.ITrafficNeaxSwitchAccessor;
import com.fwutech.oss.middleware.adapters.neaxswitch.utils.NeaxSwitchDateUtil;
import com.fwutech.oss.middleware.adapters.neaxswitch.utils.NeaxSwitchParamUtil;
import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.enums.CommonErrorCode;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import com.fwutech.oss.middleware.commons.globals.ApiParam;
import com.fwutech.oss.middleware.commons.globals.ApiRoute;
import com.fwutech.oss.middleware.commons.interfaces.traffic.ITrafficAccessor;
import com.fwutech.oss.middleware.commons.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Service
@Qualifier(value = "neaxSwitchTrafficAccessorService")
public class TrafficAccessorService implements ITrafficAccessor {

    @Autowired
    private ITrafficNeaxSwitchAccessor trafficNeaxSwitchAccessorService;

    @Override
    public String getTrafficById(RequestEvent requestEvent) throws BusinessException {
        try {
            Map<String, String> rawMap = requestEvent.getCommand().getParams();
            Map<String, String> filteredParams = new HashMap<>();
            String trafficId = rawMap.get(ApiRoute.TRAFFIC_ID);
            Map<String, String> qParams = NeaxSwitchParamUtil.getNeaxSwitchQueryParams(rawMap, filteredParams);
            String startDate = NeaxSwitchDateUtil.iso8601BasicToDateV2(qParams.get(ApiParam.BEGIN_TIME));
            String endDate = NeaxSwitchDateUtil.iso8601BasicToDateV2(qParams.get(ApiParam.END_TIME));
            ResponseEntity<Object> responseEntity = trafficNeaxSwitchAccessorService.getTrafficById(trafficId,startDate,endDate);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return JsonUtil.getJsonString(responseEntity.getBody(), JsonAutoDetect.Visibility.PUBLIC_ONLY);
            }
            throw new BusinessException(NeaxSwitchErrorCode.HTTP_REQUEST_EXCEPTION, responseEntity.getStatusCode().toString(), requestEvent);
        } catch (BusinessException e) {
            throw e;
        } catch (RestClientException e) {
            throw new BusinessException(NeaxSwitchErrorCode.REST_CONNECTION_EXCEPTION, e, requestEvent);
        } catch (JsonProcessingException e) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, e, requestEvent);
        } catch (NullPointerException | IllegalArgumentException | ParseException e) {
            throw new BusinessException(CommonErrorCode.BAD_PARAM_EXCEPTION, e, requestEvent);
        } catch (Exception e) {
            throw new BusinessException(NeaxSwitchErrorCode.NEAX_SWITCH_GENERAL_EXCEPTION, e, requestEvent);
        }
    }

}

