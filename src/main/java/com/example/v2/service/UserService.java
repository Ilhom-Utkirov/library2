package com.example.v2.service;


import com.example.v2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    //List<Book> getAllUsersBooks(Long id);
    User findById(Long id);
    void save(User user);


    void delete(User user);


    Page<User> findPaginated(Pageable pageable);

    int totalUsers();

    List<User> findUserWithPage(Integer start, Integer length);
}
