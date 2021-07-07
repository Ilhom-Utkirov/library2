package com.example.v2.controller;


import com.example.v2.model.Book;
import com.example.v2.service.BookService;
import com.example.v2.service.UserService;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/book")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")  //it doesnot work
public class BookController {


    private final BookService bookService;
    private final UserService userService;

    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    /* Show all books very simple form*/
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE','ROLE_STUDENT')")
    @RequestMapping("/showAll")
    public String showAllBooks(Model model) {

        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "bookFiles/showBooklist";
    }

/*get book list of serverside in ajax*/
    @GetMapping("/serverSideAjax")
    public String getServerSideAjax(){

        return "bookFiles/booksServerSideAjax";
    }

    /* Show all books Ajax version*/
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    @RequestMapping("/showAjax")
    public String showAllBooksPageable(Model model) {

        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "bookFiles/showBooklistAjax";
    }


    /*Code for Ajax version of showing the list of books*/
    @RequestMapping(value = "/ajax/bookList")
    @ResponseBody
    public HashMap<String, Object> getBookListAjax(){

        List<Book> bookList = bookService.findAll();
        HashMap<String, Object> result = new HashMap<>();
        List<Object[]> convenientForJSONArray = new ArrayList<>(bookList.size());
        for (Book b: bookList) {
            convenientForJSONArray.add(new Object[]{
                b.getId(),
                b.getName(),
                b.getAuthor(),
                b.getDescription(),
                b.getISBN(),
                b.getNumber()
            });
        }

        System.out.println("check book list ajax working : ok");
        result.put("data", convenientForJSONArray);
        return result;
    }

    /* For showing the list of books Pageable enabled */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    @RequestMapping(value = "/listBooks", method = RequestMethod.GET)
    public String listBooks(
       Model model,
       @RequestParam("page")Optional<Integer> page,
       @RequestParam("size")Optional<Integer> size
    ){

        /*if null then it is first time. Set it */
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage-1, pageSize));

        model.addAttribute("bookPage", bookPage);

        int totalPages = bookPage.getTotalPages();
        if(totalPages>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);

        }

            return "bookFiles/showBooklistPageable";
    }





    /* get save Book page. Note sending new object */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    @RequestMapping(value = "/getSaveBookPage", method = RequestMethod.GET)
    public String getSaveBookPage(Model model) {

        Book book1 = new Book();
        model.addAttribute("book", book1);
        return "bookFiles/saveBook";
    }

    /* or creating the Books */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(Book book) {

        bookService.save(book);
        return "redirect:/book/showAll";
    }

    /*for getting update page of book*/
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
    @RequestMapping(value = "/getUpdatePage/{id}", method = RequestMethod.GET)
    public String getUpdateBookPage(@PathVariable(name = "id") long id, Model model) {

        Book book;
        book = bookService.findById(id);
        model.addAttribute("book", book);
        return "bookFiles/updateBook";

    }

    /*for updateing of book*/
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String getUpdateBookPage(@PathVariable(name = "id") long id, @Valid Book book, BindingResult result, Model model) {

        if (result.hasErrors()) {
            book.setId(id);
            return "bookFiles/updateBook";
        }

        bookService.save(book);
        return "redirect:/book/showAll";

    }

    @GetMapping("/delete/{id}")

    public String deleteBook(@PathVariable(name = "id") long id, Model model) {

        Book book;
        book = bookService.findById(id);
        bookService.delete(book);
        return "redirect:/book/showAll";
    }

}