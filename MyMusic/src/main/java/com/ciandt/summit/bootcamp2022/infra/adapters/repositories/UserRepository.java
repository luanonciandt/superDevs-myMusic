package com.ciandt.summit.bootcamp2022.infra.adapters.repositories;

import com.ciandt.summit.bootcamp2022.domain.User;
import com.ciandt.summit.bootcamp2022.domain.ports.repositories.UserRepositoryPort;
import com.ciandt.summit.bootcamp2022.exceptions.InvalidParameterException;
import com.ciandt.summit.bootcamp2022.infra.adapters.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRepository implements UserRepositoryPort {

    private final SpringUserRepository springUserRepository;

    public UserRepository(SpringUserRepository springUserRepository) {
        this.springUserRepository = springUserRepository;
    }

    public User getUserById(String id){
        if(!springUserRepository.existsById(id)){
            throw new InvalidParameterException("Usuário inexistente");
        }

        UserEntity userEntity = springUserRepository.getUserById(id);

        return userEntity.toUser();
    }

}
