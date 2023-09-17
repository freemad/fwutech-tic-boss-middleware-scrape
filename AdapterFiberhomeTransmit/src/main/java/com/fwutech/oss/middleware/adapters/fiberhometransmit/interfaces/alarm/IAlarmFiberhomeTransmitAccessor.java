package com.fwutech.oss.middleware.adapters.fiberhometransmit.interfaces.alarm;

import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import mtnm.tmforum.org.globaldefs.NameAndStringValue_T;
import mtnm.tmforum.org.globaldefs.ProcessingFailureException;
import mtnm.tmforum.org.notifications.EventList_THolder;
import mtnm.tmforum.org.notifications.PerceivedSeverity_T;

public interface IAlarmFiberhomeTransmitAccessor {

    EventList_THolder getAllEMSAndMEActiveAlarms(String[] excludeProbCauses, PerceivedSeverity_T[] excludeSeverities,
                                                 int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    EventList_THolder getAllActiveAlarms(NameAndStringValue_T[] neNames, String[] excludeProbCauses,
                                         PerceivedSeverity_T[] excludeSeverities, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;

    EventList_THolder getMEHistoryAlarms(NameAndStringValue_T[] neNames, String[] excludeProbCauses,
                                         PerceivedSeverity_T[] excludeSeverities, String beginTime,
                                         String endTime, int pageIndex, int pageSize) throws ProcessingFailureException, BusinessException;
}
