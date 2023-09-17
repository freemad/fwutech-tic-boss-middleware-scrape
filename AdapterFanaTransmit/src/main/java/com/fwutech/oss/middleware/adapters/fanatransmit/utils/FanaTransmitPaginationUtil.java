package com.fwutech.oss.middleware.adapters.fanatransmit.utils;

import com.fwutech.oss.middleware.adapters.fanatransmit.beans.FanaTransmitHolderValue;
import com.fwutech.oss.middleware.adapters.fanatransmit.enums.FanaTransmitErrorCode;
import com.fwutech.oss.middleware.commons.exceptions.BusinessException;
import com.fwutech.oss.middleware.commons.utils.ThrowableBiFunction;
import mtnm.tmforum.org.globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FanaTransmitPaginationUtil {
    public static <Holder, HolderValue, IteratorValue> List<HolderValue> getDataIterativelyV2(
            Holder holder,
            HolderValue[] holderValue,
            IteratorValue iteratorValue,
            ThrowableBiFunction<IteratorValue, Holder, FanaTransmitHolderValue<HolderValue>> function,
            int pageSize, int pageIndex) throws BusinessException, ProcessingFailureException {
        try {
            int startIx = (pageIndex - 1) * pageSize;
            int endIx = pageIndex * pageSize;
            List<HolderValue> tList = new ArrayList<>();
            if (holderValue.length == 0) {
                return null;
            }
            if (startIx == 0) {
                tList.add(holderValue[0]);
            }
            boolean hasNext = iteratorValue != null;
            for (int i = 0; i < endIx - 1; i++) {
                if (iteratorValue == null || !hasNext) {
                    break;
                }
                FanaTransmitHolderValue<HolderValue> fanaTransmitHolderValue =
                        function.apply(iteratorValue, holder);
                holderValue = fanaTransmitHolderValue.getHolderValues();
                hasNext = fanaTransmitHolderValue.isHasNext();
                if (i < startIx - 1) {
                    continue;
                }
                if (holderValue.length == 0) {
                    break;
                }
                tList.add(holderValue[0]);
            }
            if (tList.size() == 0) {
                return null;
            }
            return tList;
        } catch (ProcessingFailureException ex) {
            throw ex;
        } catch (Exception e) {
            throw new BusinessException(FanaTransmitErrorCode.FANA_TRANSMIT_DATA_FETCH_EXCEPTION, e);
        }
    }

    public static <Holder, HolderValue, IteratorValue> List<HolderValue> getDataIterativelyV3(
            Holder holder,
            HolderValue[] holderValue,
            IteratorValue iteratorValue,
            ThrowableBiFunction<IteratorValue, Holder, FanaTransmitHolderValue<HolderValue>> function,
            int pageSize, int pageIndex, int iteratorPageSize) throws BusinessException, ProcessingFailureException {
        try {
            int startIx = (pageIndex - 1) * pageSize;
            int endIx = (pageIndex * pageSize) - 1;
            List<HolderValue> tList = new ArrayList<>();
            if (holderValue.length == 0) {
                return null;
            }
            // check whether client wants the first page fetched or not
            if (startIx < iteratorPageSize) {
                // the size of data is less than start index requested by client
                if (startIx > holderValue.length - 1) {
                    return null;
                }
                // all of data requested by client is in the first page fetched
                if (endIx < iteratorPageSize) {
                    Collections.addAll(tList, Arrays.copyOfRange(holderValue, startIx,
                            // check if the size of data is less than end index, return all of data
                            // otherwise, return till end index
                            Math.min(holderValue.length, endIx + 1)));
                    return tList;
                }
                // part of data requested by client is in the first page fetched
                Collections.addAll(tList, Arrays.copyOfRange(holderValue, startIx,
                        // put all of fetched data in the list, the maximum value is equal to iterator page size
                        holderValue.length));
            }
            boolean hasNext = iteratorValue != null;
            // the count of call next_n method recording to end index which client requested & page size
            int nextNCount = (int) Math.floor((float) endIx / (float) iteratorPageSize);
            for (int i = 1; i <= nextNCount; i++) {
                // if the next page is not exist, no need to continue loop
                if (iteratorValue == null || !hasNext) {
                    break;
                }
                // fetch the next page
                FanaTransmitHolderValue<HolderValue> fanaTransmitHolderValue =
                        function.apply(iteratorValue, holder);
                // update holderValue & hasNext according to this page
                holderValue = fanaTransmitHolderValue.getHolderValues();
                hasNext = fanaTransmitHolderValue.isHasNext();
                // if the start index exceeds the size of this page, data do not need to save and return
                if (((i + 1) * iteratorPageSize) - 1 < startIx) {
                    continue;
                }
                // if data is empty, no need to continue the loop
                if (holderValue.length == 0) {
                    break;
                }
                // if start index is in this page and data is finished before start index,
                // return null because there is no data in index requested by client
                if (startIx >= iteratorPageSize * i && startIx % iteratorPageSize > holderValue.length - 1) {
                    return null;
                }
                // if end index is in this page, no need to continue fetching data
                if (endIx < iteratorPageSize * (i + 1)) {
                    Collections.addAll(tList, Arrays.copyOfRange(holderValue,
                            calcStartFetchPointOfPageI(startIx, iteratorPageSize, i),
                            // if the size of data is less than the page size, all of data put into the list
                            // otherwise put data till the end index calculated by mod operator
                            Math.min(holderValue.length, (endIx % iteratorPageSize) + 1)));
                    return tList;
                }
                // this page is part of the requested data which must put in the final list
                Collections.addAll(tList, Arrays.copyOfRange(holderValue,
                        calcStartFetchPointOfPageI(startIx, iteratorPageSize, i),
                        // put all of fetched data in the list, the maximum value is equal to iterator page size
                        holderValue.length));
            }
            if (tList.size() == 0) {
                return null;
            }
            return tList;
        } catch (ProcessingFailureException ex) {
            throw ex;
        } catch (Exception e) {
            throw new BusinessException(FanaTransmitErrorCode.FANA_TRANSMIT_DATA_FETCH_EXCEPTION, e);
        }
    }

    private static int calcStartFetchPointOfPageI(int startIx, int iteratorPageSize, int pageNumber) {
        // if startIx is in the previous pages, the start index in this page is equal to the start of array
        // otherwise, calculate start index in this page by mod operator
        return startIx < iteratorPageSize * pageNumber ? 0 : startIx % iteratorPageSize;
    }
}
