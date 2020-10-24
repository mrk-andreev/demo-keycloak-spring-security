package name.mrkandreev.keycloakclient.keycloak;

public class KeycloakProperties {
  private String realm;

  private String resource;

  private String publicProtocol;

  private String publicDomain;

  private String privateDomain;

  public String getRealm() {
    return realm;
  }

  public void setRealm(String realm) {
    this.realm = realm;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getPublicProtocol() {
    return publicProtocol;
  }

  public void setPublicProtocol(String publicProtocol) {
    this.publicProtocol = publicProtocol;
  }

  public String getPublicDomain() {
    return publicDomain;
  }

  public void setPublicDomain(String publicDomain) {
    this.publicDomain = publicDomain;
  }

  public String getPrivateDomain() {
    return privateDomain;
  }

  public void setPrivateDomain(String privateDomain) {
    this.privateDomain = privateDomain;
  }
}
