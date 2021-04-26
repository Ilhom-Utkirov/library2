package com.example.v2.controller;


import com.example.v2.model.Book;
import com.example.v2.model.User;
import com.example.v2.model.UserBooksDto;
import com.example.v2.service.BookService;
import com.example.v2.service.OwnershipService;
import com.example.v2.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.xml.ws.http.HTTPException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class StudentsController {


    private final UserService userService;
    private final OwnershipService ownershipService;
    private final BookService bookService;


    public StudentsController(UserService userService, OwnershipService ownershipService, BookService bookService) {
        this.userService = userService;
        this.ownershipService = ownershipService;
        this.bookService = bookService;
    }

    @GetMapping("/getUsers")
    public String getListUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());
        return "userFiles/userList";
    }



    /* for rest controllerto get page
    *
    * MAIN ONE
    *
    * */

    @RequestMapping("/getUsersPage")
    public String getUserPage(){

        return "userFiles/userAjaxServersidePagination";
    }


    /* Show all books Ajax version get Page*/
    @RequestMapping("/showAjax")
    public String showAllBooksPageable(Model model) {

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userFiles/userListAjax";
    }


    /*Code for Ajax version of showing the list of books*/
    @RequestMapping(value = "/ajax/userList")
    @ResponseBody
    public HashMap<String, Object> getBookListAjax(){

        List<User> userList = userService.getAllUsers();
        HashMap<String, Object> result = new HashMap<>();
        List<Object[]> convenientForJSONArray = new ArrayList<>(userList.size());
        for (User u: userList) {
            convenientForJSONArray.add(new Object[]{
                    u.getId(),
                    u.getName(),
                    u.getSurname(),
                    u.getPhoneNumber(),
                    u.getAddress()
            });
        }

        System.out.println("check book list ajax working : ok");
        result.put("data", convenientForJSONArray);
        return result;
    }

    /* For showing the list of USers Pageable enabled */
    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    public String listUsers(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size")Optional<Integer> size
    ){

        /*if null then it is first time. Set it */
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<User> userPage = userService.findPaginated(PageRequest.of(currentPage-1, pageSize));

        model.addAttribute("userPage", userPage);

        int totalPages = userPage.getTotalPages();
        if(totalPages>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "userFiles/userListPagination";
    }




    /* get SaveUSer page. Note sending new object */
    @RequestMapping(value = "/getUsers/getSaveUserPage", method = RequestMethod.GET)
    public String getSaveUserPage(Model model) {

        User user1 = new User();
        model.addAttribute("user", user1);
        return "userFiles/saveUser";
    }

   /* validation/ status */
     /*@RequestMapping(value = "/getUsers/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(User user) {
        if (user == null)
             return  new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        userService.save(user);
        return  new ResponseEntity<>("saved", HttpStatus.OK);


    }  */

    /* For creating the student */
    @RequestMapping(value = "/getUsers/save", method = RequestMethod.POST)
    public String saveUser(User user) {

        userService.save(user);
        return "redirect:/getUsers";
    }

    /*For getting update page of user*/
    @RequestMapping(value = "/getUsers/getUpdatePage/{id}", method = RequestMethod.GET)
    public String getUpdatePage(@PathVariable(name = "id") long id, Model model) {

        User user;
        user = userService.findById(id);
        model.addAttribute("user", user);
        return "userFiles/updateUser";
    }


    /*For updating the user*/
    @RequestMapping(value = "/getUsers/updateUser/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable(name = "id") long id, @Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            user.setId(id);
            return "userFiles/updateUser";
        }

        userService.save(user);
        return "redirect:/getUsers";
    }

    /* For deleting the user*/
    @GetMapping("/getUsers/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") long id, Model model) {
        User user = userService.findById(id);
        //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.delete(user);

        return "redirect:/getUsers";
    }


    /* to get list Books of user */
    @RequestMapping("/getUsers/showUserBooks/{id}")
    public String getListOfUserBooks(Model model, @PathVariable(value = "id") long id) {

        List<UserBooksDto> userBooksDtoList = new ArrayList<>();


        List<Book> books = ownershipService.findAllByUserIdAndStatusTrue(id);



        for (Book b:books) {

            if( ownershipService.getStatusByUserIdAndBookid(id, b.getId()) ) {

                UserBooksDto userBooksDto = new UserBooksDto();

                userBooksDto.bookId = b.getId();
                userBooksDto.author = b.getAuthor();
                userBooksDto.description = b.getDescription();
                userBooksDto.iSBN = b.iSBN;
                userBooksDto.number = b.number;
                userBooksDto.name = b.getName();

                userBooksDto.userId = id;
                userBooksDtoList.add(userBooksDto);
            }

        }

        model.addAttribute("dtoList", userBooksDtoList);
        return "userFiles/showBooksUser";
    }


    /* For deleting the Book of user*/

    @GetMapping("/getUsers/deleteUserBook/{userId}/{bookId}")
    public String deleteUserBook(@PathVariable(name = "userId") long userId,@PathVariable(name = "bookId") long bookId, Model model) {

        ownershipService.deleteUserBook(userId,bookId);

        //.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        return "redirect:/getUsers";
    }

}
