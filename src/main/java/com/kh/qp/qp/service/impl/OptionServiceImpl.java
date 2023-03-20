package com.kh.qp.qp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kh.qp.qp.dto.OptionDto;
import com.kh.qp.qp.mapper.OptionMapper;
import com.kh.qp.qp.pojo.Option;
import com.kh.qp.qp.pojo.QuestionInfo;
import com.kh.qp.qp.service.OptionService;
import com.kh.qp.qp.vo.ResultVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class OptionServiceImpl extends ServiceImpl<OptionMapper,Option> implements OptionService {
    @Resource
    private OptionMapper optionMapper;
    @Override
    public ResultVo findById(Integer optionId) {
        Option option = optionMapper.selectById(optionId);
        return ResultVo.ok(option);
    }

    @Override
    public ResultVo updateOptionIfo(Option option) {
        optionMapper.updateById(option);
        return ResultVo.ok("更新成功！");
    }

    @Override
    public ResultVo deleteById(Integer optionId) {
          optionMapper.deleteById(optionId);
        return ResultVo.ok("删除成功！");
    }

    @Override
    public ResultVo listOptionPage(OptionDto optionDto) {
        QueryWrapper<Option> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(optionDto.getQuestionId()>0,"question_id",optionDto.getQuestionId())
                .eq(optionDto.getQuestionInfoId()>0,"question_info_id",optionDto.getQuestionInfoId());

        Page<Option> page = new Page<>(optionDto.getPageIndex(),optionDto.getPageSize());
        Page<Option> optionPage = optionMapper.selectPage(page,queryWrapper);
        return ResultVo.ok(optionPage.getRecords(),optionPage.getTotal());
    }

    @Override
    public ResultVo saveOption(Option option) {
        int insert = optionMapper.insert(option);
        return ResultVo.ok("添加成功！");
    }
}
