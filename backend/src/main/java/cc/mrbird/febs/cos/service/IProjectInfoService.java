package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ProjectInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.LinkedHashMap;

/**
 * 资助项目表 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IProjectInfoService extends IService<ProjectInfo> {

    /**
     * 分页获取资助项目信息
     *
     * @param page        分页对象
     * @param projectInfo 资助项目信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryProjectPage(IPage<ProjectInfo> page, ProjectInfo projectInfo);
}
