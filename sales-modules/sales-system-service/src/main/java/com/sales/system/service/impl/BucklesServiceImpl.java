package com.sales.system.service.impl;

import com.sales.common.core.text.Convert;
import com.sales.system.domain.entity.Buckles;
import com.sales.system.mapper.BucklesMapper;
import com.sales.system.service.IBucklesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 扣费 服务层实现
 *
 * @author zhangtailong
 * @date 2022-08-23
 */
@Service
public class BucklesServiceImpl implements IBucklesService {
    @Autowired
    private BucklesMapper bucklesMapper;

    /**
     * 查询扣费信息
     *
     * @param bucklesId 扣费ID
     * @return 扣费信息
     */
    @Override
    public Buckles selectBucklesById(Long bucklesId) {
        return bucklesMapper.selectBucklesById(bucklesId);
    }

    /**
     * 查询扣费列表
     *
     * @param buckles 扣费信息
     * @return 扣费集合
     */
    @Override
    public List<Buckles> selectBucklesList(Buckles buckles) {

        return bucklesMapper.selectBucklesList(buckles);
    }

    /**
     * 新增扣费
     *
     * @param buckles 扣费信息
     * @return 结果
     */
    @Override
    public int insertBuckles(Buckles buckles) {
        return bucklesMapper.insertBuckles(buckles);
    }

    /**
     * 修改扣费
     *
     * @param buckles 扣费信息
     * @return 结果
     */
    @Override
    public int updateBuckles(Buckles buckles) {
        return bucklesMapper.updateBuckles(buckles);
    }

    /**
     * 删除扣费对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBucklesByIds(String ids) {

        return bucklesMapper.deleteBucklesByIds(Convert.toStrArray(ids));
    }


}
