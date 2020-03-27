package nl.tudelft.oopp.demo.communication;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

public class AdminServerCommunication extends ServerCommunication {

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
     * Adds the given room to the database.
     *
     * @param roomID     roomID
     * @param roomCap    roomCap
     * @param buildingID buildingID
     * @param roomType   roomType
     * @return true if building successfully added, false otherwise.
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
    public boolean editRoomAdmin(String roomID, int roomCap, int buildingID, String roomType) {

        String body = "{\"roomId\":\"" + roomID
                + "\",\"capacity\":\"" + roomCap
                + "\",\"type\":\"" + roomType
                + "\",\"associatedBuilding\":\"" + buildingID + "\"}";
        System.out.println(body);
        try {
            boolean bool = super.webClient.post().uri("/editRoom")
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



}
