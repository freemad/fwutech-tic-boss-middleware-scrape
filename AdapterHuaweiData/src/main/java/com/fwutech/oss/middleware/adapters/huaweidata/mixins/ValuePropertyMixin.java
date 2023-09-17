package com.fwutech.oss.middleware.adapters.huaweidata.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ValuePropertyMixin {
    @JsonCreator
    public ValuePropertyMixin(@JsonProperty("_value_") String _value_) {
    }
}
