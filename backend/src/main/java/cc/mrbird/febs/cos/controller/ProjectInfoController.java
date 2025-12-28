package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ProjectInfo;
import cc.mrbird.febs.cos.service.IProjectInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 资助项目表 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/project-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectInfoController {

    private final IProjectInfoService projectInfoService;

    /**
     * 分页获取资助项目信息
     *
     * @param page        分页对象
     * @param projectInfo 资助项目信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ProjectInfo> page, ProjectInfo projectInfo) {
        return R.ok(projectInfoService.queryProjectPage(page, projectInfo));
    }

    /**
     * 查询资助项目信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(projectInfoService.getById(id));
    }

    /**
     * 查询资助项目信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(projectInfoService.list());
    }

    /**
     * 新增资助项目信息
     *
     * @param projectInfo 资助项目信息
     * @return 结果
     */
    @PostMapping
    public R save(ProjectInfo projectInfo) {
        projectInfo.setPublishDate(DateUtil.formatDateTime(new Date()));
        return R.ok(projectInfoService.save(projectInfo));
    }

    /**
     * 修改资助项目信息
     *
     * @param projectInfo 资助项目信息
     * @return 结果
     */
    @PutMapping
    public R edit(ProjectInfo projectInfo) {
        return R.ok(projectInfoService.updateById(projectInfo));
    }

    /**
     * 删除资助项目信息
     *
     * @param ids ids
     * @return 资助项目信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(projectInfoService.removeByIds(ids));
    }
}
