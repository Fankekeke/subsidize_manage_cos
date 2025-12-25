package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.FinancialStatusInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.LinkedHashMap;

/**
 * 学生经济情况表 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IFinancialStatusInfoService extends IService<FinancialStatusInfo> {

    /**
     * 分页获取学生经济情况信息
     *
     * @param page                分页对象
     * @param financialStatusInfo 学生经济情况信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryFinancialStatusPage(Page<FinancialStatusInfo> page, FinancialStatusInfo financialStatusInfo);
}
