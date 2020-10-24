package name.mrkandreev.keycloakspring.security;

import org.springframework.stereotype.Component;

@Component
public interface AuthenticationFacade {
  String getUser() throws IllegalPrincipalException;
}
