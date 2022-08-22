package com.sales.auth.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 用户注册对象
 *
 * @author sales
 */
@Data
public class RegisterBody {
	/**
	 * 登陆用户类型:manage=管理端用户，mobile=移动端用户
	 */
	@NotBlank(message = "用户类型不能为空")
	private String userType;

	@Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$", message = "请输入正确的手机号")
	@NotBlank(message = "手机号不能为空")
	private String phone;

	/**
	 * 用户密码
	 */
	@NotBlank(message = "密码不能为空")
	@Length(min = 5, max = 20, message = "密码必须在5-20位之内")
	private String password;

	@NotBlank(message = "确认密码不能为空")
	@Length(min = 5, max = 20, message = "密码必须在5-20位之内")
	private String password2;
	/**
	 * 短信验证码
	 */
	@NotBlank(message = "短息验证码不能为空")
	private String smsCode;

	@NotBlank(message = "uuid不能为空")
	private String uuid;
}
