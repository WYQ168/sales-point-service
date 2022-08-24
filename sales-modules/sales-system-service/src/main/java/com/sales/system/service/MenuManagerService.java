package com.sales.system.service;

import com.sales.system.domain.entity.Menu;
import com.sales.system.domain.resp.MenuResp;

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
}
