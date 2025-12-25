package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.FinancialStatusInfo;
import cc.mrbird.febs.cos.service.IFinancialStatusInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生经济情况表 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/financial-status-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FinancialStatusInfoController {

    private final IFinancialStatusInfoService financialStatusInfoService;

    /**
     * 分页获取学生经济情况信息
     *
     * @param page                分页对象
     * @param financialStatusInfo 学生经济情况信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<FinancialStatusInfo> page, FinancialStatusInfo financialStatusInfo) {
        return R.ok(financialStatusInfoService.queryFinancialStatusPage(page, financialStatusInfo));
    }

    /**
     * 查询学生经济情况信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(financialStatusInfoService.getById(id));
    }

    /**
     * 查询学生经济情况信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(financialStatusInfoService.list());
    }

    /**
     * 新增学生经济情况信息
     *
     * @param financialStatusInfo 学生经济情况信息
     * @return 结果
     */
    @PostMapping
    public R save(FinancialStatusInfo financialStatusInfo) {
        return R.ok(financialStatusInfoService.save(financialStatusInfo));
    }

    /**
     * 修改学生经济情况信息
     *
     * @param financialStatusInfo 学生经济情况信息
     * @return 结果
     */
    @PutMapping
    public R edit(FinancialStatusInfo financialStatusInfo) {
        return R.ok(financialStatusInfoService.updateById(financialStatusInfo));
    }

    /**
     * 删除学生经济情况信息
     *
     * @param ids ids
     * @return 学生经济情况信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(financialStatusInfoService.removeByIds(ids));
    }
}
