package com.example.v2.model;

import javax.persistence.*;

@Entity
public class UserBooksDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //book variables
    @Column(name = "book_id")
    public Long bookId;
    @Column(name = "name")
    public String name;
    @Column(name = "author")
    public String author;
    @Column(name = "isbn")
    public String iSBN;
    @Column(name = "description")
    public String description;
    @Column(name = "number")
    public int number;
    @Column(name = "book_status_ownership")
    public boolean bookStatusFromOwnership;

    //user variables
    @Column(name = "user_id")
    public Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getiSBN() {
        return iSBN;
    }

    public void setiSBN(String iSBN) {
        this.iSBN = iSBN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isBookStatusFromOwnership() {
        return bookStatusFromOwnership;
    }

    public void setBookStatusFromOwnership(boolean bookStatusFromOwnership) {
        this.bookStatusFromOwnership = bookStatusFromOwnership;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}