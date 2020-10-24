package name.mrkandreev.keycloakspring;

import org.keycloak.adapters.springboot.KeycloakAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {KeycloakAutoConfiguration.class})
public class KeycloakSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakSpringApplication.class, args);
	}

}
