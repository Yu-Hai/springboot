package com.office.springboot.user.mapper;

import com.office.springboot.user.dto.UserDTO;

/**
 * 用户模块 sql mapper
 * @author Neo
 *
 */
public interface UserMapper {

	/**
	 * 通过用户信息查询用户
	 * @param user
	 * @return
	 */
	UserDTO getUserInfo(UserDTO user);

	/**
	 * 插入用户信息
	 * @param user
	 */
	void insertUser(UserDTO user);

}
