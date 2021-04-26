package com.example.v2.controller;

import com.example.v2.model.OwnershipDTO;
import com.example.v2.repository.OwnershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ownershipRest")
public class OwnershipRestController {

    @Autowired
    public OwnershipRepository ownershipRepository;

    @RequestMapping("/getOwnerships")
    public OwnershipDTO getUserPage(@RequestParam( value = "start", required = false) int start,
                                    @RequestParam( value = "length", required = false) int length,
                                    @RequestParam( value = "draw", required = false) int draw,
                                    @RequestParam( value = "order[0][column]", required = false) int sortColIndex,
                                    @RequestParam( value = "order[0][dir]", required = false) String order,
                                    @RequestParam( value = "columns[0][data]", required = false) String col0DataAttrName   ){
        // To handle Sortingvalue =
        // sortColIndex => which column index is being sorted
        // order => asc or desc
        // col0DataAttrName => can be used to pass column name dynamically in DBQuery

        int totalOwnerships = ownershipRepository.totalOwnerships();
        OwnershipDTO ownershipDto = new OwnershipDTO();
        ownershipDto.setData(ownershipRepository.findOwnershipWithPage(start, length));
        ownershipDto.setRecordsFiltered(totalOwnerships);
        ownershipDto.setRecordsTotal(totalOwnerships); // Needed to show Pagination in Datatable
        ownershipDto.setDraw(draw);
        return ownershipDto;
    }

}
