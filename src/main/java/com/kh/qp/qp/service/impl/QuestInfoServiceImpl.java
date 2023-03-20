package com.kh.qp.qp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kh.qp.qp.dto.QuestInfoDto;
import com.kh.qp.qp.mapper.QuestInfoMapper;
import com.kh.qp.qp.pojo.QuestionInfo;
import com.kh.qp.qp.service.QuestInfoService;
import com.kh.qp.qp.vo.ResultVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiremock.org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
@Service
@Transactional(rollbackFor = Exception.class)
public class QuestInfoServiceImpl extends ServiceImpl<QuestInfoMapper, QuestionInfo> implements QuestInfoService {


    @Resource
    private QuestInfoMapper questInfoMapper;
    @Override
    public ResultVo findById(Integer questInfoId) {
        QuestionInfo questionInfo = questInfoMapper.selectById(questInfoId);
        return ResultVo.ok(questionInfo);
    }

    @Override
    public ResultVo updateQuestIfo(QuestionInfo questionInfo) {
        int i = questInfoMapper.updateById(questionInfo);
        if (i>0){
            return ResultVo.ok("问卷信息修改成功！");
        }
        return ResultVo.fail("问卷信息修改失败！");
    }


    @Override
    public ResultVo deleteById(Integer questInfoId) {
        int i = questInfoMapper.deleteById(questInfoId);
        if (i>0){
            return ResultVo.ok("问卷信息删除成功！");
        }
        return ResultVo.fail("问卷信息删除失败！");
    }

    @Override
    public ResultVo listQuestInfoPage(QuestInfoDto questInfoDto) {
        LambdaQueryWrapper<QuestionInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .like(StringUtils.isNotBlank(questInfoDto.getTitle()),QuestionInfo::getTitle,questInfoDto.getTitle())
                .eq(questInfoDto.getIDelete()==0,QuestionInfo::getIDelete,questInfoDto.getIDelete());
        Page<QuestionInfo> page = new Page<>(questInfoDto.getPageIndex(),questInfoDto.getPageSize());
        Page<QuestionInfo> questionInfoPage = questInfoMapper.selectPage(page, lambdaQueryWrapper);
        return ResultVo.ok(questionInfoPage.getRecords(),questionInfoPage.getTotal());
    }

    @Override
    public ResultVo saveQuestInfo(QuestionInfo questionInfo) {
        LambdaQueryWrapper<QuestionInfo> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(QuestionInfo::getTitle, questionInfo.getTitle());

        QuestionInfo questionInfoEntity= questInfoMapper.selectOne(queryWrapper);
        if (questionInfoEntity != null) {
            return ResultVo.fail("此问卷已存在");
        }
        int insert = questInfoMapper.insert(questionInfo);
        if (insert>0){
            return ResultVo.ok("问卷信息保存成功！");
        }
        return ResultVo.fail("问卷信息保存失败！");
    }
}
