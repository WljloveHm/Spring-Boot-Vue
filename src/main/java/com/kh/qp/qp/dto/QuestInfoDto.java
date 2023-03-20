package com.kh.qp.qp.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
public class QuestInfoDto implements Serializable {
    private int pageIndex=1;
    private int pageSize=5;

    private String title=null;

//    private String creator=null;

    private Integer iDelete=0;


//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @ApiModelProperty("开始时间")
//    @TableField(exist = false)
//    private Date startTime;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @ApiModelProperty("截止时间")
//    @TableField(exist = false)
//    private Date endTime;

}
