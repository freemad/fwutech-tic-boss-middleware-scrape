package com.fwutech.oss.middleware.commons.interfaces.aspects;

import com.fwutech.oss.middleware.commons.utils.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.json.JSONObject;

public interface ILogBefore {

    default JSONObject prepareBeforeLog(JoinPoint joinPoint){
        return JsonUtil.createMethodJsonLog(joinPoint);
    }
    void logBefore(JoinPoint joinPoint);
}
