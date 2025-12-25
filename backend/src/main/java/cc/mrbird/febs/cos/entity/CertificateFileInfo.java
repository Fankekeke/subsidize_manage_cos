package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 学生证书表
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CertificateFileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生用户ID
     */
    private Integer userId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件类型 (如: 获奖证书, 成绩单, 贫困证明)
     */
    private String fileType;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 上传时间
     */
    private String uploadTime;


}
