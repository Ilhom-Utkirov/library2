package com.example.v2.service;

import com.example.v2.model.Book;
import com.example.v2.model.User;
import com.example.v2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {

        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new RuntimeException("Recipe not Found");
        }

        return userOptional.get();
    }

    /*
        //public List<Book> getAllUsersBooks(Long id) {


            //return
           // return userRepository.findById(id).get().getBookList();
        }
    */
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.deleteById(user.getId());
        //userRepository.delete(user);
    }

    @Override
    public Page<User> findPaginated(Pageable pageable) {
        List<User> users = userRepository.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage= pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<User> list;

        if(users.size() < startItem){
            list = Collections.emptyList();
        }else {
            ///*To prevent pagination being bigger than the list of books retrieved*/
            int toIndex = Math.min(startItem + pageSize, users.size());
            list = users.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), users.size());

    }

    @Override
    public int totalUsers() {
        return userRepository.totalUsers();
    }

    @Override
    public List<User> findUserWithPage(Integer start, Integer length) {
        return userRepository.findUserWithPage(start, length);
    }
}
