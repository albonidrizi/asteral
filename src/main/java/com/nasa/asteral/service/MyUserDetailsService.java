package com.nasa.asteral.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nasa.asteral.model.MyUserDetails;
import com.nasa.asteral.model.db.MyUser;
import com.nasa.asteral.repository.MyUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
	
	private final MyUserRepository myUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser myUser = myUserRepository.findUserByUsernameIgnoreCase(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found."));
		
		return new MyUserDetails(myUser);
	}

}
