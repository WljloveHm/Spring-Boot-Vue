package com.kh.qp.qp.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ReceiveParam implements Serializable {

    List<String> ids;
}
