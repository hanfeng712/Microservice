package com.gaoxi.gaoxicommonservicefacade.common.auth.response;

import com.gaoxi.gaoxicommonservicefacade.common.base.response.ResponseResult;
import com.gaoxi.gaoxicommonservicefacade.common.base.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
public class LoginResult extends ResponseResult {
    public LoginResult(ResultCode resultCode,String token) {
        super(resultCode);
        this.token = token;
    }
    private String token;
}
