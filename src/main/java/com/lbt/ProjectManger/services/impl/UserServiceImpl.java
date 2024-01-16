package com.lbt.ProjectManger.services.impl;

import com.lbt.ProjectManger.domain.entities.UserEntity;
import com.lbt.ProjectManger.repositories.UserRepository;
import com.lbt.ProjectManger.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Page<UserEntity> findMany(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public boolean isExist(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public UserEntity update(Long id, UserEntity userEntity) {
		userEntity.setId(id);
		return userRepository.save(userEntity);
	}

	@Override
	public UserEntity partialUpdate(Long id, UserEntity userEntity) {
		userEntity.setId(id);
		return userRepository.findById(id).map(existUser -> {
			Optional.ofNullable(userEntity.getPassword()).ifPresent(existUser::setPassword);
			Optional.ofNullable(userEntity.getEmail()).ifPresent(existUser::setEmail);
			Optional.ofNullable(userEntity.getRole()).ifPresent(existUser::setRole);
			return userRepository.save(existUser);
		}).orElseThrow(() -> new RuntimeException("Not found USER"));
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
