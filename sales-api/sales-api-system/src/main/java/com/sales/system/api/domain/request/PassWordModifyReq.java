package com.sales.system.api.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author 16554
 * @create 2022/7/19 11:25
 * @apiNote
 */
@Data
public class PassWordModifyReq {

	@ApiModelProperty("电话号码")
	@NotBlank(message = "电话不能为空")
	@Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$", message = "请输入正确的手机号")
	private String phone;

	/**
	 * uuid
	 */
	@NotBlank(message = "uuid不能为空")
	@ApiModelProperty("uuid")
	private String uuid;
	/**
	 * 短信验证码
	 */
	@NotBlank(message = "短信验证码不能为空")
	@ApiModelProperty("短信验证码")
	private String smsCode;
	/**
	 * 新密码
	 */
	@NotBlank(message = "密码不能为空")
	@ApiModelProperty("新密码")
	private String password;

	/**
	 * modify = password / modify = username ……
	 */
	@NotBlank(message = "修改标识不能为空")
	@ApiModelProperty("修改内容，如果是修改密码，则须赋值password")
	private String modify;
}
