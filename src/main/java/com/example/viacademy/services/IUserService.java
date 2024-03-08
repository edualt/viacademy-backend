package com.example.viacademy.services;

import com.example.viacademy.entities.User;
import com.example.viacademy.web.requests.CreateUserRequest;
import com.example.viacademy.web.responses.BaseResponse;

public interface IUserService {
    BaseResponse get(Long id);
    BaseResponse create(CreateUserRequest request);

//    TODO: Add update and delete methods

    User findOneAndEnsureExist(Long id);
    User findOneAndEnsureExist(String email);
}
