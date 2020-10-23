package name.mrkandreev.keycloakclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KeycloakClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakClientApplication.class, args);
	}

}
