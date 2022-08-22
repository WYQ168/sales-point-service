package com.sales.auth.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterReq implements Serializable {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 验证码
     */
    private String code;

}
