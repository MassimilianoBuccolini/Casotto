package com.example.ProgettoCasotto.api;


import com.example.ProgettoCasotto.models.LiberoPosto;
import com.example.ProgettoCasotto.models.OccupatoPosto;
import com.example.ProgettoCasotto.services.Attrezatura;
import com.example.ProgettoCasotto.services.Orario;
import com.example.ProgettoCasotto.util.DettagliPosto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;
@SpringBootApplication
public class Spiaggia {


    private ArrayList<LiberoPosto> posti_liberi=new ArrayList<>();
    private ArrayList<OccupatoPosto> posti_occupati=new ArrayList<>();
    private List<DettagliPosto> stato_spiaggia = new ArrayList<>();

    @PostConstruct
    public void RiempiTable() throws IOException {
        RiempiPostiLiberi();
        RiempiPostiOccupati();
        RiempiStato();
    }

    public List<LiberoPosto> getPostCap(int i){
        return posti_liberi.stream()
                .filter(x->x.getCapienza()>i-1)
                .collect(Collectors.toList());
    }

    public LiberoPosto getPostobyID(String id){
        return posti_liberi.stream().filter(x->x.getQr().equals(id)).findFirst().orElse(null);
    }

    //TODO add comanda
    public void partecipa_posto(OccupatoPosto ps) {

        removeFromLibero(ps);
        changeFromStats(ps,getDettagliOccupato(ps));
        posti_occupati.add(ps);


    }

    private void removeFromLibero(OccupatoPosto ps){
        posti_liberi.remove(posti_liberi.stream()
                .filter(x -> x.getQr().equals(ps.getQr()))
                .findFirst().orElse(null));

    }

    private void changeFromStats(OccupatoPosto ps,DettagliPosto dp){
        stato_spiaggia.set(stato_spiaggia.indexOf(
                        stato_spiaggia.stream()
                                .filter(x -> x.getQr().equals(ps.getQr()))
                                .findFirst().orElse(null))
                ,dp);
    }

    private void RiempiPostiLiberi() throws IOException {


        String path = "C:\\Users\\albuc\\Desktop\\Documenti Max\\Ingegneria del software\\ProgettoCasotto-master\\src\\main\\java\\com\\example\\ProgettoCasotto\\dao\\posti_db.csv";
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record : records) {
            if (!record.get(0).equals("qr")){

                String qr = record.get(0);
                Integer capienza = Integer.valueOf(record.get(1));

                LiberoPosto dp = new LiberoPosto(qr,capienza);
                posti_liberi.add(dp);
            }
        }
    }//

    private void RiempiPostiOccupati() throws IOException{
        String path = "C:\\Users\\albuc\\Desktop\\Documenti Max\\Ingegneria del software\\ProgettoCasotto-master\\src\\main\\java\\com\\example\\ProgettoCasotto\\dao\\posti_occupati_db.csv";
        Reader in = new FileReader(path);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record : records) {
            if (!record.get(0).equals("qr")){
                String qr = record.get(0);
                Integer capienza = Integer.valueOf(record.get(1));

                String persona = record.get(2);


                List<Attrezatura> attrezature = Arrays.stream(record.get(3)
                                .split("/"))
                        .map(x -> x.replace(" ", ""))
                        .map(Attrezatura::valueOf).collect(Collectors.toList());

                Orario tempo_prenotazione = Orario.valueOf(record.get(4));
                int giorni_prenotazione = 0;
                if (tempo_prenotazione.equals(Orario.GIORNI_CONSECUTIVI)){
                    giorni_prenotazione = Integer.parseInt(record.get(5));}

                OccupatoPosto dp = new OccupatoPosto(qr,capienza,persona,attrezature,tempo_prenotazione,giorni_prenotazione);
                posti_occupati.add(dp);}
        }
    }


    public List<DettagliPosto> getDettagli(){
        return stato_spiaggia;
    }

    private void RiempiStato(){
        Iterator<LiberoPosto> pli = posti_liberi.iterator();
        LiberoPosto libp;
        while (pli.hasNext()){
            libp =  pli.next();
            DettagliPosto dp = getDettagliLibero(libp);
            stato_spiaggia.add(dp);
        }

        Iterator<OccupatoPosto> ocp = posti_occupati.iterator();
        OccupatoPosto pps;
        while (ocp.hasNext()){
            pps = ocp.next();
            DettagliPosto dp = getDettagliOccupato(pps);
            stato_spiaggia.add(dp);
        }

        long seed = 25L;
        Collections.shuffle(stato_spiaggia,new Random(seed));
    }

    private DettagliPosto getDettagliLibero( LiberoPosto libp){

        return new DettagliPosto(libp.getQr(),libp.getCapienza());
    }

    private DettagliPosto getDettagliOccupato( OccupatoPosto pps){
      return new DettagliPosto(pps.getQr(), pps.getCapienza(), pps.getPersona(),
                pps.getAttrezature(),pps.getTempo_prenotazione(),pps.getGiorni_prenotazione());
    }

    public void LiberaPosto(OccupatoPosto ps){
        LiberoPosto libp = new LiberoPosto(ps.getQr(), ps.getCapienza());
        posti_liberi.add(libp);
        posti_occupati.remove(ps);
        changeFromStats(ps,getDettagliLibero(libp));

    }


}
