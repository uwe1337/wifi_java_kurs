package at.wifi.swdev.kostenrechner.controller;

import at.wifi.swdev.kostenrechner.data.Author;
import at.wifi.swdev.kostenrechner.data.Kostenrechner;
import at.wifi.swdev.kostenrechner.service.KostenrechnerService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class KostenrechnerController {
    
    @Autowired
    private KostenrechnerService service;
    
    @GetMapping({"/", "/index"})
    public String showIndex(Model model) {
        
        model.addAttribute("allKRItems", service.findAll());
        model.addAttribute("totalAmount", service.getTotalAmount());
        model.addAttribute("totalCategorys", service.getTotalCategorys());
        
        HashMap<String, Double> hashmap = service.getHMForCategoryAndAmount();
        model.addAttribute("arrayValues", service.getArrayValues(hashmap));
        model.addAttribute("arrayCategorys", service.getArrayCategorys(hashmap));
        return "/index";
    }
    
    @PostMapping("/addcost")
    public String addCosts(Model model,
            Kostenrechner kostenrechner,
            @RequestParam(required = false, defaultValue = "0") double amount) {
        
        System.out.println("kostenrechner: " + kostenrechner);
        service.addCost(kostenrechner);        
        return "redirect:/index";
    }

    @PostMapping("/removecost")
    public String removeCost(@RequestParam(required = false, defaultValue = "0") long deleteID,
            RedirectAttributes redirectAttrs) {
        
        boolean removeResult = service.removeCost(deleteID);
        if(removeResult) {
            redirectAttrs.addFlashAttribute("successMSG", "Der Eintrag wurde erfolgreich gelöscht.");
        }
        return "redirect:/index";
    }
    
    @PostMapping("/editcost")
    public String editCost(@RequestParam(required = false, defaultValue = "0") long editID,
            @RequestParam(required = false, defaultValue = "0") double editAmount,
            @RequestParam String editDate,
            @RequestParam(required = false, defaultValue = "0") String editCategory,
            @RequestParam(required = false, defaultValue = "0") String editDescription,
            RedirectAttributes redirectAttrs) {
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        boolean editResult = service.editCost(editID, editAmount, LocalDate.parse(editDate, format), editCategory, editDescription);
        if(editResult) {
            redirectAttrs.addFlashAttribute("successMSG", "Der Eintrag wurde erfolgreich bearbeitet.");
        } else {
            redirectAttrs.addFlashAttribute("errorMSG", "Der Eintrag konnte nicht bearbeitet werden.");
        }
        return "redirect:/index";
    }
    
    @GetMapping("/author")
    public String showAuthor(Model model,
            Author author) {
        
        model.addAttribute("author", service.getAuthor(author));
        return "/author";
    }
    
    @GetMapping("/error")
    public String showError() {
        return "/error";
    }
    
}
