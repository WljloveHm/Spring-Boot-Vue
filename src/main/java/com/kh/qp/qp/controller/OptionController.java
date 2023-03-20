package com.kh.qp.qp.controller;

import com.kh.qp.qp.dto.OptionDto;
import com.kh.qp.qp.dto.ReceiveParam;
import com.kh.qp.qp.pojo.Option;
import com.kh.qp.qp.service.OptionService;
import com.kh.qp.qp.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/option")
public class OptionController {
    @Resource
    private OptionService optionService;


    @PostMapping("/saveOrUpdate")
    public ResultVo saveOrupdateOption(@RequestBody(required = false) Option option){
        optionService.saveOrUpdate(option);
        return ResultVo.ok("创建成功！");
    }

    @PostMapping("/deleteAll")
    public ResultVo deleteOption(@RequestBody ReceiveParam receiveParam){
        optionService.removeBatchByIds(receiveParam.getIds());
        return  ResultVo.ok("批量删除成功！");
    }

    @PostMapping("/list")
    public ResultVo pageOption(@RequestBody(required = false) OptionDto optionDto){
        return optionService.listOptionPage(optionDto);
    }


    @GetMapping("/find/{optionId}")
    public ResultVo queryOption(@PathVariable(value = "optionId") Integer optionId){
        return optionService.findById(optionId);
    }

}

