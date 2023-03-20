package com.kh.qp.qp.controller;

import com.kh.qp.qp.dto.AnswerDto;
import com.kh.qp.qp.dto.ReceiveParam;
import com.kh.qp.qp.pojo.Answer;
import com.kh.qp.qp.service.AnswerService;
import com.kh.qp.qp.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Resource
    private AnswerService answerService;


    @PostMapping("/save")
    public ResultVo saveAnswer(@RequestBody(required = false) Answer answer){
        answerService.saveOrUpdate(answer);
        return ResultVo.ok("提交成功！");
    }

    @PostMapping("/deleteAll")
    public ResultVo deleteAnswer(@RequestBody ReceiveParam receiveParam){
        answerService.removeBatchByIds(receiveParam.getIds());
        return  ResultVo.ok("批量删除成功！");
    }

    @PostMapping("/list")
    public ResultVo pageAnswer(@RequestBody(required = false) AnswerDto answerDto){
        return answerService.listAnswerPage(answerDto);
    }


    @GetMapping("/find/{answerId}")
    public ResultVo queryAnswer(@PathVariable(value = "answerId") Integer answerId){
        return answerService.findById(answerId);
    }
}
