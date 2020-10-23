package name.mrkandreev.keycloakclient.keycloak;

import java.util.concurrent.atomic.AtomicReference;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class KeycloakConfiguration {
  @Bean
  @ConfigurationProperties(prefix = "keycloak-remote.service")
  public KeycloakProperties keycloakProperties() {
    return new KeycloakProperties();
  }

  @Bean
  @ConfigurationProperties(prefix = "keycloak-remote.credentials")
  public KeycloakCredentials keycloakCredentials() {
    return new KeycloakCredentials();
  }

  @Bean(name = "keycloakWebClient")
  public WebClient webClient(KeycloakProperties keycloakProperties) {
    return WebClient.builder()
        .baseUrl(String.format("http://%s", keycloakProperties.getPrivateDomain()))
        .defaultHeaders(
            headers -> {
              headers.set("X-Forwarded-Proto", keycloakProperties.getPublicProtocol());
              headers.set("X-Forwarded-Host", keycloakProperties.getPublicDomain());
              headers.set("Content-type", "application/json");
            })
        .build();
  }

  @Bean(name = "keycloakToken")
  public AtomicReference<KeycloakToken> tokenAtomicReference() {
    return new AtomicReference<>();
  }

}
