package com.fwutech.oss.middleware.adapters.fiberhometransmit.interfaces.mappers.alarm;

import com.fwutech.oss.middleware.adapters.fiberhometransmit.beans.alarm.StructuredEvent;
import com.fwutech.oss.middleware.commons.globals.Constant;
import com.fwutech.oss.middleware.commons.interfaces.mappers.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Mapper(componentModel = Constant.MAPPER_COMPONENT_MODEL_SPRING,
        uses = {PropertyMapper.class,
                EventHeaderMapper.class,
                AnyMapper.class})
@Component
public interface StructuredEventMapper extends Converter<org.omg.CosNotification.StructuredEvent, StructuredEvent> {
    @Override
    @Mapping(target = "filterableData", source = "filterable_data")
    @Mapping(target = "remainderOfBody", source = "remainder_of_body")
    StructuredEvent convert(org.omg.CosNotification.StructuredEvent source);
}
