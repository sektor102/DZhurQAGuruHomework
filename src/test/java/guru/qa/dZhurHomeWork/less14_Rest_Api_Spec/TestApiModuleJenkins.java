package guru.qa.dZhurHomeWork.less14_Rest_Api_Spec;

import guru.qa.dZhurHomeWork.less14_Rest_Api_Spec.models.AddUserModel;
import guru.qa.dZhurHomeWork.less14_Rest_Api_Spec.specs.BaseSpecs;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static guru.qa.dZhurHomeWork.less14_Rest_Api_Spec.specs.BaseSpecs.loginRequestSpec;
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

    private AddUserModel createTestUser() {
        AddUserModel user = new AddUserModel();
        user.setName(faker.name().firstName());
        user.setGender("M");
        user.setEmail(faker.internet().emailAddress());

        AddUserModel response = given(loginRequestSpec)
                .body(user)
                .when()
                .post()
                .then()
                .spec(BaseSpecs.logAndStatusSpecs(201))
                .extract().as(AddUserModel.class);

        response.setName(user.getName());
        response.setGender(user.getGender());
        response.setEmail(user.getEmail());

        return response;
    }


    @Test
    void postCreateUser() {
        AddUserModel user = new AddUserModel();
        user.setName(faker.name().firstName());
        user.setGender("M");
        user.setEmail(faker.internet().emailAddress());

        AddUserModel response = step("Создаём пользователя", () ->
                given(loginRequestSpec)
                        .log().all()
                        .contentType(JSON)
                        .body(user)
                        .when()
                        .post()
                        .then()
                        .spec(BaseSpecs.logAndStatusSpecs(201))
                        .extract().as(AddUserModel.class)
        );

        step("Проверяем, что id вернулся и сообщение корректное", () -> {
            assertNotNull(response.getId(), "ID не должен быть null");
            assertEquals("User created", response.getMessage(), "Неверное сообщение в ответе");
        });
    }

    @Test
    void getCheckUser() {
        AddUserModel created = createTestUser();

        given(loginRequestSpec)
                .when()
                .get("/{id}", created.getId())
                .then()
                .spec(BaseSpecs.logAndStatusSpecs(200))
                .body("id", equalTo(created.getId()))
                .body("name", equalTo(created.getName()));
    }


    @Test
    void putUser() {
        AddUserModel created = createTestUser();

        AddUserModel updatedUser = new AddUserModel();
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
                .spec(BaseSpecs.logAndStatusSpecs(200))
                .body("id", equalTo(created.getId()))
                .body("message", equalTo("User fully updated"));

        given(loginRequestSpec)
                .when()
                .get("/{id}", created.getId())
                .then()
                .spec(BaseSpecs.logAndStatusSpecs(200))
                .body("name", equalTo(updatedUser.getName()))
                .body("email", equalTo(updatedUser.getEmail()))
                .body("gender", equalTo(updatedUser.getGender()));
    }

    @Test
    void patchUser() {
        AddUserModel created = createTestUser();

        AddUserModel updatedUser = new AddUserModel();
        updatedUser.setName(faker.name().firstName());

        given(loginRequestSpec)
                .body(updatedUser)
                .when()
                .patch("/{id}", created.getId())
                .then()
                .log().status()
                .log().body()
                .spec(BaseSpecs.logAndStatusSpecs(200))
                .body("id", equalTo(created.getId()))
                .body("message", equalTo("User partially updated"));

        given(loginRequestSpec)
                .when()
                .get("/{id}", created.getId())
                .then()
                .spec(BaseSpecs.logAndStatusSpecs(200))
                .body("name", equalTo(updatedUser.getName()));
    }

    @Test
    void deleteUser() {
        AddUserModel created = createTestUser();

        given(loginRequestSpec)
                .when()
                .delete("/{id}", created.getId())
                .then()
                .log().status()
                .spec(BaseSpecs.logAndStatusSpecs(204));

        given(loginRequestSpec)
                .when()
                .get("/{id}", created.getId())
                .then()
                .spec(BaseSpecs.logAndStatusSpecs(404));
    }

}
