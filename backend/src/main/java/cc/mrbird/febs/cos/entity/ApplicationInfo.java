package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 资助申请记录表
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApplicationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 所申请项目ID
     */
    private Integer projectId;

    /**
     * 申请学生ID
     */
    private Integer userId;

    /**
     * 申请提交时间
     */
    private String applicationDate;

    /**
     * 申请状态 (资助申请总览)
     */
    private String status;

    /**
     * 申请时填写的动态表单数据
     */
    private String applicationData;


}
