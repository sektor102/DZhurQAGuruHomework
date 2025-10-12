package guru.qa.dZhurHomeWork.less13_Rest_Assured;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

@Tag("Api")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApi {

    static Integer userId;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://176.120.67.49:8000";
    }

    @Test
    @Order(1)
    void postCreateUser() {
        userId = given()
                .log().all()
                .contentType(JSON)
                .body("{\"name\": \"Dmitry\", \"gender\": \"M\", \"email\": \"test@test.com\"}")

                .when()
                .post("/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("id", notNullValue())
                .extract()
                .path("id");

                System.out.println("Создан пользователь с id = " + userId);
        }

    @Test
    @Order(2)
    void getCheckUser() {
        given()
                .log().all()
                .contentType(JSON)

                .when()
                .get("/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("[0].id", is(userId))
                .body("[0].email", is("test@test.com"))
                .body("[0].gender", is("M"))
                .body("[0].name", is("Dmitry"));
    }

    @Test
    @Order(3)
    void putUser() {
        given()
                .log().all()
                .contentType(JSON)
                .body("{\"name\": \"Dante\", \"gender\": \"X\", \"email\": \"HELL@HELLS.com\"}")

                .when()
                .put("/users/" + userId)

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(userId))
                .body("message", is("User fully updated"));
    }

    @Test
    @Order(4)
    void patchUser() {
        given()
                .log().all()
                .contentType(JSON)
                .body("{\"email\": \"HELL@HELLS.HELLS\"}")

                .when()
                .patch("/users/" + userId)

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(userId))
                .body("message", is("User partially updated"));
    }

    @Test
    @Order(5)
    void deleteUser() {
        given()
                .log().all()
                .contentType(JSON)

                .when()
                .delete("/users/" + userId)

                .then()
                .log().status()
                .statusCode(204);
    }



}
