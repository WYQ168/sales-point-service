package com.sales.system.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sales.common.core.text.Convert;
import com.sales.system.domain.entity.LoginUser;
import com.sales.system.domain.entity.SysUser;
import com.sales.system.enums.SystemEnum;
import com.sales.system.mapper.SysUserMapper;
import com.sales.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 系统用户 服务层实现
 *
 * @author zhangtailong
 * @date 2022-08-24
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询系统用户信息
     *
     * @param userName 系统用户名称
     * @return 系统用户信息
     */
    @Override
    public JSONObject getSysUserInfo(String userName) {
        /*查询系统用户*/
        SysUser user = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getDelFlag, Integer.parseInt(SystemEnum.DELETE_STATUS_1.getValue()))
                .eq(SysUser::getSysUsername, userName)
        );
        if (Objects.isNull(user)) {
            return null;
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", UUID.randomUUID().toString());
            jsonObject.put("userid", user.getSysUserId());
            jsonObject.put("userType", SystemEnum.USER_TYPE_2.getValue());
            jsonObject.put("username", user.getSysUsername());
            jsonObject.put("loginTime", System.currentTimeMillis());
            jsonObject.put("name", user.getRealName());
            jsonObject.put("user", user);
            return jsonObject;
        }
    }

}
