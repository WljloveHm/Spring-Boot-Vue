package com.kh.qp.qp.controller;

import com.kh.qp.qp.dto.QuestDto;
import com.kh.qp.qp.dto.ReceiveParam;
import com.kh.qp.qp.pojo.Question;
import com.kh.qp.qp.service.QuestService;
import com.kh.qp.qp.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/quest")
public class QuestionController {

    @Resource
    private QuestService questService;


    @PostMapping("/saveOrUpdate")
    public ResultVo saveOrupdateQuestion(@RequestBody(required = false) Question question){
        questService.saveOrUpdate(question);
        return ResultVo.ok("操作成功！");
    }

    @PostMapping("/deleteAll")
    public ResultVo deleteQuest(@RequestBody ReceiveParam receiveParam){
        questService.removeBatchByIds(receiveParam.getIds());
        return ResultVo.ok("删除成功！");
    }

    @PostMapping("/list")
    public ResultVo pageQuest(@RequestBody(required = false) QuestDto questDto){
        return questService.listQuestPage(questDto);
    }


    @GetMapping("/find/{questId}")
    public ResultVo queryQuest(@PathVariable(value = "questId") Integer questId){
        return questService.findById(questId);
    }

}

