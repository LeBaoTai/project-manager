package com.lbt.ProjectManger.controllers;

import com.lbt.ProjectManger.domain.dto.UserDto;
import com.lbt.ProjectManger.domain.entities.UserEntity;
import com.lbt.ProjectManger.mappers.Mapper;
import com.lbt.ProjectManger.services.impl.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class UserController {

	private UserServiceImpl userService;
	private Mapper<UserEntity, UserDto> userMapper;

	public UserController(UserServiceImpl userService, Mapper<UserEntity, UserDto> userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		UserEntity userEntity = userMapper.mapFrom(userDto);
		UserEntity savedUserEntity = userService.save(userEntity);
		return new ResponseEntity<>(userMapper.mapTo(savedUserEntity), HttpStatus.CREATED);
	}

	@GetMapping(path = "/users/{id}")
	public ResponseEntity<UserDto> findOneUser(@PathVariable("id") Long id) {
		Optional<UserEntity> userEntity = userService.findOne(id);
		return userEntity.map(foundUserEntity -> {
			UserDto userDto = userMapper.mapTo(foundUserEntity);
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping(path = "/users")
	public Page<UserDto> listUser(Pageable pageable) {
		Page<UserEntity> listUsers = userService.findMany(pageable);
		return listUsers.map(userMapper::mapTo);
	}
}
