package nl.tudelft.oopp.demo.communication;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import java.net.http.HttpClient;


public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    private static String baseURL = "http://localhost:8080";
    private static WebClient webClient = WebClient.create(baseURL);

    public static boolean login(String user, String pass) {

        String body = "{\"netID\":\"" + user + "\",\"password\":\"" + pass + "\"}";
        try {
            boolean bool = webClient.post().uri("/registerNewUser")
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
}
