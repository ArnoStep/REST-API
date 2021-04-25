package petsRestAssuredTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pet.Category;
import pet.Pet;
import pet.TagsItem;

import java.util.ArrayList;
import java.util.List;

public class PetsRestAssuredTests {
    private static ObjectMapper objectMapper;

    private static Pet pet;

    private static Category category;

    private static TagsItem tagsItem;

    private static List<TagsItem> tagsItemList;

    private String jsonPet;

    @BeforeAll
    public static void baseURL() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        pet = new Pet();
        category = new Category();
        tagsItem = new TagsItem();
        tagsItemList = new ArrayList<>();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void postRequest200() {
        pet.setId(2000);
        pet.setName("doggie");
        category.setId(2010);
        category.setName("enim cillum nulla fugiat");
        pet.setCategory(category);
        pet.setStatus("pending");
        tagsItem.setId(2100);
        tagsItem.setName("sed adipisicing officia");
        tagsItemList.add(tagsItem);
        tagsItem.setId(2110);
        tagsItem.setName("nostrud");
        tagsItemList.add(tagsItem);
        pet.setTags(tagsItemList);
        try {
            jsonPet = objectMapper.writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Pet postPet = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonPet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .and()
                .extract().as(Pet.class);
        Assertions.assertEquals(pet, postPet);

        Pet getPet = RestAssured.given()
                .when()
                .get("/pet/2000")
                .then()
                .statusCode(200)
                .extract().as(Pet.class);
        Assertions.assertEquals(pet, getPet);
    }

    @Test
    public void getRequest200() {
        pet.setId(3000);
        pet.setName("doggie");
        category.setId(3010);
        category.setName("enim cillum nulla fugiat");
        pet.setCategory(category);
        pet.setStatus("pending");
        tagsItem.setId(3100);
        tagsItem.setName("sed adipisicing officia");
        tagsItemList.add(tagsItem);
        tagsItem.setId(3200);
        tagsItem.setName("nostrud");
        tagsItemList.add(tagsItem);
        pet.setTags(tagsItemList);
        try {
            jsonPet = objectMapper.writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Pet postPet = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonPet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .and()
                .extract().as(Pet.class);
        Assertions.assertEquals(pet, postPet);

        Pet getPet = RestAssured.given()
                .when()
                .get("/pet/3000")
                .then()
                .statusCode(200)
                .extract().as(Pet.class);
        Assertions.assertEquals(pet, getPet);
    }

    @Test
    public void getRequest404() {
        pet.setId(3001);
        pet.setName("doggie");
        category.setId(3011);
        category.setName("enim cillum nulla fugiat");
        pet.setCategory(category);
        pet.setStatus("pending");
        tagsItem.setId(3101);
        tagsItem.setName("sed adipisicing officia");
        tagsItemList.add(tagsItem);
        tagsItem.setId(3201);
        tagsItem.setName("nostrud");
        tagsItemList.add(tagsItem);
        pet.setTags(tagsItemList);
        try {
            jsonPet = objectMapper.writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Pet postPet = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonPet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .and()
                .extract().as(Pet.class);
        Assertions.assertEquals(pet, postPet);

        RestAssured.given()
                .when()
                .delete("/pet/3001")
                .then()
                .statusCode(200);

        RestAssured.given()
                .when()
                .get("/pet/3001")
                .then()
                .statusCode(404);
    }

    @Test
    public void putRequest200() {
        pet.setId(4000);
        pet.setName("doggie");
        category.setId(4010);
        category.setName("enim cillum nulla fugiat");
        pet.setCategory(category);
        pet.setStatus("pending");
        tagsItem.setId(4100);
        tagsItem.setName("sed adipisicing officia");
        tagsItemList.add(tagsItem);
        tagsItem.setId(4200);
        tagsItem.setName("nostrud");
        tagsItemList.add(tagsItem);
        pet.setTags(tagsItemList);
        try {
            jsonPet = objectMapper.writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Pet postPet = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonPet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .and()
                .extract().as(Pet.class);
        Assertions.assertEquals(pet, postPet);

        RestAssured.given()
                .when()
                .get("/pet/4000")
                .then()
                .statusCode(200);

        pet.setName("doggie 2");
        category.setId(4010);
        category.setName("enim cillum nulla fugiat 2");
        pet.setCategory(category);
        pet.setStatus("pending");
        tagsItem.setId(4100);
        tagsItem.setName("sed adipisicing officia 2");
        tagsItemList.add(tagsItem);
        tagsItem.setId(4200);
        tagsItem.setName("nostrud 2");
        tagsItemList.add(tagsItem);
        pet.setTags(tagsItemList);
        try {
            jsonPet = objectMapper.writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonPet)
                .when()
                .put("/pet")
                .then()
                .statusCode(200);

        Pet putPet = RestAssured.given()
                .when()
                .get("/pet/4000")
                .then()
                .statusCode(200)
                .extract().as(Pet.class);
        Assertions.assertEquals(pet, putPet);
    }

    @Test
    public void putRequest404() {
        pet.setId(4001);
        pet.setName("doggie");
        category.setId(4011);
        category.setName("enim cillum nulla fugiat");
        pet.setCategory(category);
        pet.setStatus("pending");
        tagsItem.setId(4101);
        tagsItem.setName("sed adipisicing officia");
        tagsItemList.add(tagsItem);
        tagsItem.setId(4201);
        tagsItem.setName("nostrud");
        tagsItemList.add(tagsItem);
        pet.setTags(tagsItemList);
        try {
            jsonPet = objectMapper.writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Pet postPet = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonPet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .and()
                .extract().as(Pet.class);
        Assertions.assertEquals(pet, postPet);

        RestAssured.given()
                .when()
                .delete("/pet/4001")
                .then()
                .statusCode(200);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonPet)
                .when()
                .put("/pet/4001")
                .then()
                .statusCode(404);
    }

    @Test
    public void deleteRequest200() {
        pet.setId(5000);
        pet.setName("doggie");
        category.setId(5010);
        category.setName("enim cillum nulla fugiat");
        pet.setCategory(category);
        pet.setStatus("pending");
        tagsItem.setId(5100);
        tagsItem.setName("sed adipisicing officia");
        tagsItemList.add(tagsItem);
        tagsItem.setId(5110);
        tagsItem.setName("nostrud");
        tagsItemList.add(tagsItem);
        pet.setTags(tagsItemList);
        try {
            jsonPet = objectMapper.writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Pet postPet = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonPet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .and()
                .extract().as(Pet.class);
        Assertions.assertEquals(pet, postPet);

        Pet getPet = RestAssured.given()
                .when()
                .get("/pet/5000")
                .then()
                .statusCode(200)
                .extract().as(Pet.class);
        Assertions.assertEquals(pet, getPet);

        RestAssured.given()
                .when()
                .delete("/pet/5000")
                .then()
                .statusCode(200);

        RestAssured.given()
                .when()
                .get("/pet/5000")
                .then()
                .statusCode(404);
    }

    @Test
    public void deleteRequest404() {
        pet.setId(5001);
        pet.setName("doggie");
        category.setId(5011);
        category.setName("enim cillum nulla fugiat");
        pet.setCategory(category);
        pet.setStatus("pending");
        tagsItem.setId(5101);
        tagsItem.setName("sed adipisicing officia");
        tagsItemList.add(tagsItem);
        tagsItem.setId(5201);
        tagsItem.setName("nostrud");
        tagsItemList.add(tagsItem);
        pet.setTags(tagsItemList);
        try {
            jsonPet = objectMapper.writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        RestAssured.given()
                .body(jsonPet)
                .when()
                .delete("/pet/5001");

        RestAssured.given()
                .body(jsonPet)
                .when()
                .delete("/pet/5001")
                .then()
                .statusCode(404);
    }

    @AfterAll
    public static void deleteAll() {
        RestAssured.delete("/pet/2000");
        RestAssured.delete("/pet/3000");
        RestAssured.delete("/pet/3001");
        RestAssured.delete("/pet/4000");
        RestAssured.delete("/pet/4001");
        RestAssured.delete("/pet/5000");
        RestAssured.delete("/pet/5001");
    }
}
