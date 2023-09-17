package com.fwutech.oss.middleware.adapters.ncedata.interceptor;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SOAPLogHandler extends BasicHandler {

    private static Logger LOG = LoggerFactory.getLogger(SOAPLogHandler.class);
    private static final long serialVersionUID = 1L;

    @Override
    public void invoke(MessageContext msgContext) throws AxisFault {
        if (msgContext.getResponseMessage() != null && msgContext.getResponseMessage().getSOAPPart() != null) {
            LOG.info(" Response = " + msgContext.getResponseMessage().getSOAPPartAsString());
        } else {
            if (msgContext.getRequestMessage() != null && msgContext.getRequestMessage().getSOAPPartAsString() != null) {
                LOG.info(" Request = " + msgContext.getRequestMessage().getSOAPPartAsString());
            }
        }
    }
}
