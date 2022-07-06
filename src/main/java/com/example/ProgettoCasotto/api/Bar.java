package com.example.ProgettoCasotto.api;

import com.example.ProgettoCasotto.models.Bibita;
import com.example.ProgettoCasotto.models.Piatto;
import com.example.ProgettoCasotto.util.DettagliBevande;
import com.example.ProgettoCasotto.util.DettagliCibo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class Bar {

    private ArrayList<Bibita> bevande_disponibili = new ArrayList<>();
    private ArrayList<Piatto> piatti_disponibili = new ArrayList<>();

    private List<DettagliBevande> MenuBevande = new ArrayList<>();
    private List<DettagliCibo> MenuPiatti = new ArrayList<>();

    @PostConstruct
    private void riempiBar() throws IOException {
        riempiBibite();
        riempiPiatti();
        RiempiMenuBevande();
        RiempiMenuPiatti();
    }

    public List<DettagliBevande> getMenuBevande() throws IOException {
        return MenuBevande;
    }

    public List<DettagliCibo> getMenuPiatti() throws IOException {
        return MenuPiatti;
    }

    private void riempiBibite() throws IOException{
        String path = "C:\\Users\\albuc\\Desktop\\Documenti Max\\Ingegneria del software\\ProgettoCasotto-master\\src\\main\\java\\com\\example\\ProgettoCasotto\\dao\\bibite_db.csv";
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record : records) {
            if (!record.get(0).equals("ID")){
                String ID = record.get(0);
                String nome = record.get(1);
                Double prezzo = Double.valueOf(record.get(2));
                Boolean alcolico = record.get(3).equals("true");
                Integer disponibili = Integer.valueOf(record.get(4));

                Bibita bd = new Bibita(ID,nome,prezzo,alcolico,disponibili);
                bevande_disponibili.add(bd);}
        }
    }
    private void riempiPiatti() throws IOException{
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

                Piatto pt = new Piatto(nome, prezzo, ID, portata, disponibili);
                piatti_disponibili.add(pt);
            }
        }
    }

    public Bibita getBibitabyID(String id){
        return bevande_disponibili.stream().filter(x->x.getID().equals(id)).findFirst().orElse(null);
    }
    public Piatto getPiattoByID(String id){
        return piatti_disponibili.stream().filter(x->x.getID().equals(id)).findFirst().orElse(null);
    }

    private Double acquistabibita (String ID, Integer ordini) {
        if (ordini<= getBibitabyID(ID).getDisponibilità()) {
            getBibitabyID(ID).setDisponibilità(getBibitabyID(ID).getDisponibilità() - ordini);
            Double conto;
            conto = (getBibitabyID(ID).getPrezzo() * ordini);
            RiempiMenuBevande();
            return conto;
        }
        else throw new IndexOutOfBoundsException();
    }

    private Double acquistaPiatto (String ID, Integer ordini) {
        if (ordini<= getPiattoByID(ID).getDisponibili()) {
            getPiattoByID(ID).setDisponibili(getPiattoByID(ID).getDisponibili() - ordini);
            Double conto;
            conto = (getPiattoByID(ID).getPrezzo() * ordini);
            RiempiMenuPiatti();
            return conto;
        }
        else throw new IndexOutOfBoundsException();
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


    private DettagliBevande getDettagliBibite( Bibita b) {
        return new DettagliBevande(b.getID(),b.getNome(),b.getPrezzo(),b.isAlcolico(),b.getDisponibilità());

    }
    private DettagliCibo getDettagliPiatto (Piatto p) {
        return new DettagliCibo(p.getID(),p.getNome(),p.getPrezzo(),p.getPortata(),p.getDisponibili());
    }

}
