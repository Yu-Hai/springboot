package com.office.springboot.common.service;

import com.office.springboot.common.exception.BusinessException;
/**
 * Redis 服务接口
 * @author Administrator
 *
 */
public interface RedisService {

	/**
	 * 批量删除对应的value
	 * @param keys
	 * @throws BusinessException
	 */
	void remove(String... keys) throws BusinessException;
	
	/**
	 * 批量删除 key
	 * @param pattern
	 * @throws BusinessException
	 */
	void removePattern(String pattern)throws BusinessException;
	
	/**
	 * 删除对应的value
	 * @param key
	 * @throws BusinessException
	 */
	void remove(String key)throws BusinessException;
	
	/**
	 * 判断缓存中是否存在对应的key
	 * @param key
	 * @return
	 * @throws BusinessException
	 */
	boolean exists(String key)throws BusinessException;
	
	/**
	 * 读取缓存
	 * @param key
	 * @return
	 * @throws BusinessException
	 */
	Object get(String key)throws BusinessException;
	
	/**
	 * 写入缓存
	 * @return
	 * @throws BusinessException
	 */
	boolean set(String key,Object value)throws BusinessException;
	
	/**
	 * 写入缓存并设置失效时间
	 * @param key
	 * @param value
	 * @param expireTime
	 * @return
	 * @throws BusinessException
	 */
	boolean set(String key,Object value,Long expireTime)throws BusinessException;

}
