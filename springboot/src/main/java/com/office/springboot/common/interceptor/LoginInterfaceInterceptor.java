package com.office.springboot.common.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.office.springboot.common.client.ResponseHandler;
import com.office.springboot.common.constant.ResponseStatusCode;
import com.office.springboot.common.service.RedisService;
import com.office.springboot.common.session.SessionAttributeNames;
import com.office.springboot.common.util.PropertiesUtil;
import com.office.springboot.common.util.SessionManagerUtils;
import com.office.springboot.user.dto.UserDTO;

/**
 * 登录认证的拦截器
 * 
 * @author Neo 2017-5-12
 *
 */
@Component
public class LoginInterfaceInterceptor extends HandlerInterceptorAdapter implements InitializingBean {
	private static Logger logger = LoggerFactory.getLogger(LoginInterfaceInterceptor.class);

	//private RedisServiceImpl redisService= (RedisServiceImpl) SpringContextConfig.getBean("redisService");
	
	@Autowired
	private RedisService redisService;
	
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
		// 从Session中获取当前用户的ID
		String currentIdUser = (String) request.getSession().getAttribute(SessionAttributeNames.CURRENT_ID_USER);
		logger.trace("SessionAttributeNames.CURRENT_ID_USER:" + currentIdUser);
		if ("true".equals(isNeedLogin)) {
			if (currentIdUser == null) {
				return true;
			}
			if (StringUtils.isNotBlank(currentIdUser)) {
				// 通过用户的ID组装Redis中存储用户信息的Key
				String key = SessionAttributeNames.CURRENT_USER + ":" + currentIdUser;
				// 从Redis中获取当前用户的信息
				UserDTO currentUser = (UserDTO) redisService.get(key);
				// 判断Redis是否超时
				if (StringUtils.isBlank(currentUser.getIdUser())) {
					return true;
				}
				// 将获取到的用户信息存入Session中
				SessionManagerUtils.addOrUpdateSessionInfo(request, SessionAttributeNames.CURRENT_USER, currentUser);
				return false;
			}
		}
		return true;
	}

}
