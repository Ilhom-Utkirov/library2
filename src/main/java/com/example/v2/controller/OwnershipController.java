package com.example.v2.controller;
;
import com.example.v2.model.Ownership;
import com.example.v2.service.OwnershipService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/ownership")
public class OwnershipController {

    private final OwnershipService ownershipService;
    public OwnershipController(OwnershipService ownershipService) {
        this.ownershipService = ownershipService;
    }


    /*show list of ownerships*/
    @RequestMapping("/show")
    public String getOwnershipPageShow(Model model) {

        List<Ownership> ownerships = ownershipService.findAllByStatus(true);
        model.addAttribute("ownerships", ownerships);
        return "OwnershipFiles/ownershipList";

    }


    /*when user wants to get book, new instance of ownership is created.
     When user clicks Dou you want to order book? */
    @GetMapping("/getSavePage")
    public String getOwnershipSavePage(Model model) {

        Ownership ownership = new Ownership();
        model.addAttribute("ownership", ownership);
        return "OwnershipFiles/saveOwnership";
    }


    /*save for submit button*/
    @PostMapping("/save")
    public String OwnerSave(Model model, Ownership ownership) {

        ownership.setStatus(true);
        ownershipService.save(ownership);
        return "redirect:/ownership/show";
    }

    /*For getting edit ownership page*/
    @RequestMapping(value = "/getUpdatePage/{id}" , method = RequestMethod.GET)
    public String getUpdatePageOwnership(@PathVariable(name = "id") long id, Model model){

        Ownership ownership;
        ownership = ownershipService.findById(id);
        model.addAttribute("ownership", ownership);

        return "OwnershipFiles/updateOwnership";
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateOwnership(@PathVariable(name = "id") long id, @Valid Ownership ownership, BindingResult result, Model model){

        if(result.hasErrors()){
            ownership.setId(id);
            return "OwnershipFiles/updateOwnership";
        }

        ownershipService.save(ownership);
        return "redirect:/ownership/show";
    }

    @GetMapping("/getDeletePage/{id}")
    public String deleteOwnership(@PathVariable(name = "id") String id){

        ownershipService.delete(Long.valueOf(id));
        return "redirect:/ownership/show";
    }


    /*show list of ownerships Paging*/
    @GetMapping("/showPaging")
    public String getOwnershipPageShowPaging(
            Model model,
            @RequestParam("page")Optional<Integer> page,
            @RequestParam("size")Optional<Integer> size
            ) {

        /*if null set it to first page*/
        int currentPage = page.orElse(1);
        int currentSize = size.orElse(5);
        int startItem = currentPage * currentSize;
        Page<Ownership>  ownershipPage = ownershipService
                .findPaginated(PageRequest.of(currentPage-1, currentSize));
        model.addAttribute("ownershipPage",ownershipPage);

        int totalPages = ownershipPage.getTotalPages();
        if(totalPages>0){
            List<Integer> pageNumbers= IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }


        return "OwnershipFiles/ownershiplistPaging";

    }


    @RequestMapping(value = "/ajax/list")
    @ResponseBody
    public HashMap<String, Object> getOwnershipList() {

        List<Ownership> ownershipList = ownershipService.findAll();
        HashMap<String, Object> result = new HashMap<>();
        List<Object[]> convenientForJSONArray = new ArrayList<>(ownershipList.size());
        for (Ownership o : ownershipList) {
            convenientForJSONArray.add(new Object[]{
                    o.getId(),
                    o.getBookId(),
                    o.getUserId(),
                    o.getDateFrom(),
                    o.getDateTo(),
            });
        }


        System.out.println("entered ajax list check : ok");
        result.put("data", convenientForJSONArray);
        return result;
    }


    @GetMapping("/serverSideList")
    public String OwnershipAjaxServerSideList(){
        return "OwnershipFiles/OwnershipAjaxServerSideList";
    }

}
