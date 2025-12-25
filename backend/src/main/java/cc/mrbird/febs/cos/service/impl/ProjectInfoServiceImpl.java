package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.ProjectInfoMapper;
import cc.mrbird.febs.cos.entity.ProjectInfo;
import cc.mrbird.febs.cos.service.IProjectInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 资助项目表 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class ProjectInfoServiceImpl extends ServiceImpl<ProjectInfoMapper, ProjectInfo> implements IProjectInfoService {

    /**
     * 分页获取资助项目信息
     *
     * @param page        分页对象
     * @param projectInfo 资助项目信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryProjectPage(IPage<ProjectInfo> page, ProjectInfo projectInfo) {
        return baseMapper.queryProjectPage(page, projectInfo);
    }
}
