package com.sales.file.enums;


import com.sales.common.core.web.result.ICode;

/**
 *@Title:Code
 *@Desc: 常用响应编码
 *@Author: Pengwc
 *@Date: 2019-3-20 20:49
 */
public enum FileCodeEnum implements ICode {

	ERR_FILE_ID_NOT_EXEIST(15003001,"文件不存在"),
	ERR_FILE_UPLOAD(15003002,"文件上传失败"),
	ERR_FILE_DOWNLOAD(15003002,"文件下载失败"),
	ERR_FILE_SUFFIX_NOT_EXIST(15003003,"文件缺少后缀名"),
	;
	private Integer code;
	private String msg;


	public void setCode(Integer code) {
		this.code = code;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}

	private FileCodeEnum(Integer code, String msg) {
		
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}
}
