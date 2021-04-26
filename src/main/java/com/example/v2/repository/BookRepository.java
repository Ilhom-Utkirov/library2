package com.example.v2.repository;

import com.example.v2.model.Book;
import com.example.v2.model.Ownership;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.From;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    //@Query("UPDATE Book b SET b.id = :id")

    @Query("SELECT b FROM Book b WHERE b.id = :id")
    Book findByLongId(@Param("id") Long id);




    @Query(value = "SELECT count(*) FROM book", nativeQuery = true)
    int totalBooks();


    @Query(value = "SELECT * FROM book LIMIT ?,?", nativeQuery = true)
    List<Book> findBooksWithPage(int start, int length);
}