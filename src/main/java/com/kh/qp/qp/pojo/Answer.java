package com.kh.qp.qp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "answer")
public class Answer implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "question_id")
    private Integer questionId;
    @TableField(value = "source_type")
    private Integer sourceType;

    @TableField(value = "source_ip")
    private String sourceIp;

    @TableField(value = "creator")
    private String creator;

    @TableField(value = "answer")
    private String answer;

}
