package name.mrkandreev.keycloakspring.security;

import org.keycloak.KeycloakPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {
  @Override
  public String getUser() throws IllegalPrincipalException {
    return getKeycloakPrincipal().getName();
  }

  private KeycloakPrincipal<?> getKeycloakPrincipal() throws IllegalPrincipalException {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (!(principal instanceof KeycloakPrincipal)) {
      throw new IllegalPrincipalException();
    }

    return (KeycloakPrincipal<?>) principal;
  }
}
