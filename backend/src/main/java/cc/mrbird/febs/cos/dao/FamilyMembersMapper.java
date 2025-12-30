package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.FamilyMembers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.LinkedHashMap;

/**
 * 家庭成员 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface FamilyMembersMapper extends BaseMapper<FamilyMembers> {

    /**
     * 分页获取学生家庭成员信息
     *
     * @param page          分页对象
     * @param familyMembers 学生家庭成员信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryFamilyMembersPage(Page<FamilyMembers> page, FamilyMembers familyMembers);
}
