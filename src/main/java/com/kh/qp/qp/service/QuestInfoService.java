package com.kh.qp.qp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kh.qp.qp.dto.QuestInfoDto;
import com.kh.qp.qp.pojo.QuestionInfo;
import com.kh.qp.qp.vo.ResultVo;

public interface QuestInfoService extends IService<QuestionInfo>{
    ResultVo findById(Integer questInfoId);

   ResultVo updateQuestIfo(QuestionInfo questionInfo);
    ResultVo deleteById(Integer questInfoId);

    ResultVo listQuestInfoPage(QuestInfoDto questInfoDto);

    ResultVo saveQuestInfo(QuestionInfo questionInfo);

}
