package com.example.User.service;

import com.example.User.dao.UserDao;
import com.example.User.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;



    public User createUser(User user)
    {
        return userDao.save(user);
    }

    public List<User> getAllUsers()
    {
        return userDao.findAll();
    }

    public User getUserById(int id)
    {
        return userDao.findById(id).get();
    }

    public User updateUser(User user)
    {
        User existingUser = getUserById(user.getUserId());

        existingUser.setName(user.getName());
        existingUser.setLocation((user.getLocation()));
        return userDao.save(existingUser);

    }

    public User deleteUserById(int id) {

        userDao.deleteById(id);

        return  null ;
    }
}
