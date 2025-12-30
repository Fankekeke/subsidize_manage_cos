package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.InsuranceInfo;
import cc.mrbird.febs.cos.dao.InsuranceInfoMapper;
import cc.mrbird.febs.cos.service.IInsuranceInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 参保情况 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class InsuranceInfoServiceImpl extends ServiceImpl<InsuranceInfoMapper, InsuranceInfo> implements IInsuranceInfoService {

    /**
     * 分页获取学生参保情况信息
     *
     * @param page          分页对象
     * @param insuranceInfo 学生参保情况信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryInsuranceInfoPage(Page<InsuranceInfo> page, InsuranceInfo insuranceInfo) {
        return baseMapper.queryInsuranceInfoPage(page, insuranceInfo);
    }
}
