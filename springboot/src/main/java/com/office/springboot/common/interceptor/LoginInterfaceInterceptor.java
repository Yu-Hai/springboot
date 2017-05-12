package com.office.springboot.common.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.office.springboot.common.client.ResponseHandler;
import com.office.springboot.common.constant.ResponseStatusCode;
import com.office.springboot.common.session.SessionAttributeNames;
import com.office.springboot.common.util.PropertiesUtil;
import com.office.springboot.user.dto.UserDTO;

/**
 * 登录认证的拦截器
 * 
 * @author Neo 2017-5-12
 *
 */
public class LoginInterfaceInterceptor extends HandlerInterceptorAdapter implements InitializingBean {
	private static Logger logger = LoggerFactory.getLogger(LoginInterfaceInterceptor.class);

	/**
	 * Handler执行完成之后调用这个方法
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exc)
			throws Exception {
	}

	/**
	 * Handler执行之后，ModelAndView返回之前调用这个方法
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		logger.trace("请求url：" + url);
		HttpSession session = request.getSession();
		logger.trace("SessionAttributeNames.CURRENT_USER:"
				+ String.valueOf((session.getAttribute(SessionAttributeNames.CURRENT_USER) == null)));
		if (url.contains("/do/") || url.endsWith(".do")) {
			if (needLogin(request)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("returnFlag", ResponseStatusCode.SESSION_TIME_OUT);
				map.put("returnMsg", "未登录或者登录超时");
				ResponseHandler.writeContent(map, response);
				return false;
			}
		}

		return super.preHandle(request, response, handler);
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 判断是否需要登录
	 * 
	 * @param request
	 * @return
	 */
	public boolean needLogin(HttpServletRequest request) {
		String isNeedLogin = PropertiesUtil.getProperty("isNeedLogin");
		UserDTO userDTO = (UserDTO) request.getSession().getAttribute(SessionAttributeNames.CURRENT_USER);
		logger.trace("SessionAttributeNames.CURRENT_USER:"
				+ String.valueOf(request.getSession().getAttribute(SessionAttributeNames.CURRENT_USER)));
		if ("true".equals(isNeedLogin)) {
			if (userDTO == null) {
				return true;
			}
			if (StringUtils.isNotBlank(userDTO.getIdUser())) {
				return false;
			}
		}
		return true;
	}

}
