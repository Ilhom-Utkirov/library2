package com.example.v2.repository;

import com.example.v2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    void deleteById(Long id);

    @Query(value="SELECT * FROM USERS LIMIT ?,?", nativeQuery = true)
    List<User> findUserWithPage(int start, int length);

    @Query(value="SELECT count(*) FROM USERS", nativeQuery = true)
    int totalUsers();
}