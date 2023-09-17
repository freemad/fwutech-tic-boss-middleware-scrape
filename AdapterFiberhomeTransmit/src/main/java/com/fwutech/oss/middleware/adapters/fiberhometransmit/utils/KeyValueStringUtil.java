package com.fwutech.oss.middleware.adapters.fiberhometransmit.utils;

import com.fwutech.oss.middleware.commons.beans.base.KeyValueString;
import mtnm.tmforum.org.globaldefs.NameAndStringValue_T;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeyValueStringUtil {
    public static List<KeyValueString> toKeyValueString(String keyValueAsString, String delimiter, String assigner) {
        return Stream.of(keyValueAsString.split(delimiter))
                .map(keyValue -> new KeyValueString(
                        keyValue.split(assigner)[0],
                        keyValue.substring(
                                keyValue.split(assigner)[0].length() + assigner.length())))
                .collect(Collectors.toList());
    }

    public static NameAndStringValue_T[] toNameAndStringValue(String keyValueAsString, String delimiter, String assigner) {
        return Stream.of(keyValueAsString.split(delimiter))
                .map(keyValue -> new NameAndStringValue_T(
                        keyValue.split(assigner)[0],
                        keyValue.substring(
                                keyValue.split(assigner)[0].length() + assigner.length())))
                .toArray(size -> new NameAndStringValue_T[size]);

    }
}
