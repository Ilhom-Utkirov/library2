package com.example.v2.controller;


import com.example.v2.model.UsersDTO;
import com.example.v2.repository.UserRepository;
import com.example.v2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/userRestController")
public class StudentRestController {

    @Autowired
    private UserRepository userRepository;



    @GetMapping("/getUsers")
    public UsersDTO getUserPage(@RequestParam( value = "start", required = false) int start,
                                @RequestParam( value = "length", required = false) int length,
                                @RequestParam( value = "draw", required = false) int draw,
                                @RequestParam( value = "order[0][column]", required = false) int sortColIndex,
                                @RequestParam( value = "order[0][dir]", required = false) String order,
                                @RequestParam( value = "columns[0][data]", required = false) String col0DataAttrName   ){
        // To handle Sortingvalue =
        // sortColIndex => which column index is being sorted
        // order => asc or desc
        // col0DataAttrName => can be used to pass column name dynamically in DBQuery



        //Integer totalUsers = userService.totalNumberUsers();
        int totalUsers = userRepository.totalUsers();
        UsersDTO usersDto = new UsersDTO();
        usersDto.setData(userRepository.findUserWithPage(start, length));
        usersDto.setRecordsFiltered(totalUsers);
        usersDto.setRecordsTotal(totalUsers); // Needed to show Pagination in Datatable
        usersDto.setDraw(draw);
        return usersDto;
    }

}
