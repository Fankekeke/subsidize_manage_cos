package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.BankAccountId;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.IBankAccountIdService;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生银行账户表 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/bank-account-id")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BankAccountIdController {

    private final IBankAccountIdService bankAccountIdService;

    private final IStudentInfoService studentInfoService;

    /**
     * 分页获取学生银行账户信息
     *
     * @param page          分页对象
     * @param bankAccountId 学生银行账户信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<BankAccountId> page, BankAccountId bankAccountId) {
        return R.ok(bankAccountIdService.queryBankAccountPage(page, bankAccountId));
    }

    /**
     * 查询学生银行账户信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(bankAccountIdService.getById(id));
    }

    /**
     * 查询学生银行账户信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(bankAccountIdService.list());
    }

    /**
     * 新增学生银行账户信息
     *
     * @param bankAccountId 学生银行账户信息
     * @return 结果
     */
    @PostMapping
    public R save(BankAccountId bankAccountId) {
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, bankAccountId.getUserId()));
        if (studentInfo != null) {
            bankAccountId.setUserId(studentInfo.getId());
        }
        return R.ok(bankAccountIdService.save(bankAccountId));
    }

    /**
     * 修改学生银行账户信息
     *
     * @param bankAccountId 学生银行账户信息
     * @return 结果
     */
    @PutMapping
    public R edit(BankAccountId bankAccountId) {
        return R.ok(bankAccountIdService.updateById(bankAccountId));
    }

    /**
     * 删除学生银行账户信息
     *
     * @param ids ids
     * @return 学生银行账户信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(bankAccountIdService.removeByIds(ids));
    }
}
