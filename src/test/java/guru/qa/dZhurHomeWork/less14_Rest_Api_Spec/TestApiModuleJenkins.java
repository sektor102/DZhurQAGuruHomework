package guru.qa.dZhurHomeWork.less14_Rest_Api_Spec;

import guru.qa.dZhurHomeWork.less14_Rest_Api_Spec.module.lombok.AddUserLombok;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static guru.qa.dZhurHomeWork.less14_Rest_Api_Spec.specs.ApiSpec.loginRequestSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



import lombok.Data;

@Tag("ApiJenkins")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Data
public class TestApiModuleJenkins {
    Faker faker = new Faker();

    static Integer userId;
    static Integer userIdForTest;
    private String name;
    private String gender;
    private String email;
    private Integer id;
    private String message;

    private AddUserLombok createTestUser() {
        AddUserLombok user = new AddUserLombok();
        user.setName(faker.name().firstName());
        user.setGender("M");
        user.setEmail(faker.internet().emailAddress());

        int id = given(loginRequestSpec)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract().path("id");

        user.setId(id);

        return user;
    }


    @Test
    void postCreateUser() {
        AddUserLombok user = new AddUserLombok();
        user.setName(faker.name().firstName());
        user.setGender("M");
        user.setEmail(faker.internet().emailAddress());

        AddUserLombok response = step("Создаём пользователя", () ->
                given(loginRequestSpec)
                        .log().all()
                        .contentType(JSON)
                        .body(user)
                        .when()
                        .post()
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(201)
                        .extract().as(AddUserLombok.class)
        );

        step("Проверяем, что id вернулся и сообщение корректное", () -> {
            assertNotNull(response.getId(), "ID не должен быть null");
            assertEquals("User created", response.getMessage(), "Неверное сообщение в ответе");
        });
    }

    @Test
    void getCheckUser() {
        AddUserLombok created = createTestUser();

        given(loginRequestSpec)
                .when()
                .get("/{id}", created.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(created.getId()))
                .body("name", equalTo(created.getName()));
    }


    @Test
    void putUser() {
        AddUserLombok created = createTestUser();

        AddUserLombok updatedUser = new AddUserLombok();
        updatedUser.setName(faker.name().firstName());
        updatedUser.setGender("F");
        updatedUser.setEmail(faker.internet().emailAddress());

        given(loginRequestSpec)
                .body(updatedUser)
                .when()
                .put("/{id}", created.getId())
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(created.getId()))
                .body("message", equalTo("User fully updated"));

        given(loginRequestSpec)
                .when()
                .get("/{id}", created.getId())
                .then()
                .statusCode(200)
                .body("name", equalTo(updatedUser.getName()))
                .body("email", equalTo(updatedUser.getEmail()))
                .body("gender", equalTo(updatedUser.getGender()));
    }

    @Test
    void patchUser() {
        AddUserLombok created = createTestUser();

        AddUserLombok updatedUser = new AddUserLombok();
        updatedUser.setName(faker.name().firstName());

        given(loginRequestSpec)
                .body(updatedUser)
                .when()
                .patch("/{id}", created.getId())
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(created.getId()))
                .body("message", equalTo("User partially updated"));

        given(loginRequestSpec)
                .when()
                .get("/{id}", created.getId())
                .then()
                .statusCode(200)
                .body("name", equalTo(updatedUser.getName()));
    }

    @Test
    void deleteUser() {
        AddUserLombok created = createTestUser();

        given(loginRequestSpec)
                .when()
                .delete("/{id}", created.getId())
                .then()
                .log().status()
                .statusCode(204);

        given(loginRequestSpec)
                .when()
                .get("/{id}", created.getId())
                .then()
                .statusCode(404);
    }

}
