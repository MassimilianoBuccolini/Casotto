package com.example.ProgettoCasotto.api;

import com.example.ProgettoCasotto.models.Bibita;
import com.example.ProgettoCasotto.models.Piatto;
import com.example.ProgettoCasotto.services.Comanda;
import com.example.ProgettoCasotto.util.DettagliBevande;
import com.example.ProgettoCasotto.util.DettagliCibo;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class Bar {

    private final ArrayList<Bibita> bevande_disponibili = new ArrayList<>();
    private final ArrayList<Piatto> piatti_disponibili = new ArrayList<>();
    private final ArrayList<Comanda> ordinazioni = new ArrayList<>();
    private final List<DettagliBevande> MenuBevande = new ArrayList<>();
    private final List<DettagliCibo> MenuPiatti = new ArrayList<>();
    private DBManager manager;

    public Bar() throws SQLException {
    }

    @PostConstruct
    private void riempiBar() {
        this.manager = DBManager.getInstance();
        riempiBibite();
        riempiPiatti();
        RiempiMenuBevande();
        RiempiMenuPiatti();
    }

    public List<DettagliBevande> getMenuBevande() {
        return MenuBevande;
    }

    public List<DettagliCibo> getMenuPiatti() {
        return MenuPiatti;
    }

    private void riempiBibite(){
        ArrayList<Bibita> arr = manager.getBibite();
        bevande_disponibili.addAll(arr);
    }


/**
    private void riempiBibite() throws IOException {
        String path = "C:\\Users\\albuc\\Desktop\\Documenti Max\\Ingegneria del software\\ProgettoCasotto-master\\src\\main\\java\\com\\example\\ProgettoCasotto\\dao\\bibite_db.csv";
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record : records) {
            if (!record.get(0).equals("ID")) {
                String ID = record.get(0);
                String nome = record.get(1);
                Double prezzo = Double.valueOf(record.get(2));
                Boolean alcolico = record.get(3).equals("true");
                Integer disponibili = Integer.valueOf(record.get(4));

                Bibita bd = new Bibita(ID, nome, alcolico,disponibili, prezzo);
                bevande_disponibili.add(bd);
            }
        }
    }
**/

private void riempiPiatti(){
    ArrayList<Piatto> arr = manager.getPiatti();
    piatti_disponibili.addAll(arr);
}

/**
    private void riempiPiatti() throws IOException {
        String path = "C:\\Users\\albuc\\Desktop\\Documenti Max\\Ingegneria del software\\ProgettoCasotto-master\\src\\main\\java\\com\\example\\ProgettoCasotto\\dao\\piatt_db.csv";
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record : records) {
            if (!record.get(0).equals("ID")) {
                String ID = record.get(0);
                String nome = record.get(1);
                Double prezzo = Double.valueOf(record.get(2));
                String portata = record.get(3);
                Integer disponibili = Integer.valueOf(record.get(4));

                Piatto pt = new Piatto(ID,nome, prezzo, portata, disponibili);
                piatti_disponibili.add(pt);
            }
        }
    }
**/
    public Bibita getBibitabyID(String id) {
        return bevande_disponibili.stream().filter(x -> x.getID().equals(id)).findFirst().orElse(null);
    }

    public Piatto getPiattoByID(String id) {
        return piatti_disponibili.stream().filter(x -> x.getID().equals(id)).findFirst().orElse(null);
    }

    private void acquistabibita(String ID) {
        if (getBibitabyID(ID).getDisponibilità() >=1) {
            getBibitabyID(ID).setDisponibilità(getBibitabyID(ID).getDisponibilità() - 1);
            RiempiMenuBevande();
        } else throw new IndexOutOfBoundsException();
    }

    private void acquistaPiatto(String ID) {
        if (getPiattoByID(ID).getDisponibili() >=1) {
            getPiattoByID(ID).setDisponibili(getPiattoByID(ID).getDisponibili() - 1);
            RiempiMenuPiatti();
        } else throw new IndexOutOfBoundsException();
    }

    private void RiempiMenuBevande() {
        Iterator<Bibita> ite = bevande_disponibili.iterator();
        Bibita bib;
        while (ite.hasNext()) {
            bib = ite.next();
            if (!bib.getDisponibilità().equals(0)) {
                DettagliBevande db = getDettagliBibite(bib);
                MenuBevande.add(db);
            }
        }
    }

    private void RiempiMenuPiatti() {
        Iterator<Piatto> ite = piatti_disponibili.iterator();
        Piatto pt;
        while (ite.hasNext()) {
            pt = ite.next();
            if (!pt.getDisponibili().equals(0)) {
                DettagliCibo dc = getDettagliPiatto(pt);
                MenuPiatti.add(dc);

            }
        }
    }

    public void aggiornaComanda(Comanda c) {
        Iterator<Comanda> ite = ordinazioni.iterator();
        Comanda vecchia;
        Piatto piatto_ordinato;
        Bibita bibita_ordinata;
        while (ite.hasNext()) {
            vecchia = ite.next();
            if (vecchia.getID().equals(c.getID())) {
                for (Piatto piatto : c.getOrdini_piatti()) {
                    piatto_ordinato = piatto;
                    vecchia.add_order_piatti(piatto_ordinato);
                    acquistaPiatto(piatto_ordinato.getID());
                }
                for (Bibita bibita : c.getOrdini_bibite()) {
                    bibita_ordinata = bibita;
                    vecchia.add_order_bibite(bibita_ordinata);
                    acquistabibita(bibita_ordinata.getID());

                }
            }
        }
    }


    public void creaComanda (Comanda c){
        Iterator<Comanda> ite = ordinazioni.iterator();
        Comanda check;
        boolean flag = false;
        while (ite.hasNext()) {
            check = ite.next();
            if (check.getID().equals(c.getID())){
                aggiornaComanda(c);
                flag = true;
            }
        }

        if (!flag){
                ordinazioni.add(c);
        }

    }

    private DettagliBevande getDettagliBibite(Bibita b) {
        return new DettagliBevande(b.getID(), b.getNome(), b.getPrezzo(), b.isAlcolico(), b.getDisponibilità());

    }

    private DettagliCibo getDettagliPiatto(Piatto p) {
        return new DettagliCibo(p.getID(), p.getNome(), p.getPrezzo(), p.getPortata(), p.getDisponibili());
    }

    public Comanda getComandaByID(String ID) {
        Iterator<Comanda> ite = ordinazioni.iterator();
        Comanda vecchia;
        while (ite.hasNext()) {
            vecchia = ite.next();
            if (vecchia.getID().equals(ID)) {
                return vecchia;
            }
        }
           return null;
    }

    public double getTotalPrice (Comanda c){
        double totale = 0.0;

        Iterator<Piatto> iteP = c.getOrdini_piatti().iterator();
        Piatto check;
        while (iteP.hasNext()){
            check = iteP.next();
            totale = totale + check.getPrezzo();
        }

      Iterator<Bibita> iteB = c.getOrdini_bibite().iterator();
        Bibita check2;
        while (iteB.hasNext()){
            check2 = iteB.next();
            totale = totale + check2.getPrezzo();
        }
      return totale;

    }

    public void deleteComanda (Comanda c){
        Iterator<Comanda> iteC = ordinazioni.iterator();
        Comanda check;
        while (iteC.hasNext()){
            check = iteC.next();
            if (check.getID().equals(c.getID())){
                ordinazioni.remove(check);
                break;
            }
        }
    }
}