package com.office.springboot.user.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.office.springboot.common.constant.CommonConstant.Prefix;
import com.office.springboot.common.constant.CommonConstant.TableSeqName;
import com.office.springboot.common.mapper.CommonMapper;
import com.office.springboot.common.util.DateFormatUtils;
import com.office.springboot.user.dto.UserDTO;
import com.office.springboot.user.mapper.UserMapper;
import com.office.springboot.user.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private CommonMapper commonMapper;

	@Override
	public UserDTO getUserInfo(UserDTO user) {
		return userMapper.getUserInfo(user);

	}

	@Override
	public String insertUserWithBackId(UserDTO user) {
		Integer seq = commonMapper.getSeqByName(TableSeqName.T_USER_SEQ);
		StringBuffer idUser = new StringBuffer();
		idUser.append(Prefix.USER_ID)
			  .append(DateFormatUtils.formatDate(new Date(), DateFormatUtils.FORMAT_YYYYMMDDHHMMSS))
			  .append(String.format("%08d", seq));
		user.setIdUser(idUser.toString());
		logger.trace("新增用户：" + user);
		userMapper.insertUserWithBackId(user);
		logger.trace("新增用户成功");
		return idUser.toString();
	}

}
