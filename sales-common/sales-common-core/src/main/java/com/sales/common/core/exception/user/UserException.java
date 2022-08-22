package com.sales.common.core.exception.user;

import com.sales.common.core.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author sales
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
