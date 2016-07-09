package be.provikmo.leveranciers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"be.provikmo.adres", "be.provikmo.leveranciers.*"})
public class LeveranciersAngular2Application {

	public static void main(String[] args) {
		SpringApplication.run(LeveranciersAngular2Application.class, args);
	}
}
