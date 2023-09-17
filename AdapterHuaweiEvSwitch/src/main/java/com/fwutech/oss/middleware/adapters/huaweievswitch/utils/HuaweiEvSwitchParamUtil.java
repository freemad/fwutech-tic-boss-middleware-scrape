package com.fwutech.oss.middleware.adapters.huaweievswitch.utils;

import com.fwutech.oss.middleware.adapters.huaweievswitch.global.HuaweiEvSwitchApiRoute;
import com.fwutech.oss.middleware.commons.globals.ApiRoute;
import com.fwutech.oss.middleware.commons.utils.MapUtil;

import java.text.ParseException;
import java.util.Map;

public class HuaweiEvSwitchParamUtil {

    public static Map<String, String> getHuaweiEvSwitchQueryParams(Map<String, String> rawMap, Map<String, String> replacedKeys) throws ParseException {
        Map<String, String> resultMap = MapUtil.replaceKeyParams(rawMap, replacedKeys);
        if (resultMap.containsKey(ApiRoute.BEGIN_TIME) && resultMap.containsKey(ApiRoute.END_TIME)) {
            resultMap.put(HuaweiEvSwitchApiRoute.START_TIME, HuaweiEvSwitchDateUtil.iso8601BasicToEpochTime(resultMap.get(ApiRoute.BEGIN_TIME)) + "");
            resultMap.put(HuaweiEvSwitchApiRoute.END_TIME, HuaweiEvSwitchDateUtil.iso8601BasicToEpochTime(resultMap.get(ApiRoute.END_TIME)) + "");
            resultMap.remove(ApiRoute.BEGIN_TIME);
            resultMap.remove(ApiRoute.END_TIME);
        }
        return resultMap;
    }
}
