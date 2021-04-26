package com.example.v2.service;

import com.example.v2.model.Book;
import com.example.v2.model.Ownership;
import com.example.v2.repository.OwnershipRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OwnershipServiceImpl implements OwnershipService {

    /*each time user gets book new object is created*/
    private final OwnershipRepository ownershipRepository;
    private final BookService bookService;
    public OwnershipServiceImpl(OwnershipRepository ownershipRepository, BookService bookService) {
        this.ownershipRepository = ownershipRepository;
        this.bookService = bookService;
    }

    @Override
    public void save(Ownership ownership) {
        ownershipRepository.save(ownership);
    }

    @Override
    public List<Ownership> findAll() {
        return ownershipRepository.findAll();
    }

    @Override
    public Ownership findById(Long id) {

        Optional<Ownership> optionalOwnership = ownershipRepository.findById(id);

        if(!optionalOwnership.isPresent()){
            throw new RuntimeException("ownership not found not Found");
        }

        return optionalOwnership.get();
    }

    @Override
    public List<Book> findAllByUserIdAndStatusTrue(Long userId) {
        List<Ownership> ownerships=ownershipRepository.findAllByUserIdAndStatus(userId,true);

        List<Book> books = new ArrayList<>();

        for (Ownership o:ownerships ) {
            books.add(bookService.findById(o.getBookId()));

        }
        return books;
    }

    @Override
    public List<Ownership> findAllByStatus(boolean b) {
        List<Ownership> ownerships=ownershipRepository.findAllByStatusBoolean(b);

        return ownerships;
    }


    @Override
    public void deleteUserBook(Long userId, Long bookId) {

       Ownership ownership = ownershipRepository.findByUserIdAndBookId(userId,bookId);
       ownership.setStatus(false);
       ownershipRepository.save(ownership);
    }

    @Override
    public boolean getStatusByUserIdAndBookid(long userId, Long bookId) {
        return ownershipRepository.findByUserIdAndBookId(userId,bookId).getStatus();
    }


    @Override
    public Page<Ownership> findPaginated(Pageable pageable) {

        List<Ownership> ownerships = ownershipRepository.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Ownership> list;

        //5 tali 2 pageda obshiy soni 6 ta boganda
        if(ownerships.size() < startItem){
            list = Collections.emptyList();
        }else{
            int toIndex = Math.min(startItem + pageSize, ownerships.size());
            list = ownerships.subList(startItem, toIndex);
        }

        Page<Ownership> ownershipPage =
                new PageImpl<>(list, PageRequest.of(currentPage, pageSize), ownerships.size());

        return ownershipPage;
    }

    @Override
    public void delete(long id) {
        ownershipRepository.deleteById(id);
    }


}
