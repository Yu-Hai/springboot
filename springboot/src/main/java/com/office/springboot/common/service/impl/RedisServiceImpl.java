package com.office.springboot.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.springboot.common.exception.BusinessException;
import com.office.springboot.common.service.RedisService;
import com.office.springboot.common.util.RedisUtils;

/**
 * Redis 服务实现类
 * 
 * @author Administrator
 *
 */
@Service
public class RedisServiceImpl implements RedisService {
	

	@Autowired
	private RedisUtils redisUtils;

	@Override
	public void remove(String... keys) throws BusinessException {
		redisUtils.remove(keys);
	}

	@Override
	public void removePattern(String pattern) throws BusinessException {
		redisUtils.removePattern(pattern);
	}

	@Override
	public void remove(String key) throws BusinessException {
		redisUtils.remove(key);
	}

	@Override
	public boolean exists(String key) throws BusinessException {
		return redisUtils.exists(key);
	}

	@Override
	public Object get(String key) throws BusinessException {
		return redisUtils.get(key);
	}

	@Override
	public boolean set(String key, Object value) throws BusinessException {
		return redisUtils.set(key, value);
	}

	@Override
	public boolean set(String key, Object value, Long expireTime) throws BusinessException {
		return redisUtils.set(key, value, expireTime);
	}

}
