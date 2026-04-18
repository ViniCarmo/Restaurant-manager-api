package dev.vinicius.restaurant_management_api.service;

import dev.vinicius.restaurant_management_api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

        public void deleteUserById(Integer id){
        userRepository.deleteById(id);
        }


}
