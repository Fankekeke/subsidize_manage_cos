package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.InsuranceInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.IInsuranceInfoService;
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
 * 参保情况 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/insurance-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InsuranceInfoController {

    private final IInsuranceInfoService insuranceInfoService;

    private final IStudentInfoService studentInfoService;

    /**
     * 分页获取学生参保情况信息
     *
     * @param page          分页对象
     * @param insuranceInfo 学生参保情况信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<InsuranceInfo> page, InsuranceInfo insuranceInfo) {
        return R.ok(insuranceInfoService.queryInsuranceInfoPage(page, insuranceInfo));
    }

    /**
     * 查询学生参保情况信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(insuranceInfoService.getById(id));
    }

    /**
     * 查询学生参保情况信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(insuranceInfoService.list());
    }

    /**
     * 新增学生参保情况信息
     *
     * @param insuranceInfo 学生参保情况信息
     * @return 结果
     */
    @PostMapping
    public R save(InsuranceInfo insuranceInfo) {
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, insuranceInfo.getUserId()));
        if (studentInfo != null) {
            insuranceInfo.setUserId(studentInfo.getId());
        }
        insuranceInfo.setUpdateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(insuranceInfoService.save(insuranceInfo));
    }

    /**
     * 修改学生参保情况信息
     *
     * @param insuranceInfo 学生参保情况信息
     * @return 结果
     */
    @PutMapping
    public R edit(InsuranceInfo insuranceInfo) {
        insuranceInfo.setUpdateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(insuranceInfoService.updateById(insuranceInfo));
    }

    /**
     * 删除学生参保情况信息
     *
     * @param ids ids
     * @return 学生参保情况信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(insuranceInfoService.removeByIds(ids));
    }
}
