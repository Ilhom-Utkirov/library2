package com.example.v2.service;


import com.example.v2.model.Book;
import com.example.v2.model.Ownership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OwnershipService {

    void save(Ownership ownership);


    List<Ownership> findAll();



    void delete(long id);

    Ownership findById(Long id);


    List<Book> findAllByUserIdAndStatusTrue(Long id);


    List<Ownership> findAllByStatus(boolean b);

    void deleteUserBook(Long userId, Long bookId);

    boolean getStatusByUserIdAndBookid(long id, Long id1);

    Page<Ownership> findPaginated(Pageable pageable);
}
