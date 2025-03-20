package com.ducphan.identity_service.service;

import com.ducphan.identity_service.dto.request.UserCreationRequest;
import com.ducphan.identity_service.dto.request.UserUpdateRequest;
import com.ducphan.identity_service.dto.response.UserResponse;
import com.ducphan.identity_service.entity.User;
import com.ducphan.identity_service.enums.Role;
import com.ducphan.identity_service.exception.AppException;
import com.ducphan.identity_service.exception.ErrorCode;
import com.ducphan.identity_service.mapper.UserMapper;
import com.ducphan.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserService {
     UserRepository userRepository;
     UserMapper userMapper;
     PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request){
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USE_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found")));
    }

    public UserResponse updateUser(String userId ,UserUpdateRequest request){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        userMapper.updateUser(user,request);
        return userMapper.toUserResponse(userRepository.save(user)) ;
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

}
