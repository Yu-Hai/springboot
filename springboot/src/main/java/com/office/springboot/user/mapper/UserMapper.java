package com.office.springboot.user.mapper;

import com.office.springboot.user.dto.UserDTO;

public interface UserMapper {
	
	UserDTO getUserInfo(UserDTO user);
	
	void insertUserWithBackId(UserDTO user);
	
}
