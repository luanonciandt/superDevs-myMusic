package com.ciandt.summit.bootcamp2022.domain.adapters.services;

import com.ciandt.summit.bootcamp2022.domain.dtos.UserDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.UserServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repositories.UserRepositoryPort;

public class UserServiceImpl implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    public UserServiceImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserDTO getUserById(String id) {
        return userRepositoryPort.getUserById(id).toUserDTO();
    }
}
