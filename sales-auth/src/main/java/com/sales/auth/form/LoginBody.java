package com.sales.auth.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录对象
 *
 * @author sales
 */
@Data
public class LoginBody {
    /**
     * 登陆用户类型:wsPro=喔刷伙伴APP用户，wsMerchant=喔刷商家版用户
     */
    @NotBlank(message = "用户类型不能为空")
    private String userType;
    /**
     * 用户名/手机号
     */
    @NotBlank(message = "用户名/手机号不能为空")
    @Length(min = 2, max = 20, message = "用户名必须在2-20位之内")
    private String username;
    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 5, max = 20, message = "密码必须在5-20位之内")
    private String password;
    /**
     * 短信验证码
     */
    // @NotBlank(message = "短信验证码不能为空")
    private String smsCode;
    private String code;
    /**
     * 登陆方式: password=密码，smsCode=短信验证码,wechat=第三方微信登陆
     */
    @NotBlank(message = "登录类型不能为空")
    private String loginType;

/*
    @NotBlank(message = "uuid不能为空")
*/
    private String uuid;
}
