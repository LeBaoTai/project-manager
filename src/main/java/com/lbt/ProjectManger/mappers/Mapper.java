package com.lbt.ProjectManger.mappers;

import com.lbt.ProjectManger.domain.dto.UserDto;
import com.lbt.ProjectManger.domain.entities.UserEntity;

public interface Mapper<A, B>{
	B mapTo(A a);

	A mapFrom(B b);
}
