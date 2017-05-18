package com.office.springboot.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.springboot.common.form.BaseObjectForm;
import com.office.springboot.common.form.CommonBaseForm;
import com.office.springboot.user.dto.UserDTO;
import com.office.springboot.user.service.UserService;

/**
 * 用户模块Controller
 * 
 * @author Neo 2017-5-12
 *
 */
@RestController
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String home() {
		return "Hello world !";
	}

	@RequestMapping("/hello/{name}")
	public String index(@PathVariable String name) {
		return "Hello " + name + " !";
	}

	@RequestMapping("/app/user/getUserInfo")
	public UserDTO getUserInfo(@ModelAttribute UserDTO user) {
		logger.trace("获取用户信息【入参】：" + user.toString());
		UserDTO result = new UserDTO();
		try {
			result = userService.getUserInfo(user);
			result.setSuccessForm("操作成功！");
		} catch (Exception e) {
			result.setErrorForm();
		}
		logger.trace("获取用户信息【出参】：" + result.toString());
		return result;
	}

	@RequestMapping("/app/user/userRegister.dox")
	public BaseObjectForm insertUser(@ModelAttribute UserDTO user) {
		logger.trace("新增用户【入参】：" + user.toString());
		BaseObjectForm result = new BaseObjectForm();
		try {
			String userId = userService.insertUserWithBackId(user);
			result.setData(userId);
			result.setSuccessForm("新增成功！");
		} catch (Exception e) {
			result.setErrorForm();
		}
		logger.trace("新增用户【出参】：" + result.toString());
		return result;
	}
	
	@RequestMapping("/app/user/userLogin.dox")
	public CommonBaseForm userLogin(@ModelAttribute UserDTO user){
		logger.trace("用户登录【入参】：" + user.toString());
		CommonBaseForm result=new CommonBaseForm();
		UserDTO dbUser=userService.getUserInfo(user);
		if(dbUser.getPassword().equals(user.getPassword())){
			
		}
		
		
		logger.trace("用户登录【出参】：" + user.toString());
		return result;
		
	}
	
	
	

}
