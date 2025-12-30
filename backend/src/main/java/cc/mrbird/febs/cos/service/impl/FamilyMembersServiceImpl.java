package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.FamilyMembers;
import cc.mrbird.febs.cos.dao.FamilyMembersMapper;
import cc.mrbird.febs.cos.service.IFamilyMembersService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 家庭成员 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class FamilyMembersServiceImpl extends ServiceImpl<FamilyMembersMapper, FamilyMembers> implements IFamilyMembersService {

    /**
     * 分页获取学生家庭成员信息
     *
     * @param page          分页对象
     * @param familyMembers 学生家庭成员信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryFamilyMembersPage(Page<FamilyMembers> page, FamilyMembers familyMembers) {
        return baseMapper.queryFamilyMembersPage(page, familyMembers);
    }
}
