package at.wifi.swdev.rechteck.controller;

import at.wifi.swdev.rechteck.data.Author;
import at.wifi.swdev.rechteck.data.Rechteck;
import at.wifi.swdev.rechteck.service.RechteckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RechteckController {

    @Autowired
    private RechteckService service;

    @GetMapping({"/", "/index"})
    public String showIndex(Rechteck rechteck) {
        return "/index";
    }

    @PostMapping("/docalc")
    public String doCalc(Model model,
            Rechteck rechteck,
            @RequestParam(required = false, defaultValue = "0") double seiteA,
            @RequestParam(required = false, defaultValue = "0") double seiteB,
            RedirectAttributes redirectAttrs) {

        service.setDataForRechteck(rechteck, seiteA, seiteB);
        String calcResult = service.calcRechteck(rechteck);

        if (calcResult.length() == 0) {
            redirectAttrs.addFlashAttribute("rechteck", rechteck);
            redirectAttrs.addFlashAttribute("successMSG", "Die Berechnung wurde erfolgreich ausgeführt.");
        } else {
            redirectAttrs.addFlashAttribute("errorMSG", "Die Berechnung konnte nicht ausgeführt werden, Grund: " + calcResult);
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
