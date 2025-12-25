package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ApplicationInfo;
import cc.mrbird.febs.cos.mapper.ApplicationInfoMapper;
import cc.mrbird.febs.cos.service.IApplicationInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 资助申请记录表 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class ApplicationInfoServiceImpl extends ServiceImpl<ApplicationInfoMapper, ApplicationInfo> implements IApplicationInfoService {

    /**
     * 分页获取资助申请记录信息
     *
     * @param page            分页对象
     * @param applicationInfo 资助申请记录信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryApplicationPage(Page<ApplicationInfo> page, ApplicationInfo applicationInfo) {
        return baseMapper.queryApplicationPage(page, applicationInfo);
    }
}
