package com.kh.qp.qp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kh.qp.qp.dto.LoginDTO;
import com.kh.qp.qp.dto.UserDto;
import com.kh.qp.qp.mapper.UserMapper;
import com.kh.qp.qp.pojo.User;
import com.kh.qp.qp.service.UserService;
import com.kh.qp.qp.util.MD5Util;
import com.kh.qp.qp.util.SmsUtil;
import com.kh.qp.qp.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiremock.org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private UserMapper userMapper;

    @Resource
    private SmsUtil smsUtil;



    @Override
    public ResultVo register(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getUsername, user.getUsername());

        User userEntity = userMapper.selectOne(queryWrapper);
        if (userEntity != null) {
            return ResultVo.fail("此用户名已注册");
        }
        queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getPhone, user.getPhone());
        userEntity = userMapper.selectOne(queryWrapper);
        if (userEntity != null) {
            return ResultVo.fail("此手机号码已注册");
        }
        String saltMD5 = MD5Util.getSaltMD5(user.getPassword());
        user.setPassword(saltMD5);
        user.setStatus(1);
        userMapper.insert(user);
        return ResultVo.ok("账号注册成功");

    }

    //验证验证码
    @Override
    public ResultVo isCode(String phone,int code) {
        Integer redisYZM = Integer.valueOf(redisTemplate.opsForValue().get(phone));
        Boolean isCoded =  code==redisYZM;
        if (isCoded){
            return ResultVo.ok("验证成功！");
        }
        return ResultVo.err("验证码不正确！");
    }

    @Override
    public ResultVo findByIdI(Integer userId) {
        User user = userMapper.selectById(userId);
        return ResultVo.ok(user);
    }

    @Override
    public ResultVo updateUser(User user) {
        int i = userMapper.updateById(user);
        if (i>0){
            return ResultVo.ok("用户信息修改成功！");
        }
        return ResultVo.fail("用户信息修改失败！");
    }

    @Override
    public ResultVo saveUser(User user) {
        int insert = userMapper.insert(user);
        if (insert>0){
            return ResultVo.ok("用户信息添加成功！");
        }
        return ResultVo.fail("用户信息添加失败！");
    }

    @Override
    public ResultVo listUserPage(UserDto userDto) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(StringUtils.isNotBlank(userDto.getUsername()),"username",userDto.getUsername());
//        queryWrapper.like(StringUtils.isNotBlank(userDto.getPhone()),"phone",userDto.getPhone());
//        queryWrapper.eq(userDto.getStatus()!=null && userDto.getStatus() != -1,
//                "status",userDto.getStatus());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .like(StringUtils.isNotBlank(userDto.getUsername()),User::getUsername,userDto.getUsername())
                .like(StringUtils.isNotBlank(userDto.getPhone()),User::getPhone,userDto.getPhone())
        .eq(userDto.getStatus() != -1,User::getStatus,userDto.getStatus());
        Page<User> page = new Page<>(userDto.getPageIndex(),userDto.getPageSize());
        Page<User> userPage= userMapper.selectPage(page,lambdaQueryWrapper);
        return ResultVo.ok(userPage.getRecords(), userPage.getTotal());
    }

    @Override
    public ResultVo deleteById(Integer userId) {
        int i = userMapper.deleteById(userId);
        if (i>0){
            return ResultVo.ok("用户信息删除成功！");
        }
        return ResultVo.fail("用户信息删除失败！");

    }
    @Override
    public ResultVo login(LoginDTO loginDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",loginDTO.getUsername());
        queryWrapper.or(user -> user.eq("phone",loginDTO.getUsername()));
        User user = userMapper.selectOne(queryWrapper);
        if(user == null) {
            return ResultVo.fail("此账号不存在");
        }
        else {
            boolean isValid = MD5Util.getSaltverifyMD5(loginDTO.getPassword(), user.getPassword());
            if(! isValid){
                return ResultVo.fail("密码输入错误");
            }
            else if(user.getStatus() == 0) {
                return ResultVo.fail("此账号已被停用，无法登录，如需登录请与系统管理员联系");
            }
            else {
                Map<String,Object> jsonMap = new HashMap<>();
                jsonMap.put("username",user.getUsername());
                jsonMap.put("roleId","1");
                jsonMap.put("role","admin");
                jsonMap.put("permissions", Arrays.asList("*.*.*"));
                return ResultVo.ok(jsonMap);
            }
        }
    }
}
