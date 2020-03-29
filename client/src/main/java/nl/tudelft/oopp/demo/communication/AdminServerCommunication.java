package nl.tudelft.oopp.demo.communication;

import java.io.IOException;
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
     * @return true if building successfully added, false otherwise.
     */
    public boolean addBuildingAdmin(int buildingID,
                                    String buildingName,
                                    String buildingOpen,
                                    String buildingClose,
                                    String imageUrl,
                                    int bikeCapacity) {

        String body = "{\"buildingNumber\":\"" + buildingID
                + "\",\"name\":\"" + buildingName
                + "\",\"openingHours\":\"" + buildingOpen
                + "\",\"closingHours\":\"" + buildingClose
                + "\",\"numberOfBikes\":\"" + bikeCapacity
                + "\",\"url\":\"" + imageUrl + "\"}";

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
     * @return true if building successfully edited, false otherwise.
     */
    public boolean editBuildingAdmin(int buildingID,
                                     String buildingName,
                                     String buildingOpen,
                                     String buildingClose,
                                     String imageUrl,
                                     int bikeCapacity) {

        String body = "{\"buildingNumber\":\"" + buildingID
                + "\",\"name\":\"" + buildingName
                + "\",\"openingHours\":\"" + buildingOpen
                + "\",\"closingHours\":\"" + buildingClose
                + "\",\"numberOfBikes\":\"" + bikeCapacity
                + "\",\"url\":\"" + imageUrl + "\"}";

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
    public boolean addRoomAdmin(String roomID,
                                int roomCap,
                                int whiteboards,
                                int tables,
                                int computers,
                                int buildingID,
                                String roomType) {

        String body = "{\"roomId\":\"" + roomID
                + "\",\"chairs\":\"" + roomCap
                + "\",\"whiteboards\":\"" + whiteboards
                + "\",\"tables\":\"" + tables
                + "\",\"computers\":\"" + computers
                + "\",\"type\":\"" + roomType
                + "\",\"associatedBuilding\":\"" + buildingID + "\"}";
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
     * @param chairs    chairs
     * @param buildingID buildingID
     * @param roomType   roomType
     * @return true if room successfully edited, false otherwise.
     */
    public boolean editRoomAdmin(String roomID,
                                 int chairs,
                                 int whiteboards,
                                 int tables,
                                 int computers,
                                 int buildingID,
                                 String roomType,
                                 String oldRoomId) {

        String body = "{\"roomId\":\"" + roomID
                + "\",\"chairs\":\"" + chairs
                + "\",\"whiteboards\":\"" + whiteboards
                + "\",\"tables\":\"" + tables
                + "\",\"computers\":\"" + computers
                + "\",\"type\":\"" + roomType
                + "\",\"associatedBuilding\":\"" + buildingID + "\"}";
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
                System.out.println("Room deleted");
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

    //CRUD for Dishes

    /**
     * Adds the given dish to the database.
     *
     * @param name name
     * @param price price
     * @param vegan vegan
     * @return true if dish successfully added, false otherwise.
     */
    public boolean addDishAdmin(String name, int price, int vegan) {

        String body = "{\"name\":\"" + name
                + "\",\"price\":\"" + price
                + "\",\"vegan\":\"" + vegan + "\"}";

        try {
            boolean bool = super.webClient.post().uri("/addDish")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("Dish added");
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
     * Edits the given dish.
     *
     * @param name name
     * @param price price
     * @param vegan vegan
     * @return true if dish successfully edited, false otherwise.
     */
    public boolean editDishAdmin(String name, int price, int vegan, String oldDishName) {

        String body = "{\"name\":\"" + name
                + "\",\"price\":\"" + price
                + "\",\"vegan\":\"" + vegan + "\"}";

        try {
            boolean bool = super.webClient.post().uri("/editDish?oldDishName=" + oldDishName)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("Dish edited");
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
     * Deletes a dish.
     *
     * @param name name
     * @return true if dish successfully deleted, false otherwise.
     */
    public boolean deleteDishAdmin(String name) throws IOException {
        try {
            boolean bool = this.webClient.get().uri("/deleteDish?name="
                    + name)
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
                System.out.println("Dish deleted");
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

    //CRUD for Menus

    /**
     * Adds the given menu to the database.
     *
     * @param buildingNumber Number of building
     * @param dishName Name of dish
     * @return true if menu successfully added, false otherwise.
     */
    public boolean addMenuAdmin(int buildingNumber, String dishName) {

        String body = "{\"buildingNumber\":\"" + buildingNumber
                + "\",\"dishName\":\"" + dishName + "\"}";

        try {
            boolean bool = super.webClient.post().uri("/addMenu")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("Menu added");
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
     * Edits the given menu in the database.
     *
     * @param buildingNumber Number of building
     * @param newDishName Name of new dish
     * @param oldDishName Name of previous dish
     * @return true if menu successfully edited, false otherwise.
     */
    public boolean editMenuAdmin(int buildingNumber, String newDishName, String oldDishName) {

        String body = "{\"buildingNumber\":\"" + buildingNumber
                + "\",\"dishName\":\"" + newDishName + "\"}";

        try {
            boolean bool = super.webClient.post().uri("/editMenu?oldDishName=" + oldDishName)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(body))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            if (bool) {
                System.out.println("Menu edited");
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
     * Deletes a menu from the building.
     *
     * @param buildingNumber Number of building
     * @param name name of dish
     * @return true if menu successfully deleted, false otherwise.
     */
    public boolean deleteMenuAdmin(int buildingNumber, String name) throws IOException {
        try {
            boolean bool = this.webClient.get().uri("/deleteMenu?buildingNumber="
                    + buildingNumber + "&dishName=" + name)
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
                System.out.println("Menu deleted");
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

    //CRUD for Holidays
}
