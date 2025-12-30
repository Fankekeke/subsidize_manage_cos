package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ApplicationInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.LinkedHashMap;

/**
 * 资助申请记录表 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IApplicationInfoService extends IService<ApplicationInfo> {

    /**
     * 分页获取资助申请记录信息
     *
     * @param page            分页对象
     * @param applicationInfo 资助申请记录信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryApplicationPage(Page<ApplicationInfo> page, ApplicationInfo applicationInfo);

    /**
     * 导师审核
     *
     * @param applicationInfo 资助申请记录信息
     * @return 结果
     */
    Boolean staffAudit(ApplicationInfo applicationInfo);

    /**
     * 管理员审核
     *
     * @param applicationInfo 资助申请记录信息
     * @return 结果
     */
    Boolean adminAudit(ApplicationInfo applicationInfo);
}
