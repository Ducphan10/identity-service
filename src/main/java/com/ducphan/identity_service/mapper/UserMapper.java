package com.ducphan.identity_service.mapper;

import com.ducphan.identity_service.dto.request.UserCreationRequest;
import com.ducphan.identity_service.dto.request.UserUpdateRequest;
import com.ducphan.identity_service.dto.response.UserResponse;
import com.ducphan.identity_service.entity.User;
import jakarta.persistence.Column;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
