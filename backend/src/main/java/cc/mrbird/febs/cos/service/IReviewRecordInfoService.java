package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ReviewRecordInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.LinkedHashMap;

/**
 * 资助评审记录表 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IReviewRecordInfoService extends IService<ReviewRecordInfo> {

    /**
     * 分页获取资助项目信息
     *
     * @param page        分页对象
     * @param reviewRecordInfo 资助项目信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryReviewPage(Page<ReviewRecordInfo> page, ReviewRecordInfo reviewRecordInfo);
}
