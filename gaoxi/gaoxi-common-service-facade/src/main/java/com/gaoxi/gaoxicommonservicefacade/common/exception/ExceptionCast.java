package com.gaoxi.gaoxicommonservicefacade.common.exception;

import com.gaoxi.gaoxicommonservicefacade.common.base.response.ResultCode;
/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-14 17:31
 **/
public class ExceptionCast {

    public static void cast(ResultCode resultCode) throws CustomException {
        throw new CustomException(resultCode);
    }
}
