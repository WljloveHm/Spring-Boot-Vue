package com.kh.qp.qp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kh.qp.qp.dto.AnswerDto;
import com.kh.qp.qp.mapper.AnswerMapper;
import com.kh.qp.qp.pojo.Answer;
import com.kh.qp.qp.service.AnswerService;
import com.kh.qp.qp.vo.ResultVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@Service
@Transactional(rollbackFor = Exception.class)
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper,Answer> implements AnswerService {

    @Resource
    private AnswerMapper answerMapper;
    @Override
    public ResultVo saveAnswer(Answer answer) {
        answerMapper.insert(answer);
        return ResultVo.ok("提交成功！");
    }

    @Override
    public ResultVo deleteAnswer(Integer answerId) {
        answerMapper.deleteById(answerId);
        return ResultVo.ok("删除成功！！");
    }

    @Override
    public ResultVo listAnswerPage(AnswerDto answerDto) {
        QueryWrapper<Answer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(answerDto.getQuestionId()>0,"question_id",answerDto.getQuestionId())
                .eq(answerDto.getSourceType()>0,"source_type",answerDto.getSourceType());

        Page<Answer> page = new Page<>(answerDto.getPageIndex(),answerDto.getPageSize());
        Page<Answer> answerPage = answerMapper.selectPage(page,queryWrapper);
        return ResultVo.ok(answerPage.getRecords(),answerPage.getTotal());

    }

    @Override
    public ResultVo findById(Integer answerId) {
        Answer answer = answerMapper.selectById(answerId);
        return ResultVo.ok(answer);
    }
}
