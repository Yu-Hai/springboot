package com.office.springboot.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.springboot.common.form.BaseObjectForm;
import com.office.springboot.user.dto.UserDTO;
import com.office.springboot.user.service.UserService;

@RestController
public class UserController {

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
		UserDTO result = userService.getUserInfo(user);
		result.setSuccessForm("操作成功！");
		return result;
	}

	@RequestMapping("/app/user/insertUser")
	public BaseObjectForm insertUser(@ModelAttribute UserDTO user) {
		BaseObjectForm result = new BaseObjectForm();
		String userId = userService.insertUserWithBackId(user);
		result.setData(userId);
		result.setSuccessForm("新增成功！");
		return result;
	}

}
