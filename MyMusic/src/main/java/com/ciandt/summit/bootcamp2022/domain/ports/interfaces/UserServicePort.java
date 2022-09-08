package com.ciandt.summit.bootcamp2022.domain.ports.interfaces;

import com.ciandt.summit.bootcamp2022.domain.dtos.UserDTO;

public interface UserServicePort {
    UserDTO getUserById(String id);
}
