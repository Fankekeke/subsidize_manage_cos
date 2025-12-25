package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ProjectInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.LinkedHashMap;

/**
 * 资助项目表 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface ProjectInfoMapper extends BaseMapper<ProjectInfo> {

    /**
     * 分页获取资助项目信息
     *
     * @param page        分页对象
     * @param projectInfo 资助项目信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryProjectPage(IPage<ProjectInfo> page, ProjectInfo projectInfo);
}
