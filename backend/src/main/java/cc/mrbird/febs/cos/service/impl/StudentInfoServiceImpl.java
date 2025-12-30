package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.*;
import cc.mrbird.febs.cos.entity.*;
import cc.mrbird.febs.cos.service.IBulletinInfoService;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 学生信息 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements IStudentInfoService {

    private final IBulletinInfoService bulletinInfoService;

    private final CertificateFileInfoMapper certificateFileInfoMapper;

    private final FinancialStatusInfoMapper financialStatusInfoMapper;

    private final InsuranceInfoMapper insuranceInfoMapper;

    private final FamilyMembersMapper familyMembersMapper;

    /**
     * 分页获取学生信息
     *
     * @param page        分页对象
     * @param studentInfo 学生信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryStudentPage(Page<StudentInfo> page, StudentInfo studentInfo) {
        return baseMapper.queryStudentPage(page, studentInfo);
    }

    /**
     * 查询学生信息详情
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> queryStudentDetail(Integer userId) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        StudentInfo studentInfo = this.getById(userId);
        // 学生信息
        LinkedHashMap<String, Object> userInfo = baseMapper.queryStudentDetail(studentInfo.getUserId());
        // 学生证书
        List<CertificateFileInfo> certificateFileInfoList = certificateFileInfoMapper.selectList(Wrappers.<CertificateFileInfo>lambdaQuery().eq(CertificateFileInfo::getUserId, userId));
        // 家庭情况
        List<FinancialStatusInfo> financialStatusInfoList = financialStatusInfoMapper.selectList(Wrappers.<FinancialStatusInfo>lambdaQuery().eq(FinancialStatusInfo::getUserId, userId));
        // 保险情况
        InsuranceInfo insuranceInfo = insuranceInfoMapper.selectOne(Wrappers.<InsuranceInfo>lambdaQuery().eq(InsuranceInfo::getUserId, userId));
        // 家庭成员
        List<FamilyMembers> familyMembersList = familyMembersMapper.selectList(Wrappers.<FamilyMembers>lambdaQuery().eq(FamilyMembers::getUserId, userId));
        result.put("family", familyMembersList);
        result.put("insurance", insuranceInfo);
        result.put("financial", financialStatusInfoList);
        result.put("certificate", certificateFileInfoList);
        result.put("user", userInfo);
        return result;
    }

    /**
     * 查询用户信息详情【公告信息】
     *
     * @param userId 主键ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> selectBulletinDetail(Integer userId) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("user", null);
                put("bulletin", Collections.emptyList());
            }
        };
        StudentInfo userInfo = this.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, userId));
        if (userInfo == null) {
            return result;
        }
        result.put("user", userInfo);

        // 公告信息
        List<BulletinInfo> bulletinInfoList = bulletinInfoService.list(Wrappers.<BulletinInfo>lambdaQuery().eq(BulletinInfo::getRackUp, "1"));
        result.put("bulletin", bulletinInfoList);
        return result;
    }
}
