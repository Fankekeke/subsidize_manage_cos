package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ApplicationInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 资助申请记录表 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface ApplicationInfoMapper extends BaseMapper<ApplicationInfo> {

    /**
     * 分页获取资助申请记录信息
     *
     * @param page            分页对象
     * @param applicationInfo 资助申请记录信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryApplicationPage(Page<ApplicationInfo> page, @Param("queryParam") ApplicationInfo applicationInfo);

    /**
     * 分页获取资助申请记录信息
     *
     * @param ids 申请ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryApplicationByIds(@Param("ids") List<Integer> ids);

    Integer selectDataByMonth(@Param("year") Integer year, @Param("month") Integer month);

    Integer selectAlertByMonth(@Param("year") Integer year, @Param("month") Integer month);

    List<LinkedHashMap<String, Object>> selectDataNumWithinDays();

    List<LinkedHashMap<String, Object>> selectAlertNumWithinDays();
}
