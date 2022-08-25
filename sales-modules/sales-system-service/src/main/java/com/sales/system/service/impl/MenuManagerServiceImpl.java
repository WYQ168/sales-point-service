package com.sales.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sales.common.core.exception.BaseException;
import com.sales.system.domain.entity.Menu;
import com.sales.system.domain.pojo.MenuPojo;
import com.sales.system.mapper.MenuMapper;
import com.sales.system.domain.entity.SysUser;
import com.sales.system.domain.response.MenuResp;
import com.sales.system.enums.SystemEnum;
import com.sales.system.service.MenuManagerService;
import com.sales.system.utils.UserDataUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 菜单管理业务层
 * @author: wuyingqiang
 * @date: 2022-08-24 15:41
 */

@Service
public class MenuManagerServiceImpl implements MenuManagerService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuResp> getMenuDataList() {
        List<Menu> menuList = menuMapper.selectList(Wrappers.<Menu>lambdaQuery()
                .eq(Menu::getDelFlag, Integer.parseInt(SystemEnum.DELETE_STATUS_1.getValue()))
        );
        List<Menu> oneList = menuList.stream().filter(entity -> Objects.isNull(entity.getPid()) || entity.getPid() == 0).collect(Collectors.toList());
        List<MenuResp> voList = new ArrayList<>();
        for (Menu menu : oneList) {
            MenuResp menuResp = new MenuResp();
            BeanUtils.copyProperties(menu, menuResp);
            menuResp.setChildren(menuList.stream().filter(entity -> !Objects.isNull(entity.getPid()) && entity.getPid().equals(menu.getId())).collect(Collectors.toList()));
            voList.add(menuResp);
        }
        return voList;
    }

    @Override
    public void addMenuData(Menu menu) {
        SysUser sysUser = UserDataUtils.getUserData();
        menu.setCreateTime(new Date());
        menu.setCreateBy(sysUser.getSysUserId());
        menu.setDelFlag(Boolean.FALSE);
        menuMapper.insert(menu);
    }

    @Override
    public void editMenuData(Menu menu) {
        SysUser sysUser = UserDataUtils.getUserData();
        Menu checkMenu = menuMapper.selectById(menu.getId());
        if (Objects.isNull(checkMenu)) {
            throw new BaseException("数据不存在");
        }
        checkMenu.setUpdateTime(new Date());
        checkMenu.setName(menu.getName());
        checkMenu.setComponent(menu.getComponent());
        checkMenu.setPid(menu.getPid());
        checkMenu.setSort(menu.getSort());
        checkMenu.setIcon(menu.getIcon());
        checkMenu.setPath(menu.getPath());
        checkMenu.setComponentName(menu.getComponentName());
        menuMapper.updateById(checkMenu);
    }

    @Override
    public List<MenuPojo> getRouterData() {
        SysUser sysUser = UserDataUtils.getUserData();
        List<Menu> menuList = menuMapper.getMenuByUserId(sysUser.getSysUserId());
        List<MenuPojo> voList = new ArrayList<>();
        List<Menu> oneList = menuList.stream().filter(entity -> entity.getPid() == 0L || Objects.isNull(entity.getPid())).collect(Collectors.toList());
        for (Menu menu : oneList) {
            List<Menu> twoList = menuList.stream().filter(entity -> !Objects.isNull(entity.getPid()) && entity.getPid().equals(menu.getId())).collect(Collectors.toList());
            MenuPojo menuPojo = new MenuPojo();
            menuPojo.setName(menu.getComponentName());
            menuPojo.setPath(menu.getPath());
            menuPojo.setHidden(menu.getHidden());
            menuPojo.setComponent(Objects.isNull(menu.getComponent()) ? "Layout" : menu.getComponent());
            MenuPojo.meta meta = new MenuPojo.meta();
            meta.setTitle(menu.getName());
            meta.setIcon(menu.getIcon());
            menuPojo.setMeta(meta);
            menuPojo.setAlwaysShow(true);
            menuPojo.setRedirect("noRedirect");
            List<MenuPojo> voTwoList = new ArrayList<>();
            for (Menu menu1 : twoList) {
                MenuPojo menuTwoPojo = new MenuPojo();
                menuTwoPojo.setName(menu1.getComponentName());
                menuTwoPojo.setPath(menu1.getPath());
                menuTwoPojo.setHidden(menu1.getHidden());
                menuTwoPojo.setComponent(Objects.isNull(menu1.getComponent()) ? "Layout" : menu1.getComponent());
                MenuPojo.meta metaTwo = new MenuPojo.meta();
                metaTwo.setTitle(menu1.getName());
                metaTwo.setIcon(menu1.getIcon());
                menuTwoPojo.setMeta(metaTwo);
                voTwoList.add(menuTwoPojo);
            }
            menuPojo.setChildren(voTwoList);
            voList.add(menuPojo);
        }
        return voList;
    }


}
