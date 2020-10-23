package name.mrkandreev.keycloakclient.keycloak;

import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KeycloakCredentialService {
  private final KeycloakCredentials credentials;
  private final KeycloakProperties keycloakProperties;
  private final WebClient webClient;
  private final AtomicReference<KeycloakToken> tokenAtomicReference;

  public KeycloakCredentialService(KeycloakCredentials credentials,
                                   KeycloakProperties keycloakProperties,
                                   @Qualifier("keycloakWebClient") WebClient webClient,
                                   AtomicReference<KeycloakToken> tokenAtomicReference) {
    this.credentials = credentials;
    this.keycloakProperties = keycloakProperties;
    this.webClient = webClient;
    this.tokenAtomicReference = tokenAtomicReference;
  }

  public String getAccessToken() {
    if (tokenAtomicReference.get() == null) {
      fetchToken();
    }

    KeycloakToken token = tokenAtomicReference.get();

    return token != null ? token.getAccessToken() : null;
  }

  public HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", String.format("bearer %s", getAccessToken()));
    headers.set("X-Forwarded-Proto", keycloakProperties.getPublicProtocol());
    headers.set("X-Forwarded-Host", keycloakProperties.getPublicDomain());

    return headers;
  }

  @Scheduled(fixedRateString = "${keycloak-remote.token.reissue-timeout}")
  private void fetchToken() {
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("client_id", keycloakProperties.getResource());
    map.add("username", credentials.getUsername());
    map.add("password", credentials.getPassword());
    map.add("grant_type", "password");

    ResponseEntity<KeycloakToken> response =
        webClient
            .post()
            .uri(
                String.format(
                    "/auth/realms/%s/protocol/openid-connect/token", keycloakProperties.getRealm()))
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .bodyValue(map)
            .retrieve()
            .toEntity(KeycloakToken.class)
            .block();
    if (response != null
        && response.getStatusCode().is2xxSuccessful()
        && response.getBody() != null) {
      tokenAtomicReference.set(response.getBody());
    }
  }
}
