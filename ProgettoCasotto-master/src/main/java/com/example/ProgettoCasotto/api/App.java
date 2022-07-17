package com.example.ProgettoCasotto.api;

import com.example.ProgettoCasotto.models.Bibita;
import com.example.ProgettoCasotto.models.Gioco;
import com.example.ProgettoCasotto.models.Piatto;
import com.example.ProgettoCasotto.models.Sport;
import com.example.ProgettoCasotto.services.Comanda;
import com.example.ProgettoCasotto.services.ComandaAttivita;
import com.example.ProgettoCasotto.services.Ordine;
import com.example.ProgettoCasotto.services.OrdineAttivita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class App {

    @Autowired
    Bar bar1;

    @Autowired
    SegreteriaAttivita segreteriaAttivita;

    @GetMapping("/bar_avvio")
    public String goBar() {
        return "Bar";
    }

    @GetMapping("/scelta_ordine")
    public String goChoice() {
        return "SceltaOrdine";
    }

    @GetMapping("/chiudi_comanda")
    public String goClose(Model model) {
        Comanda comanda = new Comanda();
        model.addAttribute("comanda", comanda);
        return "ChiudiComanda";
    }


    @GetMapping("/menu_piatti_bibite")
    public String ListaPosti(Model model) throws IOException {
        model.addAttribute("bevanda", bar1.getMenuBevande());
        model.addAttribute("piatto", bar1.getMenuPiatti());
        model.addAttribute("next_path", "/");//btn_txt
        model.addAttribute("btn_txt", "Indietro");
        return "PiattiBibitaList";
    }

    @GetMapping("/scegli_cibo")
    public String scegliPiatti(Model model) throws IOException {
        Ordine order = new Ordine();
        model.addAttribute("order", order);
        model.addAttribute("piatto", bar1.getMenuPiatti());
        model.addAttribute("next_path", "/");//btn_txt
        model.addAttribute("btn_txt", "Avanti");
        return "OrdinaCibo";
    }

    @GetMapping("/scegli_bibita")
    public String scegliBibita(Model model) throws IOException {
        Ordine order = new Ordine();
        model.addAttribute("order", order);
        model.addAttribute("bibita", bar1.getMenuBevande());
        model.addAttribute("next_path", "/");//btn_txt
        model.addAttribute("btn_txt", "Avanti");
        return "OrdinaBibita";
    }

    @PostMapping("/register_cibo")
    public String submitFormCibo(@ModelAttribute("order") Ordine order) {
        Comanda c = new Comanda();
        c.setID(order.getID_posto());
        c.add_order_piatti(bar1.getPiattoByID(order.getID_bar()));
        bar1.creaComanda(c);
        return "register_success";
    }

    @PostMapping("/register_bibita")
    public String submitFormBibite(@ModelAttribute("order") Ordine order) {
        Comanda c = new Comanda();
        c.setID(order.getID_posto());
        c.add_order_bibite(bar1.getBibitabyID(order.getID_bar()));
        bar1.creaComanda(c);
        return "register_success";
    }

    @GetMapping("/conferma_QR")
    public String selectQR(Model model) {
        Comanda comanda = new Comanda();
        model.addAttribute("comanda", comanda);
        return "Conferma_QR";
    }


    @PostMapping("/vedi_comanda")
    public String vediComanda(Model model, @ModelAttribute("comanda") Comanda c) {
        if (bar1.getComandaByID(c.getID()) != null) {
            Comanda lista = bar1.getComandaByID(c.getID());
            ArrayList<Piatto> cibo_comprato = lista.getOrdini_piatti();
            ArrayList<Bibita> bibite_comprate = lista.getOrdini_bibite();
            model.addAttribute("comanda", lista);
            model.addAttribute("bibite", bibite_comprate);
            model.addAttribute("piatti", cibo_comprato);
            return "LeggiComanda";
        } else return "QRNonEsiste";
    }

    @PostMapping("/fai_scontrino")
    public String faiScontrino(Model model, @ModelAttribute("comanda") Comanda c) {
        if (bar1.getComandaByID(c.getID()) != null) {
            Comanda lista = bar1.getComandaByID(c.getID());
            ArrayList<Piatto> cibo_comprato = lista.getOrdini_piatti();
            ArrayList<Bibita> bibite_comprate = lista.getOrdini_bibite();
            double totale = bar1.getTotalPrice(lista);
            bar1.deleteComanda (bar1.getComandaByID(c.getID()));
            model.addAttribute("totale", totale);
            model.addAttribute("comanda", lista);
            model.addAttribute("bibite", bibite_comprate);
            model.addAttribute("piatti", cibo_comprato);
            return "Scontrino";
        } else return "QRNonEsiste";
    }

    @GetMapping("/vai_segreteria")
    public String goSeg() {
        return "SegreteriaAttivita";
    }

    @GetMapping("/vedi_attivita")
    public String vediAttivita(Model model)  {
        model.addAttribute("giochi",segreteriaAttivita.getGiochi_attivi());
        model.addAttribute("sport",segreteriaAttivita.getSport_attivi());
        model.addAttribute("next_path", "/");//btn_txt
        model.addAttribute("btn_txt", "Indietro");
        return "MenuAttivita";
    }
    @GetMapping("/comanda_attivita")
    public String goChoiceAct() {
        return "SceltaAttivita";
    }


    @GetMapping("/scegli_gioco")
    public String scegliGioco(Model model) throws IOException {
        OrdineAttivita order = new OrdineAttivita();
        model.addAttribute("order", order);
        model.addAttribute("gioco", segreteriaAttivita.getGiochi_attivi());
        model.addAttribute("next_path", "/");//btn_txt
        model.addAttribute("btn_txt", "Avanti");
        return "OrdinaGioco";
    }

    @GetMapping("/scegli_sport")
    public String scegliSport(Model model) throws IOException {
        OrdineAttivita order = new OrdineAttivita();
        model.addAttribute("order", order);
        model.addAttribute("sport", segreteriaAttivita.getSport_attivi());
        model.addAttribute("next_path", "/");//btn_txt
        model.addAttribute("btn_txt", "Avanti");
        return "OrdinaSport";
    }

    @PostMapping("/register_gioco")
    public String submitFormGioco(@ModelAttribute("order") OrdineAttivita order) {
        if (segreteriaAttivita.checkPartecipantiGiochi(order)) {
            segreteriaAttivita.getGiocobyID(order.getID_attivita()).setNumPrenotati(segreteriaAttivita.getGiocobyID(order.getID_attivita()).getNumPrenotati() + Integer.parseInt(order.getPartecipanti()));
            order.setPrezzo(segreteriaAttivita.getGiocobyID(order.getID_attivita()).getPrezzo());
            ComandaAttivita c = new ComandaAttivita();
            c.setID(order.getID_posto());
            c.add_order_gioco(segreteriaAttivita.getGiocobyID(order.getID_attivita()));
            c.getOrdini_gioco().get(0).setPrenotatiSingoloPosto(Integer.parseInt(order.getPartecipanti()));
            segreteriaAttivita.addOrUpdate(c);
            return "registerA_success";
        }
        else return "registerFailure";
    }

    @PostMapping("/register_sport")
    public String submitFormSport(@ModelAttribute("order") OrdineAttivita order) {
        if ((segreteriaAttivita.checkPartecipantiSport(order))){

            segreteriaAttivita.getSportbyID(order.getID_attivita()).setNumPrenotati(segreteriaAttivita.getSportbyID(order.getID_attivita()).getNumPrenotati() + Integer.parseInt(order.getPartecipanti()));
            order.setPrezzo(segreteriaAttivita.getSportbyID(order.getID_attivita()).getPrezzo());
            ComandaAttivita c = new ComandaAttivita();
            c.setID(order.getID_posto());
            c.add_order_sport(segreteriaAttivita.getSportbyID(order.getID_attivita()));
            c.getOrdini_sport().get(0).setPrenotatiSingoloPosto(Integer.parseInt(order.getPartecipanti()));
            segreteriaAttivita.addOrUpdate(c);
            return "registerA_success";
        }
        else return "registerFailure";

    }


    @GetMapping("/chiudi_comandaAtt")
    public String goCloseAtt(Model model) {
        OrdineAttivita comanda = new OrdineAttivita();
        model.addAttribute("comanda", comanda);
        return "ChiudiComandaAtt";
    }
    @PostMapping("/fai_scontrinoAtt")
    public String faiScontrinoAtt(Model model, @ModelAttribute("comanda") OrdineAttivita order) {
        if (segreteriaAttivita.getPrenotazioniByID(order.getID_posto()) != null) {
            ComandaAttivita prenotedAct = segreteriaAttivita.getPrenotazioniByID(order.getID_posto());
            double totale = segreteriaAttivita.getTotalPrice(prenotedAct.getID());
            segreteriaAttivita.deleteComanda (order.getID_posto());
            ArrayList<Gioco> comandaGioco = prenotedAct.getOrdini_gioco();
            ArrayList<Sport> comandaSport = prenotedAct.getOrdini_sport();
            model.addAttribute("totale", totale);
            model.addAttribute("comanda", prenotedAct);
            model.addAttribute("comandasport", comandaSport);
            model.addAttribute("comandagioco",comandaGioco);
            return "ScontrinoAtt";
        } else return "QRNonEsisteA";
    }
}
