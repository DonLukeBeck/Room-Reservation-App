package nl.tudelft.oopp.demo.communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import nl.tudelft.oopp.demo.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ServerCommunication {

    // private static HttpClient client = HttpClient.newBuilder().build();

    private String baseURL = "http://localhost:8080";
    private WebClient webClient = WebClient.create(baseURL);

    /**
     * Create new user.
     * @param user net_id
     * @param pass password from the user
     * @return true if new user is created, false if not.
     */
    public boolean signUp(String user, String pass, String role) {

        String body = "{\"netid\":\"" + user + "\",\"password\":\"" + pass + "\",\"role\":\"" + role + "\"}";

        try {
            boolean bool = this.webClient.post().uri("/registerNewUser")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("User registered");
                return true;
            } else {
                System.out.println("Authentication failed");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String logIn(String user, String pass) {

        String body = "{\"netid\":\"" + user + "\",\"password\":\"" + pass + "\"}";
        try {
            String str = this.webClient.post().uri("/loginUser")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            if (!str.isEmpty()) {
                System.out.println("User Logged in");
                System.out.println(str);
                return str;
            } else {
                System.out.println("Authentication failed");
                return "";
            }
        } catch (Exception e) {
            System.out.println("Authentication failed");
            return "";
        }
    }


    /**
     * Retrieves building list from the server.
     * @return the body of a get request to the server.
     * @throws Exception if communication with the server fails.
     */
    public List<Buildings> getBuildings() throws IOException {
        String jsonString = this.webClient.get().uri("/allBuildings")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    System.out.println("4xx error");
                    return Mono.error(new RuntimeException("4xx"));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    System.out.println("5xx error");
                    return Mono.error(new RuntimeException("5xx"));
                })
                .bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        List<Buildings> buildingsJsonList = mapper.readValue(jsonString, new com.fasterxml.jackson.core.type.TypeReference<List<Buildings>>() {});

        return buildingsJsonList;
    }

    /**
     * Retrieves rooms list from the server.
     * @return the body of a get request to the server.
     * @throws Exception if communication with the server fails.
     */
    public List<Rooms> getRooms() throws IOException {
        String jsonString = this.webClient.get().uri("/allRooms")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    System.out.println("4xx error");
                    return Mono.error(new RuntimeException("4xx"));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    System.out.println("5xx error");
                    return Mono.error(new RuntimeException("5xx"));
                })
                .bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        List<Rooms> roomsJsonList = mapper.readValue(jsonString, new com.fasterxml.jackson.core.type.TypeReference<List<Rooms>>() {});

        return roomsJsonList;
    }

    /**
     * Retrieves reservations list from the server.
     * @return the body of a get request to the server.
     * @throws Exception if communication with the server fails.
     */
    public List<Reservations> getReservations() throws IOException {
        String jsonString = this.webClient.get().uri("/allReservations")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    System.out.println("4xx error");
                    return Mono.error(new RuntimeException("4xx"));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    System.out.println("5xx error");
                    return Mono.error(new RuntimeException("5xx"));
                })
                .bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        List<Reservations> reservationsJsonList = mapper.readValue(jsonString, new com.fasterxml.jackson.core.type.TypeReference<List<Reservations>>() {
        });

        return reservationsJsonList;
    }

    /**
     * Retrieves bikes list from the server.
     * @return the body of a get request to the server.
     * @throws Exception if communication with the server fails.
     */
    public List<Bikes> getBikes() throws IOException {
        String jsonString = this.webClient.get().uri("/allBikes")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    System.out.println("4xx error");
                    return Mono.error(new RuntimeException("4xx"));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    System.out.println("5xx error");
                    return Mono.error(new RuntimeException("5xx"));
                })
                .bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        List<Bikes> bikesJsonList = mapper.readValue(jsonString, new com.fasterxml.jackson.core.type.TypeReference<List<Bikes>>() {
        });

        return bikesJsonList;
    }

    /**
     * Retrieves menus list from the server.
     * @return the body of a get request to the server.
     * @throws Exception if communication with the server fails.
     */
    public List<Menus> getMenus() throws IOException {
        String jsonString = this.webClient.get().uri("/allMenus")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    System.out.println("4xx error");
                    return Mono.error(new RuntimeException("4xx"));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    System.out.println("5xx error");
                    return Mono.error(new RuntimeException("5xx"));
                })
                .bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        List<Menus> menusJsonList = mapper.readValue(jsonString, new com.fasterxml.jackson.core.type.TypeReference<List<Menus>>() {
        });

        return menusJsonList;
    }

    /**
     * Retrieves dishes list from the server.
     * @return the body of a get request to the server.
     * @throws Exception if communication with the server fails.
     */
    public List<Dishes> getDishes() throws IOException {
        String jsonString = this.webClient.get().uri("/allDishes")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    System.out.println("4xx error");
                    return Mono.error(new RuntimeException("4xx"));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    System.out.println("5xx error");
                    return Mono.error(new RuntimeException("5xx"));
                })
                .bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        List<Dishes> dishesJsonList = mapper.readValue(jsonString, new com.fasterxml.jackson.core.type.TypeReference<List<Dishes>>() {
        });

        return dishesJsonList;
    }

}
