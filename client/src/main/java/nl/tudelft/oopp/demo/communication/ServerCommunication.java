package nl.tudelft.oopp.demo.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.entities.Rooms;
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
     * @param user NetId
     * @param pass Password from the user
     * @return true if new user is created, false if not.
     */
    public boolean signUp(String user, String pass, String role) {

        String body = "{\"netID\":\"" + user + "\",\"password\":\"" + pass + "\",\"role\":\"" + role + "\"}";
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

    public boolean logIn(String user, String pass) {

        String body = "{\"netID\":\"" + user + "\",\"password\":\"" + pass + "\"}";
        try {
            boolean bool = this.webClient.post().uri("/logInNewUser")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("User Logged in");
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

}
