package com.kh.qp.qp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "user")
public class User implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "username")
    private String username;
    private String password;
    private String phone;
    private Integer status;

}
