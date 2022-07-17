package com.example.ProgettoCasotto.api;

import com.example.ProgettoCasotto.models.Gioco;
import com.example.ProgettoCasotto.models.Sport;
import com.example.ProgettoCasotto.services.ComandaAttivita;
import com.example.ProgettoCasotto.services.OrdineAttivita;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

@SpringBootApplication
public class SegreteriaAttivita {



    private ArrayList<Gioco> giochi_attivi = new ArrayList<>();
    private ArrayList<Sport> sport_attivi = new ArrayList<>();

    private ArrayList<ComandaAttivita> prenotazioni = new ArrayList<>();
    private DBManager manager;


    public SegreteriaAttivita() throws SQLException {
    }


    @PostConstruct
    private void riempiBar() throws IOException {
        this.manager = DBManager.getInstance();
        riempiGiochi();
        riempiSport();
    }

    private void riempiGiochi(){
        ArrayList<Gioco> arr = manager.getGiochi();
        giochi_attivi.addAll(arr);
    }
    private void riempiSport(){
        ArrayList<Sport> arr = manager.getSport();
        sport_attivi.addAll(arr);
    }


/**
    private void riempiGiochi() throws IOException {
        String path = "C:\\Users\\albuc\\Desktop\\Documenti Max\\Ingegneria del software\\ProgettoCasotto-master\\src\\main\\java\\com\\example\\ProgettoCasotto\\dao\\giochi.csv";
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record : records) {
            if (!record.get(0).equals("ID")) {
                String ID = record.get(0);
                String nome = record.get(1);
                Double prezzo = Double.valueOf(record.get(2));
                Integer max = Integer.valueOf(record.get(3));

                Gioco game = new Gioco(ID, nome, prezzo);
                game.setMaxPartecipanti(max);
                giochi_attivi.add(game);
            }
        }
    }

            void riempiSport() throws IOException {
                String path = "C:\\Users\\albuc\\Desktop\\Documenti Max\\Ingegneria del software\\ProgettoCasotto-master\\src\\main\\java\\com\\example\\ProgettoCasotto\\dao\\sport.csv";
                Reader in = new FileReader(path);
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
                for (CSVRecord record : records) {
                    if (!record.get(0).equals("ID")) {
                        String ID = record.get(0);
                        String nome = record.get(1);
                        Double prezzo = Double.valueOf(record.get(2));
                        Integer max = Integer.valueOf(record.get(3));

                        Sport game = new Sport(ID, nome, prezzo);
                        game.setMaxPartecipanti(max);
                        sport_attivi.add(game);
                    }
                }
        }
**/
        public boolean checkPartecipantiSport(OrdineAttivita order){
        Integer nuovi = Integer.parseInt(order.getPartecipanti());
        String ID = order.getID_attivita();
            return getSportbyID(ID).getMaxPartecipanti() >= getSportbyID(ID).getNumPrenotati() + nuovi;

        }

    public boolean checkPartecipantiGiochi(OrdineAttivita order){
        Integer nuovi = Integer.parseInt(order.getPartecipanti());
        String ID = order.getID_attivita();
        return getGiocobyID(ID).getMaxPartecipanti() >= getGiocobyID(ID).getNumPrenotati() + nuovi;

    }





    public Sport getSportbyID(String id) {
        return sport_attivi.stream().filter(x -> x.getID().equals(id)).findFirst().orElse(null);
    }

    public Gioco getGiocobyID(String id) {
        return giochi_attivi.stream().filter(x -> x.getID().equals(id)).findFirst().orElse(null);
    }

    public ArrayList<Gioco> getGiochi_attivi() {
        return giochi_attivi;
    }

    public void setGiochi_attivi(ArrayList<Gioco> giochi_attivi) {
        this.giochi_attivi = giochi_attivi;
    }

    public ArrayList<Sport> getSport_attivi() {
        return sport_attivi;
    }

    public void setSport_attivi(ArrayList<Sport> sport_attivi) {
        this.sport_attivi = sport_attivi;
    }

    public ComandaAttivita getPrenotazioniByID (String ID){
        Iterator<ComandaAttivita> ite = prenotazioni.iterator();
        ComandaAttivita check;
        while (ite.hasNext()) {
            check = ite.next();
            if (check.getID().equals(ID)) {
                return check;
            }

        }
        return null;
    }

    public Double getTotalPrice (String ID) {
        Double totale = 0.0;
        ComandaAttivita check = getPrenotazioniByID(ID);

        for (Gioco g : check.getOrdini_gioco()) {
            totale = totale + g.getPrezzo() * g.getPrenotatiSingoloPosto();
        }
        for (Sport s : check.getOrdini_sport()) {
            totale = totale + s.getPrezzo() * s.getPrenotatiSingoloPosto();
        }
        return totale;
    }

        public void deleteComanda (String ID){
            prenotazioni.remove(getPrenotazioniByID(ID));
        }


    public ArrayList<ComandaAttivita> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(ArrayList<ComandaAttivita> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public void addOrUpdate (ComandaAttivita cmda){
        if (getPrenotazioniByID(cmda.getID())==null){
                prenotazioni.add(cmda);
            }
            else {
                for (Gioco g: cmda.getOrdini_gioco()) {
                    getPrenotazioniByID(cmda.getID()).add_order_gioco(g);
                }
                for (Sport s: cmda.getOrdini_sport()) {
                    getPrenotazioniByID(cmda.getID()).add_order_sport(s);
                }
        }





    }
}

