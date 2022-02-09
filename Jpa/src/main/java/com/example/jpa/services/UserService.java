package com.example.jpa.services;

import com.example.jpa.dbModel.User;
import com.example.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }


    public User findById(final Long id) {

        //put business here......


        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new RuntimeException("No User found");
        }

        return user.get();
    }

    public List<User> findAll() {

        //put business here......

        return userRepository.findAll();
    }


    public void save(User user) {

        //put business here......

        userRepository.save(user);


    }


    public void saveWithException(User user) {

        //put business here......

        userRepository.save(user);

        throw new RuntimeException("Failed to save user: " + user.getUserName());

    }

    public void deleteById(final Long id) {

        //put business here......


        userRepository.deleteById(id);

    }

}
