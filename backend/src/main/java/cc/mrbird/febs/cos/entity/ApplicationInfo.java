package cc.mrbird.febs.cos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
     * 申请状态 (资助申请总览) '待初审','待复审','待终审','已通过','已驳回'
     */
    private String status;

    /**
     * 申请时填写的动态表单数据
     */
    private String applicationData;

    /**
     * 初审时间
     */
    private String firstAuditDate;

    /**
     * 初审结果
     */
    private String firstAuditStatus;

    /**
     * 初审意见
     */
    private String firstAuditContent;

    /**
     * 初审签名
     */
    private String firstAuditSign;

    /**
     * 管理员审核时间
     */
    private String adminAuditDate;

    /**
     * 管理员审核结果
     */
    private String adminAuditStatus;

    /**
     * 管理员审核意见
     */
    private String adminAuditContent;

    /**
     * 管理员审核签名
     */
    private String adminAuditSign;

    /**
     * 申请内容
     */
    private String content;

    /**
     * 银行ID
     */
    private Integer bankId;

    /**
     * 申请文件路径
     */
    private String filePath;
    private Integer staffId;

    @TableField(exist = false)
    private String studentName;

    @TableField(exist = false)
    private String projectName;

}
