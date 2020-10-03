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
@TableName("t_apt")
@ApiModel(value="Apt对象", description="")
public class Apt implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "员工id")
    private Integer empid;

    @ApiModelProperty(value = "用户id")
    private Integer userid;

    @ApiModelProperty(value = "预约房间id")
    private Integer roomid;

    @ApiModelProperty(value = "渠道id")
    private Integer sourceid;

    @ApiModelProperty(value = "支付类型id")
    private Integer paytypeid;

    @ApiModelProperty(value = "预约人数")
    private Integer personnum;

    @ApiModelProperty(value = "预约开始时间")
    private Date starttime;

    @ApiModelProperty(value = "预约结束时间")
    private Date endtime;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "创建预约时间")
    private Date createtime;

    @ApiModelProperty(value = "预约状态：0已预约、1已到场、2已放弃")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String comment;


}
