package com.fwutech.oss.middleware.adapters.fiberhometransmit.interfaces.mappers.alarm;

import com.fwutech.oss.middleware.adapters.fiberhometransmit.beans.FiberhomeTransmitSessionFactory;
import com.fwutech.oss.middleware.commons.globals.Constant;
import com.fwutech.oss.middleware.commons.interfaces.mappers.Converter;
import mtnm.tmforum.org.globaldefs.NVSList_THelper;
import mtnm.tmforum.org.globaldefs.NameAndStringValue_T;
import org.mapstruct.Mapper;
import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.CORBA.TCKind;
import org.omg.DynamicAny.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Mapper(componentModel = Constant.MAPPER_COMPONENT_MODEL_SPRING)
@Component
public abstract class AnyMapper implements Converter<org.omg.CORBA.Any, String> {

    @Autowired
    private FiberhomeTransmitSessionFactory fiberhomeTransmitSessionFactory;

    @Override
    public String convert(Any source) {
        if (source == null) {
            return "";
        }
        return switchKinds(source);
    }

    private String switchKinds(Any source) {
        try {
            ORB orb = fiberhomeTransmitSessionFactory.getCorbaConnect().getOrb();
            DynAnyFactory factory = DynAnyFactoryHelper.narrow(orb.resolve_initial_references("DynAnyFactory"));
            StringBuilder result = new StringBuilder();
            switch (source.type().kind().value()) {
                case TCKind._tk_char:
                    result.append(source.extract_char());
                    break;
                case TCKind._tk_null:
                    break;
                case TCKind._tk_boolean:
                    result.append(source.extract_boolean());
                    break;
                case TCKind._tk_short:
                    result.append(source.extract_short());
                    break;
                case TCKind._tk_long:
                    result.append(source.extract_long());
                    break;
                case TCKind._tk_double:
                    result.append(source.extract_double());
                    break;
                case TCKind._tk_float:
                    result.append(source.extract_float());
                    break;
                case TCKind._tk_octet:
                    result.append(source.extract_octet());
                    break;
                case TCKind._tk_ulong:
                    result.append(source.extract_ulong());
                    break;
                case TCKind._tk_string:
                    result.append(source.extract_string());
                    break;
                case TCKind._tk_objref: {
                    result.append(source.extract_Object());
                    break;
                }
                case TCKind._tk_enum: {
                    DynEnum dynEnum = (DynEnum) factory.create_dyn_any(source);
                    result.append(dynEnum.get_as_string());
                    break;
                }
                case TCKind._tk_any: {
                    Any any = factory.create_dyn_any(source).get_any();
                    result.append(convert(any));
                    break;
                }
                case TCKind._tk_struct:
                case TCKind._tk_except: {
                    DynStruct dynStruct = (DynStruct) factory.create_dyn_any(source);
                    NameValuePair[] membersArray = dynStruct.get_members();
                    String members = Arrays.stream(membersArray).map(member -> member.id + "=" + convert(member.value))
                            .collect(Collectors.joining(","));
                    result.append(members);
                    break;
                }
                case TCKind._tk_union:
                    DynUnion dynUnion = (DynUnion) factory.create_dyn_any(source);
                    result.append(dynUnion.member_name()).append("=");
                    result.append(convert(dynUnion.member().to_any()));
                    break;
                case TCKind._tk_sequence:
                    DynSequence dynSequence = (DynSequence) factory.create_dyn_any(source);
                    Any[] dynSequenceElements = dynSequence.get_elements();
                    String sequenceElements = Arrays.stream(dynSequenceElements).map(this::convert)
                            .collect(Collectors.joining(","));
                    result.append(sequenceElements);
                    break;
                case TCKind._tk_array:
                    DynArray dynArray = (DynArray) factory.create_dyn_any(source);
                    Any[] dynArrayElements = dynArray.get_elements();
                    String arrayElements = Arrays.stream(dynArrayElements).map(this::convert)
                            .collect(Collectors.joining(","));
                    result.append(arrayElements);
                    break;
                case TCKind._tk_alias:
                    NameAndStringValue_T[] nameValuesArray = NVSList_THelper.extract(source);
                    String nameValues = Arrays.stream(nameValuesArray)
                            .map(nameAndStringValue -> nameAndStringValue.name + "=" + nameAndStringValue.value)
                            .collect(Collectors.joining(","));
                    result.append(nameValues);
                    break;
                default:
                    result.append(source.type().kind().value());
            }
            return result.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
