package com.llm.demo.util;
import com.llm.demo.IOrderService;
import com.llm.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
@Component
public class UserDetailsImpl implements UserDetailsService{

	@Reference
	private IOrderService service;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		User se = service.login(name);
		System.out.println(se);
		if(se!=null) {
			return se;
		}
		return null;
	}

}
