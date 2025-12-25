package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ReviewRecordInfo;
import cc.mrbird.febs.cos.service.IReviewRecordInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资助评审记录表 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/review-record-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReviewRecordInfoController {

    private final IReviewRecordInfoService reviewRecordInfoService;

    /**
     * 分页获取资助项目信息
     *
     * @param page             分页对象
     * @param reviewRecordInfo 资助项目信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ReviewRecordInfo> page, ReviewRecordInfo reviewRecordInfo) {
        return R.ok(reviewRecordInfoService.queryReviewPage(page, reviewRecordInfo));
    }

    /**
     * 查询资助项目信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(reviewRecordInfoService.getById(id));
    }

    /**
     * 查询资助项目信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(reviewRecordInfoService.list());
    }

    /**
     * 新增资助项目信息
     *
     * @param reviewRecordInfo 资助项目信息
     * @return 结果
     */
    @PostMapping
    public R save(ReviewRecordInfo reviewRecordInfo) {
        return R.ok(reviewRecordInfoService.save(reviewRecordInfo));
    }

    /**
     * 修改资助项目信息
     *
     * @param reviewRecordInfo 资助项目信息
     * @return 结果
     */
    @PutMapping
    public R edit(ReviewRecordInfo reviewRecordInfo) {
        return R.ok(reviewRecordInfoService.updateById(reviewRecordInfo));
    }

    /**
     * 删除资助项目信息
     *
     * @param ids ids
     * @return 资助项目信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(reviewRecordInfoService.removeByIds(ids));
    }
}
