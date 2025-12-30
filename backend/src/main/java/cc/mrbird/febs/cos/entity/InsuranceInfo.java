package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 参保情况
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InsuranceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属学生
     */
    private Integer userId;

    /**
     * 参保情况（0.否 1.是）
     */
    private String enrollmentStatus;

    /**
     * 医疗费用总额
     */
    private BigDecimal totalMedicalExpenses;

    /**
     * 实际报销金额
     */
    private BigDecimal actualAmount;

    /**
     * 更新时间
     */
    private String updateDate;


}
