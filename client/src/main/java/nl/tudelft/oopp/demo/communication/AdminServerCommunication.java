package nl.tudelft.oopp.demo.communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Rooms;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

public class AdminServerCommunication extends ServerCommunication {

    //CRUD for Buildings

    /**
     * Adds the given building to the database.
     *
     * @param buildingID    buildingID
     * @param buildingName  buildingName
     * @param buildingOpen  building Opening Hours
     * @param buildingClose building Closing Hours
     * @param imageUrl      imageUrl
     * @param bikeCapacity  bikeCapacity
     * @param roomCapacity  roomCapacity
     * @return true if building successfully added, false otherwise.
     */
    public boolean addBuildingAdmin(int buildingID,
                                    String buildingName,
                                    String buildingOpen,
                                    String buildingClose,
                                    String imageUrl,
                                    int bikeCapacity,
                                    int roomCapacity) {

        String body = "{\"buildingNumber\":\"" + buildingID
                + "\",\"name\":\"" + buildingName
                + "\",\"openingHours\":\"" + buildingOpen
                + "\",\"closingHours\":\"" + buildingClose
                + "\",\"numberOfRooms\":\"" + roomCapacity
                + "\",\"numberOfBikes\":\"" + bikeCapacity
                + "\",\"url\":\"" + imageUrl + "\"}";

        System.out.println(body);
        try {
            boolean bool = super.webClient.post().uri("/addBuilding")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("Building added");
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
     * Edits the given building.
     *
     * @param buildingID    buildingID
     * @param buildingName  buildingName
     * @param buildingOpen  building Opening Hours
     * @param buildingClose building Closing Hours
     * @param imageUrl      imageUrl
     * @param bikeCapacity  bikeCapacity
     * @param roomCapacity  roomCapacity
     * @return true if building successfully edited, false otherwise.
     */
    public boolean editBuildingAdmin(int buildingID,
                                    String buildingName,
                                    String buildingOpen,
                                    String buildingClose,
                                    String imageUrl,
                                    int bikeCapacity,
                                    int roomCapacity) {

        String body = "{\"buildingNumber\":\"" + buildingID
                + "\",\"name\":\"" + buildingName
                + "\",\"openingHours\":\"" + buildingOpen
                + "\",\"closingHours\":\"" + buildingClose
                + "\",\"numberOfRooms\":\"" + roomCapacity
                + "\",\"numberOfBikes\":\"" + bikeCapacity
                + "\",\"url\":\"" + imageUrl + "\"}";

        System.out.println(body);
        try {
            boolean bool = super.webClient.post().uri("/editBuilding")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("Building edited");
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
     * Deletes a building.
     *
     * @param buildingNumber buildingID
     * @return true if building successfully removed, false otherwise.
     */
    public boolean deleteBuildingAdmin(int buildingNumber) throws IOException {
        try {
            boolean bool = this.webClient.get().uri("/deleteBuilding?bnr="
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
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("Building deleted");
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

    //CRUD for Rooms

    /**
     * Adds the given room to the database.
     *
     * @param roomID     roomID
     * @param roomCap    roomCap
     * @param buildingID buildingID
     * @param roomType   roomType
     * @return true if room successfully added, false otherwise.
     */
    public boolean addRoomAdmin(String roomID, int roomCap, int buildingID, String roomType) {

        String body = "{\"roomId\":\"" + roomID
                + "\",\"capacity\":\"" + roomCap
                + "\",\"type\":\"" + roomType
                + "\",\"associatedBuilding\":\"" + buildingID + "\"}";
        System.out.println(body);
        try {
            boolean bool = super.webClient.post().uri("/addRoom")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("Room added");
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
     * Edits the given room.
     *
     * @param roomID     roomID
     * @param roomCap    roomCap
     * @param buildingID buildingID
     * @param roomType   roomType
     * @return true if building successfully edited, false otherwise.
     */
    public boolean editRoomAdmin(String roomID,
                                 int roomCap,
                                 int buildingID,
                                 String roomType,
                                 String oldRoomId) {

        String body = "{\"roomId\":\"" + roomID
                + "\",\"capacity\":\"" + roomCap
                + "\",\"type\":\"" + roomType
                + "\",\"associatedBuilding\":\"" + buildingID + "\"}";
        System.out.println(body);
        try {
            boolean bool = super.webClient.post().uri("/editRoom?oldRoomId=" + oldRoomId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("Room edited");
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
     * Deletes a room.
     *
     * @param roomId roomID
     * @return true if room successfully deleted, false otherwise.
     */
    public boolean deleteRoomAdmin(String roomId) throws IOException {
        try {
            boolean bool = this.webClient.get().uri("/deleteRoom?roomId="
                    + roomId)
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
                System.out.println("Building deleted");
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

}
