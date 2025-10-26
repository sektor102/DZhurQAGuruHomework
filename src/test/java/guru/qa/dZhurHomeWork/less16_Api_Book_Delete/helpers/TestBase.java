package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.ApiSpec.requestSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class TestBase {
    protected String token;
    protected String isbn;
    protected String userId;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://demoqa.com";
    }

    @BeforeEach
        void loginDemoQaAndAddBook() {
        Response response = given(requestSpec)
                .log().all()
                .contentType(JSON)
                .body("{\"password\": \"2Baikal123&\", \"userName\": \"2Baikal\"}")

                .when()
                .post("/Account/v1/login")

                .then()
                .statusCode(200)
                .body("userId", notNullValue())
                .extract()
                .response();

        token = response.path("token");
        userId = response.path("userId");

        System.out.println("Успешный залогин и сохранили userId = " + token);

        isbn = given(requestSpec)
                .log().all()
                .when()
                .get("/BookStore/v1/Books")

                .then()
                .statusCode(200)
                .extract()
                .path("books[0].isbn");

        System.out.println("Успешный вытащили айди книги isbn = " + isbn);

        given(requestSpec)
                .log().all()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .body("{\"userId\": \"" + userId + "\", \"collectionOfIsbns\": [{\"isbn\": \"" + isbn + "\"}]}")

                .when()
                .post("/BookStore/v1/Books")
                .then()
                .statusCode(201);
    }

}
