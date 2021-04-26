package com.example.v2.model;


import javax.persistence.*;

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    public Long id;

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


    public Book(Long id, String name, String author, String iSBN, String description, int number) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.iSBN = iSBN;
        this.description = description;
        this.number = number;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getISBN() {
        return iSBN;
    }

    public void setISBN(String iSBN) {
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
}
