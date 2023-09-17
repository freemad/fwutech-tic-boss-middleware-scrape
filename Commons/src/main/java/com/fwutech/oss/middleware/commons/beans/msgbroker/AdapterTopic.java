package com.fwutech.oss.middleware.commons.beans.msgbroker;

import com.fwutech.oss.middleware.commons.enums.ProtocolType;
import com.fwutech.oss.middleware.commons.enums.TopicType;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdapterTopic implements Serializable {
    private static final long serialVersionUID = -1781843729428979379L;

    private static final String DELIMITER = "_";
    private static final String TOPIC = "TOPIC";
    private ProtocolType protocolType;
    private TopicType topicType;

    public String getName() {
        return TOPIC
                .concat(DELIMITER)
                .concat(protocolType.name())
                .concat(DELIMITER)
                .concat(topicType.name());
    }
}
