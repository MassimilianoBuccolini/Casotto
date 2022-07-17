package com.example.ProgettoCasotto.api;

import com.example.ProgettoCasotto.models.LiberoPosto;
import com.example.ProgettoCasotto.models.OccupatoPosto;
import com.example.ProgettoCasotto.services.Attrezatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddettoPrenotazione {

    @Autowired
    Spiaggia spiaggia1;

    @GetMapping("/")
    public String home(){
        return "InizioMenu";
    }

    @RequestMapping(value = "/posti_disponibili",method = RequestMethod.GET)
    public String SelectPosto(Model model,@RequestParam(value = "numero_selezionato") String numero_selezionato){
        List<LiberoPosto> ls = spiaggia1.getPostCap(Integer.parseInt(numero_selezionato));
        if (ls.isEmpty()){
            model.addAttribute("numero",numero_selezionato);
            model.addAttribute("max_num",
                    spiaggia1.getPostCap(1).stream()
                            .map(LiberoPosto::getCapienza)
                            .mapToInt(x->x).max().orElse(1));
            return "PostoNonDisponibile";
        }
        model.addAttribute("posti",ls);
        return "ListaPostiSeleziona";
    }

    // TODO trasforma libero posto in posto occupato
    //
    @RequestMapping(value = "/id_posto",method = RequestMethod.GET)
    public String StartForm(Model model,@RequestParam(value = "qr_id") String qr_id){
        LiberoPosto std = spiaggia1.getPostobyID(qr_id);
        model.addAttribute("libero",std);
        model.addAttribute("posto",new OccupatoPosto(std));
        model.addAttribute("getAttr", Attrezatura.values());
        return "PrenotaMenu";
    }


    @GetMapping("/vedi_lista")
    public String ListaPosti(Model model){
        model.addAttribute("posti", spiaggia1.getDettagli());
        model.addAttribute("next_path","/");//btn_txt
        model.addAttribute("btn_txt","Indietro");
        return "DettagliPosto";
    }

    // POST METHOD
    @RequestMapping(value = "/show_new_list",method = RequestMethod.POST)
    public String Partecipa(Model model, @ModelAttribute("posto") OccupatoPosto ps) {
        spiaggia1.partecipa_posto(ps);

        model.addAttribute("posti", spiaggia1.getDettagli());
        model.addAttribute("next_path","home");
        model.addAttribute("btn_txt","fine prenotazione");
        return "DettagliPosto";
    }

    @GetMapping("/home")
    public String goHome(){
        return "HomeMenu";
    }

    @GetMapping("/start")
    public String Start(){
        return "InizioMenu";
    }

    @GetMapping("/numero_partecipanti")
    public String selectNumbers(Model model){
        model.addAttribute("numero",3);
        return "NumeroPrenotazioni";
    }

    @GetMapping("/prenota")
    public String inputPosto(){
        return "PrenotaMenu";
    }

}
