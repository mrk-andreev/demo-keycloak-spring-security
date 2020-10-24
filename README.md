# Spring Security + Keycloak

```
- keycloak
- application
- client
- gateway
``` 

- **Keycloak** contains example in-docker configuration. 
- **Application** contains spring-boot application with keycloak as sso center.
- **Client** contains example application that receives `accessToken` from keycloak and use it for access to Application.
- **Gateway** revers proxy for application.

Use `docker-compose` for start your case:
```
docker-compose -f <>.yaml up --build
```

## Use cases

```
                 -----> Keycloak (Recive JWT Token)
                 |
Client  ----------
                 |
                 -----> Application (Sent request with JWT Token)

```

### In the wild

```
docker-compose -f docker-compose-keycloak.yml up --build
```

All applications in the same network without revers proxy. Use `docker-compose-keycloak.yml` for start keycloak. Then run `application` and `client`.
- `Keycloak` as standalone application
- `Application` as standalone application
- `Client` as standalone application

### Behind revers proxy

```
docker-compose -f docker-compose-e2e.yml up --build
```

`Keycloak+Application+Client` inside docker network and one additional install of `Client` started outside behind revers proxy (`gateway`). Use `docker-compose-e2e.yml` for start all in docker services. 

- `Keycloak` in docker network
- `Application` in docker network
- `Client` in docker network
- `Gateway` in docker network and uses as revers proxy (port 8080)
- `Client` outside docker network. Start this service manually.

```
                          -----> Keycloak (Recive JWT Token)  <----------------------
                          |                                                         |
Client -----> Gateway -----                                                         |
                          |                                                         |
                          -----> Application (Sent request with JWT Token) <---------
                                                                                    |
                                 Client  -------------------------------------------- 

```

## Contributors

Thanks to @s.grishaev for help. 
