package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.ApplicationInfoMapper;
import cc.mrbird.febs.cos.entity.ApplicationInfo;
import cc.mrbird.febs.cos.entity.FundDisbursement;
import cc.mrbird.febs.cos.entity.ProjectInfo;
import cc.mrbird.febs.cos.entity.ReviewRecordInfo;
import cc.mrbird.febs.cos.service.IApplicationInfoService;
import cc.mrbird.febs.cos.service.IFundDisbursementService;
import cc.mrbird.febs.cos.service.IReviewRecordInfoService;
import cc.mrbird.febs.cos.service.IProjectInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * 资助申请记录表 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationInfoServiceImpl extends ServiceImpl<ApplicationInfoMapper, ApplicationInfo> implements IApplicationInfoService {

    private final IReviewRecordInfoService reviewRecordInfoService;

    private final IFundDisbursementService fundDisbursementService;

    private final IProjectInfoService projectInfoService;

    /**
     * 分页获取资助申请记录信息
     *
     * @param page            分页对象
     * @param applicationInfo 资助申请记录信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryApplicationPage(Page<ApplicationInfo> page, ApplicationInfo applicationInfo) {
        return baseMapper.queryApplicationPage(page, applicationInfo);
    }

    /**
     * 导师审核
     *
     * @param applicationInfo 资助申请记录信息
     * @return 结果
     */
    @Override
    public Boolean staffAudit(ApplicationInfo applicationInfo) {
        applicationInfo.setFirstAuditDate(DateUtil.formatDateTime(new Date()));

        ReviewRecordInfo reviewRecordInfo = new ReviewRecordInfo();
        reviewRecordInfo.setApplicationId(applicationInfo.getId());
        reviewRecordInfo.setReviewerId(applicationInfo.getStaffId());
        reviewRecordInfo.setReviewLevel("导师");
        reviewRecordInfo.setReviewTime(DateUtil.formatDateTime(new Date()));
        reviewRecordInfo.setReviewResult("已驳回".equals(applicationInfo.getStatus()) ? "驳回" : "通过");
        reviewRecordInfo.setReviewComment(applicationInfo.getFirstAuditContent());
        reviewRecordInfoService.save(reviewRecordInfo);
        return updateById(applicationInfo);
    }

    /**
     * 管理员审核
     *
     * @param applicationInfo 资助申请记录信息
     * @return 结果
     */
    @Override
    public Boolean adminAudit(ApplicationInfo applicationInfo) {
        applicationInfo.setAdminAuditDate(DateUtil.formatDateTime(new Date()));

        ReviewRecordInfo reviewRecordInfo = new ReviewRecordInfo();
        reviewRecordInfo.setApplicationId(applicationInfo.getId());
        reviewRecordInfo.setReviewerId(0);
        reviewRecordInfo.setReviewLevel("管理员");
        reviewRecordInfo.setReviewTime(DateUtil.formatDateTime(new Date()));
        reviewRecordInfo.setReviewResult("已驳回".equals(applicationInfo.getStatus()) ? "驳回" : "通过");
        reviewRecordInfo.setReviewComment(applicationInfo.getAdminAuditDate());
        reviewRecordInfoService.save(reviewRecordInfo);

        if (!"已驳回".equals(applicationInfo.getStatus())) {
            FundDisbursement fundDisbursement = new FundDisbursement();
            fundDisbursement.setApplicationId(applicationInfo.getId());
            ProjectInfo projectInfo = projectInfoService.getById(applicationInfo.getProjectId());
            fundDisbursement.setDisburseAmount(projectInfo.getAmountPerPerson());
            fundDisbursement.setDisburseDate(DateUtil.formatDateTime(new Date()));
            fundDisbursementService.save(fundDisbursement);
        }
        return updateById(applicationInfo);
    }
}
