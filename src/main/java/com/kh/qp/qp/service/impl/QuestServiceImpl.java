package com.kh.qp.qp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kh.qp.qp.dto.QuestDto;
import com.kh.qp.qp.mapper.QuestMapper;
import com.kh.qp.qp.pojo.Question;
import com.kh.qp.qp.service.QuestService;
import com.kh.qp.qp.vo.ResultVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiremock.org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class QuestServiceImpl extends ServiceImpl<QuestMapper,Question> implements QuestService {

    @Resource
    private QuestMapper questMapper;
    @Override
    public ResultVo findById(Integer questId) {
        Question question = questMapper.selectById(questId);
        return ResultVo.ok(question);
    }

    @Override
    public ResultVo updateQuest(Question question) {
        int i = questMapper.updateById(question);
        if (i>0){
            return ResultVo.ok("问题修改成功！");
        }
        return ResultVo.fail("问题修改失败！");
    }

    @Override
    public ResultVo deleteById(Integer questId) {
        int i = questMapper.deleteById(questId);
        if (i>0){
            return ResultVo.ok("问题删除成功！");
        }
        return ResultVo.fail("问题删除失败！");
    }

    @Override
    public ResultVo listQuestPage(QuestDto questDto) {
        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .like(StringUtils.isNotBlank(questDto.getTitle()),Question::getTitle,questDto.getTitle());
        Page<Question> page = new Page<>(questDto.getPageIndex(),questDto.getPageSize());
        Page<Question> questioPage = questMapper.selectPage(page, lambdaQueryWrapper);
        return ResultVo.ok(questioPage.getRecords(),questioPage.getTotal());
    }

    @Override
    public ResultVo saveQuest(Question question) {
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Question::getTitle, question.getTitle());

        Question questEntity = questMapper.selectOne(queryWrapper);
        if (questEntity != null) {
            return ResultVo.fail("此题已存在");
        }
        questMapper.insert(question);
        return ResultVo.ok("题目添加成功！");
    }
}
