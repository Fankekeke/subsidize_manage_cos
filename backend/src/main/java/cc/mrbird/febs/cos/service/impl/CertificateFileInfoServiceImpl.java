package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.CertificateFileInfo;
import cc.mrbird.febs.cos.mapper.CertificateFileInfoMapper;
import cc.mrbird.febs.cos.service.ICertificateFileInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 学生证书表 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class CertificateFileInfoServiceImpl extends ServiceImpl<CertificateFileInfoMapper, CertificateFileInfo> implements ICertificateFileInfoService {

    /**
     * 分页获取学生证书信息
     *
     * @param page                分页对象
     * @param certificateFileInfo 学生证书信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryCertificatePage(Page<CertificateFileInfo> page, CertificateFileInfo certificateFileInfo) {
        return baseMapper.queryCertificatePage(page, certificateFileInfo);
    }
}
