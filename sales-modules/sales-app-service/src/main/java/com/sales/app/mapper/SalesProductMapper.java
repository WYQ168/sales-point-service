package com.sales.app.mapper;

import com.sales.app.domain.entity.SalesProduct;import com.sales.app.domain.request.MallProductReq;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface SalesProductMapper {
    int deleteByPrimaryKey(Long productId);

    int insert(SalesProduct record);

    int insertSelective(SalesProduct record);

    SalesProduct selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(SalesProduct record);

    int updateByPrimaryKey(SalesProduct record);

    /**
     * 根据查询条件获取商品列表
     *
     * @param req 商城商品请求类
     * @return 商品列表
     */
    List<SalesProduct> selectAllByCondition(MallProductReq req);

    /**
     * 根据产品序列号查找商品信息
     *
     * @param productSn 产品序列号
     * @return POS产品
     */
    SalesProduct selectByProductSn(String productSn);

    /**
     * 获取积分兑换商品列表
     *
     * @param productLabel 商品标签 ：MPOS、电签、旺铺
     * @return 积分兑换商品列表
     */
    List<SalesProduct> selectIntegralProducts(@Param("productLabel") String productLabel);
}