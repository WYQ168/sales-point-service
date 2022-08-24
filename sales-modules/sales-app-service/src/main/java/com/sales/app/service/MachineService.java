package com.sales.app.service;

import com.sales.app.domain.entity.Machine;
import com.sales.app.domain.request.BaseQueryReq;
import com.sales.app.domain.request.MachineIssuedReq;
import com.sales.app.domain.request.MyMachineReq;
import com.sales.app.domain.response.PerformanceResp;

import java.util.List;

public interface MachineService {

    /**
     * 查询我的机具列表
     *
     * @param req 我的机具请求体
     * @return 我的机具
     */
    List<Machine> getMyMachineList(MyMachineReq req);

    /**
     * 我的机具下发操作，下发完成后，对方成为我的商户，该机具被我的商户绑定
     *
     * @param req 机具下发请求体
     * @return 结果
     */
    Integer machineIssued(MachineIssuedReq req);

    /**
     * 获取我的个人业绩数据
     *
     * @param req 基础查询类
     * @return 个人业绩信息
     */
    PerformanceResp getPersonalPerformance(BaseQueryReq req);

    /**
     * 获取我的伙伴业绩数据
     *
     * @param req 基础查询类
     * @return 伙伴业绩信息
     */
    PerformanceResp getPartnerPerformance(BaseQueryReq req);
}
