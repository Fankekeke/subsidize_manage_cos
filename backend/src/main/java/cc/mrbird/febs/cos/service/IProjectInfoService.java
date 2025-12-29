package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ProjectInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.LinkedHashMap;
import java.util.List;

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

    /**
     * 项目进度
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryProjectProcess();

    /**
     * 查询可申请项目信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<ProjectInfo> queryProjectByAvailable(Integer userId);
}
