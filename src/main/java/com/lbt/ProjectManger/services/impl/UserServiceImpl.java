package com.lbt.ProjectManger.services.impl;

import com.lbt.ProjectManger.domain.entities.UserEntity;
import com.lbt.ProjectManger.repositories.UserRepository;
import com.lbt.ProjectManger.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserEntity save(UserEntity userEntity) {
		return  userRepository.save(userEntity);
	}

	@Override
	public Optional<UserEntity> findOne(Long id) {
		return userRepository.findById(id);
	}
}
