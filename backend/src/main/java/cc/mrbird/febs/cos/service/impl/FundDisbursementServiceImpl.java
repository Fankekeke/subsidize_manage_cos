package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.FundDisbursementMapper;
import cc.mrbird.febs.cos.entity.FundDisbursement;
import cc.mrbird.febs.cos.service.IFundDisbursementService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 资金发放记录表 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class FundDisbursementServiceImpl extends ServiceImpl<FundDisbursementMapper, FundDisbursement> implements IFundDisbursementService {

    /**
     * 分页获取资金发放记录信息
     *
     * @param page             分页对象
     * @param fundDisbursement 资金发放记录信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectFundDisbursementPage(IPage<FundDisbursement> page, FundDisbursement fundDisbursement) {
        return baseMapper.selectFundDisbursementPage(page, fundDisbursement);
    }
}
