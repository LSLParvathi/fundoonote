package com.bridgelabz.fundoonotes.utilis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	private RedisTemplate<String, Long> redistemplate;

	private ValueOperations<String, Long> valueoperation;

	@Autowired
	public RedisService(RedisTemplate<String, Long> redistemplate) {
		this.redistemplate = redistemplate;
		valueoperation = redistemplate.opsForValue();

	}

	public void putToken(String token, Long id) {
		valueoperation.set(token, id);
	}
	
	public  Boolean getToken(String token)
	{
		if(valueoperation.get(token) != null) 
			return  true;
		return  false;  
	}
}
	
	 