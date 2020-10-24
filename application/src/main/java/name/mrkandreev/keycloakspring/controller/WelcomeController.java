package name.mrkandreev.keycloakspring.controller;

import name.mrkandreev.keycloakspring.security.AuthenticationFacade;
import name.mrkandreev.keycloakspring.security.IllegalPrincipalException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("welcome")
public class WelcomeController {
  private final AuthenticationFacade authenticationFacade;

  public WelcomeController(AuthenticationFacade authenticationFacade) {
    this.authenticationFacade = authenticationFacade;
  }

  @GetMapping
  public String getUserName() throws IllegalPrincipalException {
    return String.format("Hello, %s", authenticationFacade.getUser());
  }
}
