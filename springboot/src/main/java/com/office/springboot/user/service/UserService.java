package com.office.springboot.user.service;

import com.office.springboot.user.dto.UserDTO;

public interface UserService {

	UserDTO getUserInfo(UserDTO user);
	
	String insertUserWithBackId(UserDTO user);
}
