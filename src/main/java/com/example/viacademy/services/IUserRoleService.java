package com.example.viacademy.services;

import com.example.viacademy.entities.Role;

import java.util.List;

public interface IUserRoleService {
    List<Role> findAllRoles(Long userId);
}
