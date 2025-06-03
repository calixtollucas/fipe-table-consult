package dev.ruka.FIPETableConsult;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.ruka.FIPETableConsult.Main.Menu;

@SpringBootApplication
public class FipeTableConsultApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FipeTableConsultApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu();
		menu.start();
	}

}
