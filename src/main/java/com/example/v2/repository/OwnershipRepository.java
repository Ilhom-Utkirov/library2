package com.example.v2.repository;


import com.example.v2.model.Ownership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnershipRepository extends JpaRepository<Ownership, Long> {

    List<Ownership> findAll();


    @Query("SELECT o FROM Ownership o WHERE o.id = :id")
    Ownership findByLongId(@Param("id") Long id);

    @Query("SELECT u FROM Ownership u WHERE u.userId=:id and u.status= :status")
    List<Ownership> findAllByUserIdAndStatus(@Param("id") Long id, @Param("status") boolean status);


    // @Query(nativeQuery = true,  value ="SELECT i FROM Ownership i WHERE i.userId=:user_id and i.bookId= :book_id")
    @Query("SELECT i FROM Ownership i WHERE i.userId=:user_id and i.bookId= :book_id")
    Ownership findByUserIdAndBookId(@Param("user_id") Long user_id, @Param("book_id") Long book_id);

    @Query("SELECT j FROM  Ownership j WHERE j.status= :status  ")
    List<Ownership> findAllByStatusBoolean(@Param("status") boolean status);



    @Query(value = "SELECT * FROM ownership LIMIT ?,?", nativeQuery = true)
    List<Ownership> findOwnershipWithPage(int start, int length);

    @Query(value = "SELECT count(*) FROM ownership", nativeQuery = true)
    int totalOwnerships();

// List<Ownership> findAllByStatus(boolean status, Pageable pageable);

}