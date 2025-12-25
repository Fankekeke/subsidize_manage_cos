package cc.mrbird.febs.cos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 资助评审记录表
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ReviewRecordInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 申请ID
     */
    private Integer applicationId;

    /**
     * 评审人ID (导师/管理员)
     */
    private Integer reviewerId;

    /**
     * 评审级别
     */
    private String reviewLevel;

    /**
     * 评审时间
     */
    private String reviewTime;

    /**
     * 评审结果
     */
    private String reviewResult;

    /**
     * 评审意见 (资助初审/资助评审记录)
     */
    private String reviewComment;


}
