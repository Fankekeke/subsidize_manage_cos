package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.ReviewRecordInfoMapper;
import cc.mrbird.febs.cos.entity.ReviewRecordInfo;
import cc.mrbird.febs.cos.service.IReviewRecordInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 资助评审记录表 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class ReviewRecordInfoServiceImpl extends ServiceImpl<ReviewRecordInfoMapper, ReviewRecordInfo> implements IReviewRecordInfoService {

    /**
     * 分页获取资助项目信息
     *
     * @param page        分页对象
     * @param reviewRecordInfo 资助项目信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryReviewPage(Page<ReviewRecordInfo> page, ReviewRecordInfo reviewRecordInfo) {
        return baseMapper.queryReviewPage(page, reviewRecordInfo);
    }
}
