package cc.mrbird.febs.cos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 学生经济情况表
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FinancialStatusInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生用户ID
     */
    private Long userId;

    /**
     * 是否认定为困难生 (1是, 0否)
     */
    private Boolean isHardship;

    /**
     * 家庭年收入
     */
    private BigDecimal familyIncome;

    /**
     * 家庭人口数
     */
    private Integer familyMembers;

    /**
     * 家庭经济情况证明文件路径
     */
    private String proofFilePath;

    /**
     * 最后更新时间
     */
    private String lastUpdateTime;


}
