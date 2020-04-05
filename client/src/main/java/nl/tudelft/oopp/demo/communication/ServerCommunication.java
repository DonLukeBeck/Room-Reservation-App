package nl.tudelft.oopp.demo.communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import nl.tudelft.oopp.demo.entities.Buildings;
import nl.tudelft.oopp.demo.entities.Dishes;
import nl.tudelft.oopp.demo.entities.Holidays;
import nl.tudelft.oopp.demo.entities.Menus;
import nl.tudelft.oopp.demo.entities.Reservations;
import nl.tudelft.oopp.demo.entities.Rooms;
import nl.tudelft.oopp.demo.entities.UserEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ServerCommunication {

    private String baseUrl = "http://localhost:8080";
    protected WebClient webClient = WebClient.create(baseUrl);

    //Get methods for all entities in database

    /**
     * Retrieves building list from the server.
     *
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
        List<Buildings> buildingsJsonList = mapper
                .readValue(jsonString, new com.fasterxml
                        .jackson
                        .core
                        .type
                        .TypeReference<List<Buildings>>() {
                });

        return buildingsJsonList;
    }

    /**
     * Retrieves building from the server.
     *
     * @return the body of a get request to the server.
     * @throws Exception if communication with the server fails.
     */
    public Buildings getBuildingByName(int buildingNumber) throws IOException {
        String jsonString = this.webClient.get()
                .uri("/buildingByName?buildingNumber=" + buildingNumber)
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
        Buildings buildingsJsonList = mapper
                .readValue(jsonString, new com.fasterxml
                        .jackson
                        .core
                        .type
                        .TypeReference<Buildings>() {
                });

        return buildingsJsonList;
    }

    /**
     * Retrieves rooms list from the server.
     *
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
        List<Rooms> roomsJsonList = mapper
                .readValue(jsonString, new com
                        .fasterxml
                        .jackson
                        .core
                        .type
                        .TypeReference<List<Rooms>>() {
                });


        return roomsJsonList;
    }

    /**
     * Retrieves the rooms from a specified building.
     *
     * @return the body of a get request to the server.
     * @throws Exception if communication with the server fails.
     */
    public List<Rooms> getRoomsByBuilding(int buildingNumber) throws IOException {
        String jsonString = this.webClient.get().uri("/roomsByBuilding?bnr="
                + buildingNumber)
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
        List<Rooms> roomsJsonList = mapper
                .readValue(jsonString, new com
                        .fasterxml
                        .jackson
                        .core
                        .type
                        .TypeReference<List<Rooms>>() {
                });

        return roomsJsonList;
    }


    /**
     * Retrieves reservations list from the server.
     *
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
        List<Reservations> reservationsJsonList = mapper
                .readValue(jsonString, new com
                        .fasterxml
                        .jackson
                        .core
                        .type
                        .TypeReference<List<Reservations>>() {
                });


        return reservationsJsonList;
    }

    /**
     * Deletes a reservation.
     *
     * @param id : id of the reservation.
     * @return true if the deletion was successful.
     * @throws IOException
     */
    public boolean deleteReservation(String id) throws IOException {

        try {
            boolean bool = this.webClient.get().uri("/deleteReservation?id="
                    + id)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, response -> {
                        System.out.println("4xx error");
                        return Mono.error(new RuntimeException("4xx"));
                    })
                    .onStatus(HttpStatus::is5xxServerError, response -> {
                        System.out.println("5xx error");
                        return Mono.error(new RuntimeException("5xx"));
                    })
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("Reservation " + id + " deleted");
                return true;
            } else {
                System.out.println("failed");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Deletes a personal event.
     *
     * @param id : id of the UserEvent.
     * @return true if the deletion was successful.
     * @throws IOException
     */
    public boolean deleteUserEvent(String id) throws IOException {

        try {
            boolean bool = this.webClient.get().uri("/deleteUserEvent?id="
                    + id)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, response -> {
                        System.out.println("4xx error");
                        return Mono.error(new RuntimeException("4xx"));
                    })
                    .onStatus(HttpStatus::is5xxServerError, response -> {
                        System.out.println("5xx error");
                        return Mono.error(new RuntimeException("5xx"));
                    })
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("User event " + id + " deleted");
                return true;
            } else {
                System.out.println("failed");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to find user events.
     * @return User events as a Json list
     * @throws IOException
     */
    public List<UserEvent> getUserEvents() throws IOException {
        String jsonString = this.webClient.get().uri("/allPersonalEvents")
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
        List<UserEvent> userEventsJsonList = mapper
                .readValue(jsonString, new com
                        .fasterxml
                        .jackson
                        .core
                        .type
                        .TypeReference<List<UserEvent>>() {
                });


        return userEventsJsonList;
    }


    /**
     * Retrieves menus list from the server.
     *
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
        List<Menus> menusJsonList = mapper
                .readValue(jsonString, new com
                        .fasterxml
                        .jackson
                        .core
                        .type
                        .TypeReference<List<Menus>>() {
                });


        return menusJsonList;
    }

    /**
     * Retrieves the dishes from a specified building.
     *
     * @return the body of a get request to the server.
     * @throws Exception if communication with the server fails.
     */
    public List<Dishes> getMenuByBuilding(int buildingNumber) throws IOException {
        String jsonString = this.webClient.get().uri("/menusByBuilding?bnr="
                + buildingNumber)
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
        List<Dishes> dishesJsonList = mapper
                .readValue(jsonString, new com
                        .fasterxml
                        .jackson
                        .core
                        .type
                        .TypeReference<List<Dishes>>() {
                });


        return dishesJsonList;
    }

    /**
     * Retrieves holidays list from the server.
     *
     * @return the body of a get request to the server.
     * @throws Exception if communication with the server fails.
     */
    public List<Holidays> getHolidays() throws IOException {
        String jsonString = this.webClient.get().uri("/allHolidays")
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
        List<Holidays> holidaysJsonList = mapper
                .readValue(jsonString, new com
                        .fasterxml
                        .jackson
                        .core
                        .type
                        .TypeReference<List<Holidays>>() {
                });

        return holidaysJsonList;
    }

    /**
     * Retrieves dishes list from the server.
     *
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
        List<Dishes> dishesJsonList = mapper
                .readValue(jsonString, new com
                        .fasterxml
                        .jackson
                        .core
                        .type
                        .TypeReference<List<Dishes>>() {
                });


        return dishesJsonList;
    }

    /**
     * Checks whether bikes are available in building.
     *
     * @return true if there are available bikes, false otherwise
     * @throws Exception if communication with the server fails.
     */
    public boolean getAvailableBikes(int buildingNumber, Date date, Time timeslot) {
        boolean bool = this.webClient.get()
                .uri("/getAvailableBikes?buildingNumber=" + buildingNumber
                        + " &date=" + date
                        + "&timeslot=" + timeslot)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    System.out.println("4xx error");
                    return Mono.error(new RuntimeException("4xx"));
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    System.out.println("5xx error");
                    return Mono.error(new RuntimeException("5xx"));
                })
                .bodyToMono(boolean.class)
                .block();
        return bool;
    }

}
