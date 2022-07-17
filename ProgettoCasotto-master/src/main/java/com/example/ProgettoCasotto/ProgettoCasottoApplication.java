package com.example.ProgettoCasotto;

import com.example.ProgettoCasotto.api.DBManager;
import com.example.ProgettoCasotto.api.Spiaggia;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.Scanner;

@SpringBootApplication
public class ProgettoCasottoApplication {
	// html checkbox
	public static void main(String[] args) throws SQLException {
		Scanner reader = new Scanner(System.in);
		setCasottoDB(reader);
		SpringApplication.run(Spiaggia.class, args);
	}
	public static void setCasottoDB(Scanner reader) throws  SQLException {
		System.out.println("ok");

		DBManager dbManager = DBManager.getInstance();
		dbManager.setDBManager("jdbc:postgresql://localhost:5432/Casotto", "postgres", "gattuso93");
		dbManager.DBtest();

	}

}
