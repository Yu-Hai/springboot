package com.office.springboot.user.service;

import com.office.springboot.user.dto.UserDTO;

/**
 * 用户模块 service interface
 * 
 * @author Neo 2017-5-12
 */
public interface UserService {

	UserDTO getUserInfo(UserDTO user);

	String insertUserWithBackId(UserDTO user);
}
