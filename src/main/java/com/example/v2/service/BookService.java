package com.example.v2.service;


import com.example.v2.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Book findById(Long id);



    List<Book> findAllByUserId(Long id);

    void save(Book book);

    List<Book> findAll();


    void delete(Book book);

    public Page<Book> findPaginated(Pageable pageable);



    /*void delete(Long id);*/

    /*void deleteList(List<Book> books);*/
}
