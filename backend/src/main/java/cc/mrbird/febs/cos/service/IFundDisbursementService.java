package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.FundDisbursement;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.LinkedHashMap;

/**
 * 资金发放记录表 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IFundDisbursementService extends IService<FundDisbursement> {

    /**
     * 分页获取资金发放记录信息
     *
     * @param page             分页对象
     * @param fundDisbursement 资金发放记录信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectFundDisbursementPage(IPage<FundDisbursement> page, FundDisbursement fundDisbursement);
}
