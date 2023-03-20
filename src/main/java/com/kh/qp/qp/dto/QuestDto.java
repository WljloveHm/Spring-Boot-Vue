package com.kh.qp.qp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestDto implements Serializable {
    private int pageIndex=1;
    private int pageSize=5;

    private String title=null;


}
