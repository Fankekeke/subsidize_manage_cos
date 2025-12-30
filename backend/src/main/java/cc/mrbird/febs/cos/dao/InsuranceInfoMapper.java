package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.InsuranceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.LinkedHashMap;

/**
 * 参保情况 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface InsuranceInfoMapper extends BaseMapper<InsuranceInfo> {

    /**
     * 分页获取学生参保情况信息
     *
     * @param page          分页对象
     * @param insuranceInfo 学生参保情况信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryInsuranceInfoPage(Page<InsuranceInfo> page, InsuranceInfo insuranceInfo);
}
