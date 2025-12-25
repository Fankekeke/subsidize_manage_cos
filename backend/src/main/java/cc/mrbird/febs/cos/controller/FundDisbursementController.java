package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.FundDisbursement;
import cc.mrbird.febs.cos.service.IFundDisbursementService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资金发放记录表 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/fund-disbursement")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FundDisbursementController {

    private final IFundDisbursementService fundDisbursementService;

    /**
     * 分页获取资金发放记录信息
     *
     * @param page             分页对象
     * @param fundDisbursement 资金发放记录信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<FundDisbursement> page, FundDisbursement fundDisbursement) {
        return R.ok(fundDisbursementService.selectFundDisbursementPage(page, fundDisbursement));
    }

    /**
     * 查询资金发放记录信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(fundDisbursementService.getById(id));
    }

    /**
     * 查询资金发放记录信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(fundDisbursementService.list());
    }

    /**
     * 新增资金发放记录信息
     *
     * @param fundDisbursement 资金发放记录信息
     * @return 结果
     */
    @PostMapping
    public R save(FundDisbursement fundDisbursement) {
        return R.ok(fundDisbursementService.save(fundDisbursement));
    }

    /**
     * 修改资金发放记录信息
     *
     * @param fundDisbursement 资金发放记录信息
     * @return 结果
     */
    @PutMapping
    public R edit(FundDisbursement fundDisbursement) {
        return R.ok(fundDisbursementService.updateById(fundDisbursement));
    }

    /**
     * 删除资金发放记录信息
     *
     * @param ids ids
     * @return 资金发放记录信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(fundDisbursementService.removeByIds(ids));
    }
}
