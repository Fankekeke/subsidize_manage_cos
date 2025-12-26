package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ApplicationInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.IApplicationInfoService;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 资助申请记录表 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/application-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationInfoController {

    private final IApplicationInfoService applicationInfoService;

    private final IStudentInfoService studentInfoService;

    /**
     * 分页获取资助申请记录信息
     *
     * @param page            分页对象
     * @param applicationInfo 资助申请记录信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ApplicationInfo> page, ApplicationInfo applicationInfo) {
        return R.ok(applicationInfoService.queryApplicationPage(page, applicationInfo));
    }

    /**
     * 查询资助申请记录信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(applicationInfoService.getById(id));
    }

    /**
     * 查询资助申请记录信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(applicationInfoService.list());
    }

    /**
     * 新增资助申请记录信息
     *
     * @param applicationInfo 资助申请记录信息
     * @return 结果
     */
    @PostMapping
    public R save(ApplicationInfo applicationInfo) {
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, applicationInfo.getUserId()));
        if (studentInfo != null) {
            applicationInfo.setUserId(studentInfo.getId());
        }
        applicationInfo.setApplicationData(DateUtil.formatDateTime(new Date()));
        return R.ok(applicationInfoService.save(applicationInfo));
    }

    /**
     * 修改资助申请记录信息
     *
     * @param applicationInfo 资助申请记录信息
     * @return 结果
     */
    @PutMapping
    public R edit(ApplicationInfo applicationInfo) {
        return R.ok(applicationInfoService.updateById(applicationInfo));
    }

    /**
     * 删除资助申请记录信息
     *
     * @param ids ids
     * @return 资助申请记录信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(applicationInfoService.removeByIds(ids));
    }
}
