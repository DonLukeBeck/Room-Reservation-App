package nl.tudelft.oopp.demo.communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Rooms;
import nl.tudelft.oopp.demo.entities.UserEvent;
import nl.tudelft.oopp.demo.entities.Users;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

public class UserServerCommunication extends ServerCommunication {

    /**
     * Create new user.
     *
     * @param user net_id
     * @param pass password from the user
     * @return true if new user is created, false if not.
     */
    public boolean signUp(String user, String pass, String role) {

        String body = "{\"netid\":\"" + user + "\",\"password\":\""
                + pass + "\",\"role\":\"" + role + "\"}";


        try {
            boolean bool = super.webClient.post().uri("/registerNewUser")
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

    /**
     * Sends log in credentials to the server.
     *
     * @param user net_id
     * @param pass password from the user
     * @return true if the user is in the database, false if not.
     */
    public Users logIn(String user, String pass) {

        String body = "{\"netid\":\"" + user + "\",\"password\":\"" + pass + "\"}";
        Users userLogged = new Users();
        try {
            String userJson = super.webClient.post().uri("/loginUser")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            if (!userJson.isEmpty()) {
                System.out.println("User Logged in");
                System.out.println(userJson);
                ObjectMapper mapper = new ObjectMapper();
                userLogged = mapper.readValue(userJson, Users.class);
                System.out.println(userLogged.getNetid());
                System.out.println(userLogged.getRole());
                Users.user = userLogged;
                return userLogged;
            } else {
                System.out.println("Authentication failed");
                System.out.println(userLogged.getNetid());
                System.out.println(userLogged.getRole());
                return userLogged;
            }
        } catch (Exception e) {
            System.out.println("Authentication failed");
            System.out.println(userLogged.getNetid());
            System.out.println(userLogged.getRole());
            return userLogged;
        }
    }

    /**
     * Create new user room reservation.
     *
     * @param userReserving    The user reserving
     * @param timeSlot         The time slot
     * @param date             Date of reseervation
     * @param buildingReserved Building reserved in
     * @param room             The room reserved
     * @return true if reservation is created, false if not.
     */
    public boolean roomReservation(String userReserving,
                                   String timeSlot,
                                   String date,
                                   int buildingReserved,
                                   String room) {

        System.out.println(userReserving);
        System.out.println(room);
        String body = "{\"userReserving\":\""
                + userReserving + "\",\"timeslot\":\""
                + timeSlot + "\",\"date\":\"" + date + "\",\"buildingReserved\":\""
                + buildingReserved + "\",\"roomReserved\":\"" + room + "\"}";

        try {
            boolean bool = this.webClient.post().uri("/postRoomReservation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("Room reserved");
                return true;
            } else {
                System.out.println("Reservation failed");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Create new user bike reservation.
     *
     * @param userReserving    The user reserving
     * @param timeSlot         The time slot
     * @param date             Date of reseervation
     * @param buildingReserved Building reserved in
     * @return true if reservation is created, false if not.
     */
    public boolean bikeReservation(String userReserving,
                                   String timeSlot,
                                   String date,
                                   int buildingReserved) {

        System.out.println(userReserving);
        String body = "{\"userReserving\":\""
                + userReserving + "\",\"timeslot\":\""
                + timeSlot + "\",\"date\":\"" + date + "\",\"buildingReserved\":\""
                + buildingReserved + "\"}";

        try {
            boolean bool = this.webClient.post().uri("/postBikeReservation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("Bike reserved");
                return true;
            } else {
                System.out.println("Reservation failed");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Create new user food reservation.
     *
     * @param userReserving    The user reserving
     * @param timeSlot         The time slot
     * @param date             Date of reseervation
     * @param buildingReserved Building reserved in
     * @param dishOrdered      The dish ordered
     * @return true if reservation is created, false if not.
     */
    public boolean foodReservation(String userReserving,
                                   String timeSlot,
                                   String date,
                                   int buildingReserved,
                                   String dishOrdered) {

        System.out.println(userReserving);
        String body = "{\"userReserving\":\""
                + userReserving + "\",\"timeslot\":\""
                + timeSlot + "\",\"date\":\"" + date + "\",\"buildingReserved\":\""
                + buildingReserved + "\",\"dishOrdered\":\"" + dishOrdered + "\"}";

        try {
            boolean bool = this.webClient.post().uri("/postFoodReservation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("Food reserved");
                return true;
            } else {
                System.out.println("Reservation failed");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Method to add a user event.
     * @param user User associated with the event
     * @param timeSlot Timeslot of the event
     * @param date Date of the event
     * @param description Description of the event
     * @return True if event added successfully, false otherwise
     */
    public UserEvent addUserEvent(String user,
                                   String timeSlot,
                                   String date,
                                   String description) {
        String body = "{\"user\":\""
                + user + "\",\"time\":\""
                + timeSlot + "\",\"date\":\"" + date + "\",\"description\":\""
                + description + "\"}";

        try {
            String jsonString = this.webClient.post().uri("/postUserEvent")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ObjectMapper mapper = new ObjectMapper();
            UserEvent newUserEvent = mapper
                    .readValue(jsonString, new com
                            .fasterxml
                            .jackson
                            .core
                            .type
                            .TypeReference<UserEvent>() {
                    });

            return newUserEvent;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}