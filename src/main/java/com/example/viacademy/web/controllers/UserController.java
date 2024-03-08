package com.example.viacademy.web.controllers;

import com.example.viacademy.services.IUserService;
import com.example.viacademy.web.requests.CreateUserRequest;
import com.example.viacademy.web.responses.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final IUserService service;

    public UserController(IUserService userService) {
        this.service = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id) {
        BaseResponse response = service.get(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping()
    public ResponseEntity<BaseResponse> create(@RequestBody CreateUserRequest request) {
        BaseResponse response = service.create(request);
        return response.apply();
    }

}
