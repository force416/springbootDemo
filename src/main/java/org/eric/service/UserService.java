package org.eric.service;

import org.eric.Repository.UserRepository;
import org.eric.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("userService")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public User addUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        User dbUser = userOptional.orElse(null);

        boolean exists = dbUser != null ? true : false;

        if (!exists) {
            return userRepository.save(user);
        }

        return userOptional.get();
    }
}
