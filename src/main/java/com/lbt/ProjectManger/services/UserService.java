package com.lbt.ProjectManger.services;

import com.lbt.ProjectManger.domain.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
	UserEntity save(UserEntity userEntity);

	Optional<UserEntity> findOne(Long id);
}