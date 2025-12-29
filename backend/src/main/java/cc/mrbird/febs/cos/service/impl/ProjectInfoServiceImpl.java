package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.ApplicationInfoMapper;
import cc.mrbird.febs.cos.dao.ProjectInfoMapper;
import cc.mrbird.febs.cos.dao.StudentInfoMapper;
import cc.mrbird.febs.cos.entity.ApplicationInfo;
import cc.mrbird.febs.cos.entity.ProjectInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.IProjectInfoService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 资助项目表 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ProjectInfoServiceImpl extends ServiceImpl<ProjectInfoMapper, ProjectInfo> implements IProjectInfoService {

    private final ApplicationInfoMapper applicationInfoMapper;

    private final StudentInfoMapper studentInfoMapper;

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

    /**
     * 项目进度
     *
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> queryProjectProcess() {
        // 获取所有可申请项目
        List<ProjectInfo> projectList = baseMapper.selectList(Wrappers.<ProjectInfo>lambdaQuery());
        if (CollectionUtil.isEmpty(projectList)) {
            return Collections.emptyList();
        }
        // 返回数据
        List<LinkedHashMap<String, Object>> result = new ArrayList<>();
        // 获取所有申请记录
        List<ApplicationInfo> allApplicationList = applicationInfoMapper.selectList(Wrappers.<ApplicationInfo>lambdaQuery());
        Map<Integer, List<ApplicationInfo>> applicationMap = allApplicationList.stream().collect(Collectors.groupingBy(ApplicationInfo::getProjectId));
        projectList.forEach(projectInfo -> {
            LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>() {
                {
                    put("project", projectInfo);
                }
            };
            List<ApplicationInfo> applicationList = applicationMap.get(projectInfo.getId());
            if (CollectionUtil.isNotEmpty(applicationList)) {
                projectInfo.setApplicationNum(applicationList.size());
                List<LinkedHashMap<String, Object>> currentApplicationList = applicationInfoMapper.queryApplicationByIds(applicationList.stream().map(ApplicationInfo::getId).collect(Collectors.toList()));
                map.put("applicationList", currentApplicationList);
            } else {
                projectInfo.setApplicationNum(0);
                map.put("applicationList", Collections.emptyList());
            }
            result.add(map);
        });
        return result;
    }

    /**
     * 查询可申请项目信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public List<ProjectInfo> queryProjectByAvailable(Integer userId) {
        // 学生信息
        StudentInfo studentInfo = studentInfoMapper.selectOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, userId));
        if (studentInfo == null) {
            return Collections.emptyList();
        }
        // 获取所有可申请项目
        List<ProjectInfo> projectList = baseMapper.selectList(Wrappers.<ProjectInfo>lambdaQuery());
        if (CollectionUtil.isEmpty(projectList)) {
            return Collections.emptyList();
        }
        // 获取所有申请记录
        List<ApplicationInfo> allApplicationList = applicationInfoMapper.selectList(Wrappers.<ApplicationInfo>lambdaQuery().ne(ApplicationInfo::getStatus, "已驳回"));
        Map<Integer, List<ApplicationInfo>> applicationMap = allApplicationList.stream().collect(Collectors.groupingBy(ApplicationInfo::getProjectId));
        projectList.forEach(projectInfo -> {
            projectInfo.setSelfAudit("0");
            List<ApplicationInfo> applicationList = applicationMap.get(projectInfo.getId());
            if (CollectionUtil.isEmpty(applicationList)) {
                projectInfo.setApplicationNum(0);
            } else {
                projectInfo.setApplicationNum(applicationList.size());
                if (applicationList.stream().anyMatch(applicationInfo -> applicationInfo.getUserId().equals(studentInfo.getId()))) {
                    projectInfo.setSelfAudit("1");
                }
            }

        });
        return projectList;
    }
}
