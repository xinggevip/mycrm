package com.xinggevip.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_charge")
@ApiModel(value="Charge对象", description="")
public class Charge implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "员工id")
    private Integer empid;

    @ApiModelProperty(value = "用户id")
    private Integer userid;

    @ApiModelProperty(value = "预约id")
    private Integer aptid;

    @ApiModelProperty(value = "支付类型id")
    private Integer paytypeid;

    @ApiModelProperty(value = "充值金额")
    private BigDecimal moneynum;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty(value = "创建日期")
    private Date createdate;


}
