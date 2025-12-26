package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.CertificateFileInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.ICertificateFileInfoService;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生证书表 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/certificate-file-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CertificateFileInfoController {

    private final ICertificateFileInfoService certificateFileInfoService;

    private final IStudentInfoService studentInfoService;

    /**
     * 分页获取学生证书信息
     *
     * @param page                分页对象
     * @param certificateFileInfo 学生证书信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<CertificateFileInfo> page, CertificateFileInfo certificateFileInfo) {
        return R.ok(certificateFileInfoService.queryCertificatePage(page, certificateFileInfo));
    }

    /**
     * 查询学生证书信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(certificateFileInfoService.getById(id));
    }

    /**
     * 查询学生证书信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(certificateFileInfoService.list());
    }

    /**
     * 新增学生证书信息
     *
     * @param certificateFileInfo 学生证书信息
     * @return 结果
     */
    @PostMapping
    public R save(CertificateFileInfo certificateFileInfo) {
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, certificateFileInfo.getUserId()));
        if (studentInfo != null) {
            certificateFileInfo.setUserId(studentInfo.getId());
        }
        return R.ok(certificateFileInfoService.save(certificateFileInfo));
    }

    /**
     * 修改学生证书信息
     *
     * @param certificateFileInfo 学生证书信息
     * @return 结果
     */
    @PutMapping
    public R edit(CertificateFileInfo certificateFileInfo) {
        return R.ok(certificateFileInfoService.updateById(certificateFileInfo));
    }

    /**
     * 删除学生证书信息
     *
     * @param ids ids
     * @return 学生证书信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(certificateFileInfoService.removeByIds(ids));
    }
}
