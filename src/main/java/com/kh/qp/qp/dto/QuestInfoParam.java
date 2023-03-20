package com.kh.qp.qp.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QuestInfoParam implements Serializable {

    List<String> ids;
}
