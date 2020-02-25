package nl.tudelft.oopp.demo.communication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    /**
     * Retrieves a quote from the server.
     * @return the body of a get request to the server.
     * @throws Exception if communication with the server fails.
     */
    public static String getQuote() {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/quote")).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }

    public static String getSth(String user, String pass) throws IOException, InterruptedException {

        Map values = new HashMap<String, String>() {{
            put("name", "John Doe");
            put ("occupation", "gardener");
        }};

        String requestBody = "user: " + user + "pas: " + pass;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/login"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

//        System.out.println(response.body());
//         HttpRequest request1 = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/login")).build();
//         HttpResponse<String> response1 = null;
//
//        try {
//            response1 = client.send(request, HttpResponse.BodyHandlers.ofString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Communication with server failed";
//        }
//        if (response1.statusCode() != 200) {
//            System.out.println("Status: " + response1.statusCode());
//        }
        return response.body();
    }

}
