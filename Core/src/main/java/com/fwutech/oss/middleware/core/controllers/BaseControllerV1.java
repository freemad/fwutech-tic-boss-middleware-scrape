package com.fwutech.oss.middleware.core.controllers;

import com.fwutech.oss.middleware.commons.beans.base.Receipt;
import com.fwutech.oss.middleware.commons.beans.msgbroker.RequestEvent;
import com.fwutech.oss.middleware.commons.enums.CommonErrorCode;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import com.fwutech.oss.middleware.commons.globals.ApiRoute;
import com.fwutech.oss.middleware.commons.responses.Result;
import com.fwutech.oss.middleware.core.intefaces.IRequestEventDispatcherService;
import com.fwutech.oss.middleware.core.utils.ReceiptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = ApiRoute.V1_0 + ApiRoute.VENDORS + "/{vendorId}" + ApiRoute.NETWORKS + "/{networkId}")
public abstract class BaseControllerV1 {

    @Autowired
    private IRequestEventDispatcherService requestEventDispatcherService;

    protected Result<Receipt> wrapInBusinessException(Callable<RequestEvent> controllerBusinessExcerpt) {
        Result<Receipt> result;
        try {
            RequestEvent requestEvent = controllerBusinessExcerpt.call();
            requestEventDispatcherService.dispatchRequestEvent(requestEvent);
            return ReceiptUtil.prepareSuccessResult(requestEvent);
        } catch (BusinessException businessException) {
            result = ReceiptUtil.prepareErrorResult(businessException);
        } catch (Exception exception) {
            result = ReceiptUtil.prepareErrorResult(new BusinessException(CommonErrorCode.BAD_REQUEST_EXCEPTION, exception));
        }
        return result;
    }

}
