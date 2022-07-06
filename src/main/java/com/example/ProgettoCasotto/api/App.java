package com.example.ProgettoCasotto.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class App {

    @Autowired
    Bar bar1;

    @GetMapping("/bar_avvio")
    public String goBar() {
        return "Bar";
    }


    @GetMapping("/menu_piatti_bibite")
    public String ListaPosti(Model model) throws IOException {
        model.addAttribute("bevanda", bar1.getMenuBevande());
        model.addAttribute("piatto", bar1.getMenuPiatti());
        model.addAttribute("next_path","/");//btn_txt
        model.addAttribute("btn_txt","Indietro");
        return "PiattiBibitaList";
    }

    @GetMapping("/ordina_bar")
    public String scegliPiatti(Model model) throws IOException {
        model.addAttribute("piatto", bar1.getMenuPiatti());
        model.addAttribute("next_path","/");//btn_txt
        model.addAttribute("btn_txt","Avanti");
        return "OrdinaBar";
    }
/*

    @GetMapping("/")
    public String RichiestaMenu(){
        return "PiattiBibitaList";}

    @GetMapping("/")
    public ModelAndView codaOrdini(){return null;};

    @GetMapping("/")
    public String ScegliProdotto(){return null;};

    @RequestMapping("/delete/{id}")
    public String AggiungiPiatto(){return null;};
    @RequestMapping("/delete/{id}")
    public String AggiungiBibita(){return null;};
*/
}
