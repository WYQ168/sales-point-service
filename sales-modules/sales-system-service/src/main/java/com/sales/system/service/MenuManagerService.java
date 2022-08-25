package com.sales.system.service;

import com.sales.system.domain.entity.Menu;
import com.sales.system.domain.pojo.MenuPojo;
import com.sales.system.domain.response.MenuResp;

import java.util.List;

public interface MenuManagerService {


    /**
     * 获取菜单列表
     *
     * @return
     */
    List<MenuResp> getMenuDataList();

    /**
     * 新增菜单信息
     *
     * @param menu
     */
    void addMenuData(Menu menu);

    /**
     * 编辑菜单信息
     *
     * @param menu
     */
    void editMenuData(Menu menu);

    /**
     * 获取用户菜单权限列表
     *
     * @return
     */
    List<MenuPojo> getRouterData();

}
