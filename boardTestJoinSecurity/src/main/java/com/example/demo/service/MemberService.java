package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import lombok.Setter;

@Service
@Setter
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername 동작함");
		System.out.println("username:"+username);
		Optional<Member> obj = dao.findById(username);
		UserDetails user=null;
		if(obj.isPresent()) {		
			Member m=obj.get();
			System.out.println("로그인 맴버;"+m.getId() + ","+m.getPwd() + ","+m.getRole());
			user=User.builder().username(username).password(m.getPwd()).roles(m.getRole()).build();
		}else {
			System.out.println("계정없음.");
			throw new UsernameNotFoundException(username);
		}
		return user;
	}
	
	
}
