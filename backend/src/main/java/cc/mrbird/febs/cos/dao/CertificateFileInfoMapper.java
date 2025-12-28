package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.CertificateFileInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 学生证书表 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface CertificateFileInfoMapper extends BaseMapper<CertificateFileInfo> {

    /**
     * 分页获取学生证书信息
     *
     * @param page                分页对象
     * @param certificateFileInfo 学生证书信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryCertificatePage(Page<CertificateFileInfo> page, @Param("queryParam") CertificateFileInfo certificateFileInfo);
}
