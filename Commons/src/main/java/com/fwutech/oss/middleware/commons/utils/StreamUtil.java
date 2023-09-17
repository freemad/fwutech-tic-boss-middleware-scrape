package com.fwutech.oss.middleware.commons.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fwutech.oss.middleware.commons.enums.CommonErrorCode;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import com.fwutech.oss.middleware.commons.globals.Constant;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Map;

public class StreamUtil {
    public static short[] toShortArray(String param, String delimiter)
            throws NumberFormatException {
        String[] stringArray = param != null ? param.split(delimiter) : new String[0];
        short[] shortArray = new short[stringArray.length];
        int i = 0;
        for (String stringValue : stringArray) {
            shortArray[i] = Short.parseShort(stringValue);
            i++;
        }
        return shortArray;
    }

    public static String[] toStringArray(String param, String delimiter) {
        return param != null ? param.split(delimiter) : new String[0];
    }

    public static <T> T jsonStringToObject(String jsonString, Class<T> tClass)
            throws BusinessException {
        try {
            if (jsonString != null && !jsonString.trim().equals("")) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.setDateFormat(new SimpleDateFormat(Constant.ISO8601_DATETIME_FORMAT));
                return (T) mapper.readValue(jsonString.trim(), tClass);
            }
            Class componentClass = Class.forName(tClass.getName());
            return componentClass.isArray() ?
                    (T) Array.newInstance(componentClass.getComponentType(), 0) :
                    (T) componentClass.newInstance();
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, e);
        }
    }

    public static <T> T jsonStringToObjectByMixin(
            String jsonString, Class<T> tClass, Map<Class<?>, Class<?>> mappingMixinMap)
            throws BusinessException {
        try {
            if (jsonString != null && !jsonString.trim().equals("")) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.setDateFormat(new SimpleDateFormat(Constant.ISO8601_DATETIME_FORMAT));
                mapper.setMixIns(mappingMixinMap);
                return (T) mapper.readValue(jsonString.trim(), tClass);
            }
            Class componentClass = Class.forName(tClass.getName());
            return componentClass.isArray() ?
                    (T) Array.newInstance(componentClass.getComponentType(), 0) :
                    (T) componentClass.newInstance();
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, e);
        }
    }

/*    public static <T> T jsonStringToObjectByGson(String jsonString, Class<T> tClass)
            throws BusinessException {
        try {
            if (jsonString != null && !jsonString.trim().equals("")) {
                Gson gson = new Gson();
                return gson.fromJson(jsonString.trim(), tClass);
            }
            Class componentClass = Class.forName(tClass.getName());
            return componentClass.isArray() ?
                    (T) Array.newInstance(componentClass.getComponentType(), 0) :
                    (T) componentClass.newInstance();
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCode.JSON_PROCESSING_EXCEPTION, e);
        }
    }*/
}