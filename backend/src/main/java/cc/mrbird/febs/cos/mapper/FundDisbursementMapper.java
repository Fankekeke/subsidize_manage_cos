package cc.mrbird.febs.cos.mapper;

import cc.mrbird.febs.cos.entity.FundDisbursement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.LinkedHashMap;

/**
 * 资金发放记录表 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface FundDisbursementMapper extends BaseMapper<FundDisbursement> {

    /**
     * 分页获取资金发放记录信息
     *
     * @param page             分页对象
     * @param fundDisbursement 资金发放记录信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectFundDisbursementPage(IPage<FundDisbursement> page, FundDisbursement fundDisbursement);
}
