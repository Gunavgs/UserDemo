package com.example.User.service;

import com.example.User.dao.UserDao;
import com.example.User.entities.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserDao userDao;

    @Test
    void createUser() {
        User user = new User(1,"Guna","Chennai");
        when(userDao.save(user)).thenReturn(user);
        User user1=userService.createUser(user);
        assertNotNull(user1);
        verify(userDao).save(user);

    }

    @Test
    void getAllUsers() {
        List<User> userList=new ArrayList<User>();
//        User user1=new User(1,"Guna","Chennai");
//        User user2=new User(2,"Sekar","Red hills");
//        userList.add(user1);
//        userList.add(user2);
        when(userDao.findAll()).thenReturn(userList);
        List<User> userList1=userService.getAllUsers();

        assertEquals(userList,userList1);


    }

    @Test
    void getUserById() {
        User user = new User(1,"Guna","Chennai");

        when(userDao.findById(1)).thenReturn(Optional.of(user));
        User user1= userService.getUserById(1);
       assertNotNull(user1);
        assertThat(user1).isNotNull();
    }

    @Test
    void testUpdateUser() {
        User user = new User(1,"Guna","Chennai");
        when(userDao.findById(1)).thenReturn(Optional.of(user));
        when(userDao.save(user)).thenReturn(user);
        user.setLocation("Red hills");
        User user1=userService.updateUser(user);
        assertThat(user1.getLocation()).isEqualTo("Red hills");
        verify(userDao).save(user);

    }

    @Test
    void deleteUserById() {
        User user = new User(1,"Guna","Chennai");
        userDao.deleteById(1);
        verify(userDao).deleteById(1);


    }
}