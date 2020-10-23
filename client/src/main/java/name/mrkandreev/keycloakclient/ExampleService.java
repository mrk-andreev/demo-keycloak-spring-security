package name.mrkandreev.keycloakclient;

import name.mrkandreev.keycloakclient.keycloak.KeycloakCredentialService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExampleService {
  private final KeycloakCredentialService keycloakCredentialService;

  public ExampleService(KeycloakCredentialService keycloakCredentialService) {
    this.keycloakCredentialService = keycloakCredentialService;
  }

  @Value("${application-remote.endpoint}")
  private String endpoint;

  @Scheduled(fixedDelay = 1_000L)
  public void run() {

    ResponseEntity<String> response = WebClient.builder()
        .baseUrl(endpoint)
        .defaultHeaders(httpHeaders -> httpHeaders.putAll(keycloakCredentialService.getHeaders()))
        .build()
        .get()
        .retrieve()
        .toEntity(String.class)
        .block();
    System.out.println(response.getBody());
  }
}
