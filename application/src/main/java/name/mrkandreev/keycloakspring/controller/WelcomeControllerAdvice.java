package name.mrkandreev.keycloakspring.controller;

import name.mrkandreev.keycloakspring.security.IllegalPrincipalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackageClasses = WelcomeController.class)
public class WelcomeControllerAdvice {
  @ExceptionHandler(IllegalPrincipalException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ResponseBody
  public String notFoundException(IllegalPrincipalException e) {
    return "Illegal principal";
  }
}
