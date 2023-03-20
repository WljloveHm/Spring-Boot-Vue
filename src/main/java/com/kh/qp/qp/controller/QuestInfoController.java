package com.kh.qp.qp.controller;

import com.kh.qp.qp.dto.QuestInfoDto;
import com.kh.qp.qp.dto.ReceiveParam;
import com.kh.qp.qp.pojo.QuestionInfo;
import com.kh.qp.qp.service.QuestInfoService;
import com.kh.qp.qp.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/questInfo")
public class QuestInfoController {

    @Resource
    private QuestInfoService questInfoService;


    @PostMapping("/saveOrUpdate")
    public ResultVo saveOrupdateQI(@RequestBody(required = false) QuestionInfo questionInfo) {
        questInfoService.saveOrUpdate(questionInfo);
        return ResultVo.ok("创建成功！");
    }

    @PostMapping("/deleteAll")
    public ResultVo deleteQI(@RequestBody ReceiveParam receiveParam) {
        questInfoService.removeBatchByIds(receiveParam.getIds());
        return ResultVo.ok("批量删除成功！");
    }

    @PostMapping("/list")
    public ResultVo pageQI(@RequestBody(required = false) QuestInfoDto questInfoDto) {
        return questInfoService.listQuestInfoPage(questInfoDto);
    }


    @GetMapping("/find/{questInfoId}")
    public ResultVo queryQI(@PathVariable(value = "questInfoId") Integer questInfoId) {
        return questInfoService.findById(questInfoId);
    }

}

