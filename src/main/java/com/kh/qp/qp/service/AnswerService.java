package com.kh.qp.qp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kh.qp.qp.dto.AnswerDto;
import com.kh.qp.qp.pojo.Answer;
import com.kh.qp.qp.vo.ResultVo;

public interface AnswerService extends IService<Answer> {

    ResultVo saveAnswer(Answer answer);

    ResultVo deleteAnswer(Integer answerId);

    ResultVo listAnswerPage(AnswerDto answerDto);

    ResultVo findById(Integer answerId);
}
