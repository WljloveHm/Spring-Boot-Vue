package com.kh.qp.qp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kh.qp.qp.dto.QuestDto;
import com.kh.qp.qp.pojo.Question;
import com.kh.qp.qp.vo.ResultVo;

public interface QuestService extends IService<Question>{
    ResultVo findById(Integer questId);

   ResultVo updateQuest(Question question);
    ResultVo deleteById(Integer questId);

    ResultVo listQuestPage(QuestDto questDto);

    ResultVo saveQuest(Question question);

}
