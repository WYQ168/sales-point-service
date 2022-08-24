package com.sales.system.utils;

import com.sales.common.security.utils.SecurityUtils;
import com.sales.system.domain.entity.SysUser;
import org.springframework.beans.BeanUtils;

public class UserDataUtils {

    public static SysUser getUserData(){
        SysUser sysUser=new SysUser();
        BeanUtils.copyProperties(SecurityUtils.getSysUserData(),sysUser);
        return sysUser;
    }

}
