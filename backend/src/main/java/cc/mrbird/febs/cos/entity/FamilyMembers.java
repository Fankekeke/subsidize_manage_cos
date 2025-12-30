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
 * 家庭成员
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FamilyMembers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属学生
     */
    private Integer userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 与本人关系
     */
    private String relation;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 月收入
     */
    private BigDecimal income;


}
