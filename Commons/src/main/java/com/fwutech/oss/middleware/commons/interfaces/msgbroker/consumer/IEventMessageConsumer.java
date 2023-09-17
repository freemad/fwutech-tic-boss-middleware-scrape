package com.fwutech.oss.middleware.commons.interfaces.msgbroker.consumer;

import com.fwutech.oss.middleware.commons.beans.msgbroker.BaseEvent;

public interface IEventMessageConsumer<Event extends BaseEvent> {
    Event getEvent();
}
