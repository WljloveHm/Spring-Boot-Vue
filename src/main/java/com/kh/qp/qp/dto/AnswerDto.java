package com.kh.qp.qp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerDto implements Serializable {
    private int pageIndex = 1;
    private int pageSize = 5;

    private int questionId = -1;
    private int sourceType = -1;

}
