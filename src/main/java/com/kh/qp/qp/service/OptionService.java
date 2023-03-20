package com.kh.qp.qp.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.kh.qp.qp.dto.OptionDto;
import com.kh.qp.qp.pojo.Option;

import com.kh.qp.qp.vo.ResultVo;

public interface OptionService extends IService<Option> {

    ResultVo findById(Integer optionId);

    ResultVo updateOptionIfo(Option option);

    ResultVo deleteById(Integer optionId);

    ResultVo listOptionPage(OptionDto optionDto);

    ResultVo saveOption(Option option);

}
