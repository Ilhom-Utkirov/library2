package com.example.v2.controller;

import com.example.v2.model.BookDto;
import com.example.v2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookRest")
public class BookRestController {

    @Autowired
    public BookRepository bookRepository;

    @RequestMapping("/getBooks")
    public BookDto getUserPage(@RequestParam( value = "start", required = false) int start,
                               @RequestParam( value = "length", required = false) int length,
                               @RequestParam( value = "draw", required = false) int draw,
                               @RequestParam( value = "order[0][column]", required = false) int sortColIndex,
                               @RequestParam( value = "order[0][dir]", required = false) String order,
                               @RequestParam( value = "columns[0][data]", required = false) String col0DataAttrName   ){
        // To handle Sortingvalue =
        // sortColIndex => which column index is being sorted
        // order => asc or desc
        // col0DataAttrName => can be used to pass column name dynamically in DBQuery

        int totalBooks = bookRepository.totalBooks();
        BookDto bookDto = new BookDto();
        bookDto.setData(bookRepository.findBooksWithPage(start, length));
        bookDto.setRecordsFiltered(totalBooks);
        bookDto.setRecordsTotal(totalBooks); // Needed to show Pagination in Datatable
        bookDto.setDraw(draw);
        return bookDto;
    }

}
