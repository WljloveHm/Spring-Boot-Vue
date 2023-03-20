package com.kh.qp.qp.controller;

import com.kh.qp.qp.dto.LoginDTO;
import com.kh.qp.qp.dto.UserDto;
import com.kh.qp.qp.dto.ReceiveParam;
import com.kh.qp.qp.pojo.User;
import com.kh.qp.qp.service.UserService;
import com.kh.qp.qp.util.SmsUtil;
import com.kh.qp.qp.vo.ResultVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private UserService userService;
    @Resource
    private SmsUtil smsUtil;

    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginDTO loginForm){
        return  userService.login(loginForm);
    }

    @PostMapping("/register")
    public ResultVo register(@RequestBody User user){
        return  userService.register(user);
    }


    @GetMapping("/find/{userId}")
    public ResultVo findById(@PathVariable(value = "userId") Integer userId){
        return userService.findByIdI(userId);
    }


    @PutMapping("/update")
    public ResultVo updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }


    @PostMapping("/deleteAll") // {ids: [1,2,3]}
    public ResultVo deleteUser(@RequestBody ReceiveParam receiveParam) {
        userService.removeBatchByIds(receiveParam.getIds());
        return ResultVo.ok("批量删除成功！");
    }


    @PostMapping("/addOrUpdate")
    public ResultVo saveUser(@RequestBody User user) {
        if(user.getId() == null || user.getId() == 0) {
            return userService.saveUser(user);
        }
        else {
            return userService.updateUser(user);
        }
    }

    @PostMapping ("/list")
    public ResultVo userListPage(@RequestBody(required = false) UserDto userDto){
        return userService.listUserPage(userDto);
    }

    @GetMapping("/send")
    public ResultVo sendSms(String mobile) throws ExecutionException, InterruptedException {
        int code =(int)((Math.random()*9+1)*10000);
        redisTemplate.opsForValue().set(mobile, code + "", 30, TimeUnit.MINUTES);
        return ResultVo.ok(smsUtil.sendSms(mobile,code).getBody());
    }

    @GetMapping("/isCode")
    public ResultVo isCode(String phone, Integer code) {
         return userService.isCode(phone,code);
    }
}
