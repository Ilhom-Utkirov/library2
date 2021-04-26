package com.example.v2.service;


import com.example.v2.model.Book;
import com.example.v2.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findById(Long id) {
        /*
        Optional<Book> bookOptional= bookRepository.findById(id);

        if(!bookOptional.isPresent()){
            throw new RuntimeException("Recipe not Found");
        }
        return bookOptional.get();*/

        return bookRepository.findByLongId(id);
    }


    /*this can be deleted it is done through ownership serveice*/
    @Override
    public List<Book> findAllByUserId(Long id) {

      // return bookRepository.findAllByUserId(id);
        return new ArrayList<>(); //TODO
    }

    @Override
    public void save(Book book) {

        bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }


    /*for pagination*/
    public Page<Book> findPaginated(Pageable pageable){

        List<Book> books = bookRepository.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage= pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Book> list;

        if(books.size() < startItem){
            list = Collections.emptyList();
        }else {
           ///*To prevent pagination being bigger than the list of books retrieved*/
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), books.size());
    }


}