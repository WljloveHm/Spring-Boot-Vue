package com.kh.qp.qp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kh.qp.qp.dto.LoginDTO;
import com.kh.qp.qp.dto.UserDto;
import com.kh.qp.qp.pojo.User;
import com.kh.qp.qp.vo.ResultVo;

public interface UserService  extends IService<User> {

    ResultVo login(LoginDTO loginDTO);

    ResultVo register(User user);

    ResultVo isCode(String phone,int code);

    ResultVo findByIdI(Integer userId);

    ResultVo updateUser(User user);

    ResultVo saveUser(User user);

    ResultVo listUserPage(UserDto userDto);

    ResultVo deleteById(Integer userId);

}
