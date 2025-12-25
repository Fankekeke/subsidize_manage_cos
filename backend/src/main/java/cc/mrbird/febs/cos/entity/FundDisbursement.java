package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 资金发放记录表
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FundDisbursement implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 对应申请ID
     */
    private Integer applicationId;

    /**
     * 实际发放金额
     */
    private BigDecimal disburseAmount;

    /**
     * 资金发放日期 (资金发放)
     */
    private String disburseDate;

    /**
     * 经办人ID (管理员)
     */
    private Long handlerId;

    /**
     * 发放状态
     */
    private String status;


}
