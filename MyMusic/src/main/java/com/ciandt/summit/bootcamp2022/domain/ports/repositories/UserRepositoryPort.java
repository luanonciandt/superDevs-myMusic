package com.ciandt.summit.bootcamp2022.domain.ports.repositories;

import com.ciandt.summit.bootcamp2022.domain.User;

public interface UserRepositoryPort {
    User getUserById(String id);
}
