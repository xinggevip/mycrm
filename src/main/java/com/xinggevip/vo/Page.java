package com.xinggevip.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xinggevip
 * @since 2020-10-03 13:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Page对象", description="")
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "第几页")
    private Integer pageNum;

    @ApiModelProperty(value = "每页多少条")
    private Integer pageSize;

    @ApiModelProperty(value = "姓名")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String phonenumber;

    @ApiModelProperty(value = "关键字")
    private String keyword;

    @ApiModelProperty(value = "预约状态")
    private Integer status;

    @ApiModelProperty(value = "开始时间")
    private Date starttime;

    @ApiModelProperty(value = "结束时间")
    private Date endtime;

    @ApiModelProperty(value = "房间id")
    private Integer roomid;



}
