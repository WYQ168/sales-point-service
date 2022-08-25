package com.sales.system.domain.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

@Data
public class MenuPojo implements Serializable {

    /**
     * 名称
     */
    private String name;

    /**
     * 地址
     */
    private String path;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 重定向-noRedirect
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String redirect;

    /**
     * 组件
     */
    private String component="Layout";

    /**
     * 是否一直显示
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Boolean alwaysShow;

    /**
     * 详情信息
     */
    private meta meta;

    /**
     * 子菜单
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuPojo>children;

    @Data
    public static class meta{

        /**
         * 标题
         */
        private String title;

        /**
         * icon
         */
        private String icon;

        /**
         * 是否缓存
         */
        private Boolean noCache=false;

        /**
         * 外链
         */
        private String link;

    }

}
