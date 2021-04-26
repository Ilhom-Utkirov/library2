package com.example.v2.model;


import javax.persistence.*;

@Entity
@Table(name = "ownership")
public class Ownership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ownership_id")
    private Long id;

    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "date_from")
    private int dateFrom;
    @Column(name = "date_to")
    private int dateTo;
    @Column(name = "status")
    private boolean status;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(int dateFrom) {
        this.dateFrom = dateFrom;
    }

    public int getDateTo() {
        return dateTo;
    }

    public void setDateTo(int dateTo) {
        this.dateTo = dateTo;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}