package com.example.ProgettoCasotto.api;


import com.example.ProgettoCasotto.models.Bibita;
import com.example.ProgettoCasotto.models.Gioco;
import com.example.ProgettoCasotto.models.Piatto;
import com.example.ProgettoCasotto.models.Sport;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {

    private static DBManager instance;
    private String url;
    private String user;
    private String pwd;
    private Connection conn = null;

    private DBManager() {
    }

    public void setDBManager(String url, String user, String pwd){
        this.url = url;
        this.user= user;
        this.pwd =pwd;
    }

    public static DBManager getInstance() {
        if (instance == null){
            instance = new DBManager();
        }
        return instance;
    }

    private void connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            System.out.println("Where is your PostgreSQL JDBC Driver?");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url,user,pwd);
            System.out.println("Database Connected");
        }catch (SQLException e){
            System.out.println("Connection Problem");
            e.printStackTrace();
        }
    }

    public boolean DBtest(){
        Boolean result = true;
        try {
            if (conn == null || !conn.isClosed()){
                connect();
                result = false;
            }
            DatabaseMetaData data = conn.getMetaData();
            System.out.println("Details" + data.getDatabaseProductName());
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

public ArrayList<Bibita> getBibite() {
        String SQL = "SELECT * FROM bibita;";
        int count = 0;
        ArrayList<Bibita> datiBibite = new ArrayList<Bibita>();
        Bibita bibita;
        int i = 1;
        try (Connection conn = DriverManager.getConnection(url,user,pwd);
             Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet result = stm.executeQuery(SQL);) {
            result.beforeFirst();
            while (result.next()){
                i = 1;
                bibita = new Bibita(result.getString(i++),result.getString(i++),result.getBoolean(i++),result.getInt(i++),result.getDouble(i));
                datiBibite.add(bibita);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datiBibite;

}

    public ArrayList<Piatto> getPiatti() {
        String SQL = "SELECT * FROM piatto;";
        int count = 0;
        ArrayList<Piatto> datiPiatto = new ArrayList<Piatto>();
        Piatto piatto;
        int i = 1;
        try (Connection conn = DriverManager.getConnection(url,user,pwd);
             Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet result = stm.executeQuery(SQL);) {
            result.beforeFirst();
            while (result.next()){
                i = 1;
                piatto = new Piatto(result.getString(i++),result.getString(i++),result.getDouble(i++),result.getString(i++),result.getInt(i));
                datiPiatto.add(piatto);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datiPiatto;

    }
    public ArrayList<Gioco> getGiochi() {
        String SQL = "SELECT * FROM gioco;";
        int count = 0;
        ArrayList<Gioco> datiGioco = new ArrayList<Gioco>();
        Gioco gioco;
        int i = 1;
        try (Connection conn = DriverManager.getConnection(url,user,pwd);
             Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet result = stm.executeQuery(SQL);) {
            result.beforeFirst();
            while (result.next()){
                i = 1;
                gioco = new Gioco(result.getString(i++),result.getString(i++),result.getDouble(i++),result.getInt(i));
                datiGioco.add(gioco);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datiGioco;

    }

    public ArrayList<Sport> getSport() {
        String SQL = "SELECT * FROM sport;";
        int count = 0;
        ArrayList<Sport> datiSport = new ArrayList<Sport>();
        Sport sport;
        int i = 1;
        try (Connection conn = DriverManager.getConnection(url,user,pwd);
             Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet result = stm.executeQuery(SQL);) {
            result.beforeFirst();
            while (result.next()){
                i = 1;
                sport = new Sport(result.getString(i++),result.getString(i++),result.getDouble(i++),result.getInt(i));
                datiSport.add(sport);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datiSport;

    }

}