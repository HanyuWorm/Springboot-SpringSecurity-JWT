package com.petronas.fetchtechexam.service;

import com.petronas.fetchtechexam.dao.UserDAO;
import com.petronas.fetchtechexam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDAO userDAO;
    public List<User> findAll() {
        return userDAO.findAll();
    }
    public User findById(final int id) {
        return userDAO.findById(id);
    }
    public void save(final User user) {
        userDAO.save(user);
    }
    public void update(final User user) {
        userDAO.update(user);
    }
    public void delete(final int id) {
        User user = userDAO.findById(id);
        if (user != null) {
            userDAO.delete(user);
        }
    }
    public boolean isUserExist(int id) {
        return findById(id)!=null;
    }
    public boolean isValidUser(User user) {
        return userDAO.isValidUser(user);
    }
    public boolean isValidUserRequire(User user) {
        return userDAO.isValidUserRequire(user);
    }
}
