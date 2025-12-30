package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.ApplicationInfoMapper;
import cc.mrbird.febs.cos.entity.ApplicationInfo;
import cc.mrbird.febs.cos.entity.BulletinInfo;
import cc.mrbird.febs.cos.entity.FundDisbursement;
import cc.mrbird.febs.cos.entity.ProjectInfo;
import cc.mrbird.febs.cos.entity.ReviewRecordInfo;
import cc.mrbird.febs.cos.service.*;
import cc.mrbird.febs.cos.service.IProjectInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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

    private final IStaffInfoService staffInfoService;

    private final IStudentInfoService studentInfoService;

    private final IBulletinInfoService bulletinInfoService;

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

    /**
     * 获取首页数据
     *
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> homeData() {

        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("staffNum", 0);
                put("studentNum", 0);
                put("majorNum", 0);
                put("classNum", 0);
            }
        };

        result.put("staffNum", staffInfoService.count());
        result.put("studentNum", studentInfoService.count());
        result.put("majorNum", projectInfoService.count());
        result.put("classNum", this.count());

        int year = DateUtil.thisYear();
        int month = DateUtil.thisMonth() + 1;
        // 本月申请数量
        result.put("monthNum", baseMapper.selectDataByMonth(year, month));
        // 本月审核通过数量
        result.put("monthAlertNum", baseMapper.selectAlertByMonth(year, month));

        // 本年申请数量
        result.put("yearNum", baseMapper.selectDataByMonth(year, null));
        // 本年审核通过数量
        result.put("yearAlertNum", baseMapper.selectAlertByMonth(year, null));

        // 近十天申请统计
        result.put("numDayList", baseMapper.selectDataNumWithinDays());
        // 近十天审核通过统计
        result.put("alertDayList", baseMapper.selectAlertNumWithinDays());
        // 公告信息
        result.put("bulletin", bulletinInfoService.list(Wrappers.<BulletinInfo>lambdaQuery().eq(BulletinInfo::getRackUp, 1)));

        return result;
    }
}
