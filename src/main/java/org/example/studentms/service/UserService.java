package org.example.studentms.service;

import lombok.RequiredArgsConstructor;
import org.example.studentms.entity.User;
import org.example.studentms.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void register(User user) {
        userMapper.insertUser(user);
    }

    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void deleteUser(Integer userId) {
        userMapper.deleteUser(userId);
    }

}
