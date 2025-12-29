package cc.mrbird.febs.cos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 资助项目表
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目名称 (如: 国家助学金, 校级奖学金)
     */
    private String projectName;

    /**
     * 项目类型 (如: 奖学金, 助学金, 勤工助学)
     */
    private String projectType;

    /**
     * 发布者
     */
    private String publisherUser;

    /**
     * 发布日期
     */
    private String publishDate;

    /**
     * 申请开始日期
     */
    private String startDate;

    /**
     * 申请截止日期
     */
    private String endDate;

    /**
     * 面向年级范围
     */
    private String targetGrade;

    /**
     * 单人资助金额
     */
    private BigDecimal amountPerPerson;

    /**
     * 总名额
     */
    private Integer quota;

    /**
     * 项目状态 ('发布中','已截止','已暂停')
     */
    private String status;

    /**
     * 资助政策详细说明
     */
    private String policyText;

    /**
     * 申请人数
     */
    @TableField(exist = false)
    private Integer applicationNum;

    /**
     * 自审核
     */
    @TableField(exist = false)
    private String selfAudit;

}
