package com.kh.qp.qp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private int pageIndex=1;
    private int pageSize=5;

    private String username=null;

    private String phone=null;

    private Integer status=-1;
}
