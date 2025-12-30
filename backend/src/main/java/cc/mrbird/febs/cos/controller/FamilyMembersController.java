package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.FamilyMembers;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.IFamilyMembersService;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 家庭成员 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/family-members")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FamilyMembersController {

    private final IFamilyMembersService familyMembersService;

    private final IStudentInfoService studentInfoService;

    /**
     * 分页获取学生家庭成员信息
     *
     * @param page          分页对象
     * @param familyMembers 学生家庭成员信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<FamilyMembers> page, FamilyMembers familyMembers) {
        return R.ok(familyMembersService.queryFamilyMembersPage(page, familyMembers));
    }

    /**
     * 查询学生家庭成员信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(familyMembersService.getById(id));
    }

    /**
     * 查询学生家庭成员信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(familyMembersService.list());
    }

    /**
     * 新增学生家庭成员信息
     *
     * @param familyMembers 学生家庭成员信息
     * @return 结果
     */
    @PostMapping
    public R save(FamilyMembers familyMembers) {
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, familyMembers.getUserId()));
        if (studentInfo != null) {
            familyMembers.setUserId(studentInfo.getId());
        }
        return R.ok(familyMembersService.save(familyMembers));
    }

    /**
     * 修改学生家庭成员信息
     *
     * @param familyMembers 学生家庭成员信息
     * @return 结果
     */
    @PutMapping
    public R edit(FamilyMembers familyMembers) {
        return R.ok(familyMembersService.updateById(familyMembers));
    }

    /**
     * 删除学生家庭成员信息
     *
     * @param ids ids
     * @return 学生家庭成员信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(familyMembersService.removeByIds(ids));
    }
}
