package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.FinancialStatusInfo;
import cc.mrbird.febs.cos.mapper.FinancialStatusInfoMapper;
import cc.mrbird.febs.cos.service.IFinancialStatusInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 学生经济情况表 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class FinancialStatusInfoServiceImpl extends ServiceImpl<FinancialStatusInfoMapper, FinancialStatusInfo> implements IFinancialStatusInfoService {

    /**
     * 分页获取学生经济情况信息
     *
     * @param page                分页对象
     * @param financialStatusInfo 学生经济情况信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryFinancialStatusPage(Page<FinancialStatusInfo> page, FinancialStatusInfo financialStatusInfo) {
        return baseMapper.queryFinancialStatusPage(page, financialStatusInfo);
    }
}
