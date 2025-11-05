package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.LoginBodyRequest;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.LoginBodyResponse;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec;
import org.junit.jupiter.api.Assertions;

import static guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec.requestSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AccountLoginApi {
    public LoginBodyResponse loginDemoQa(LoginBodyRequest data) {

        LoginBodyResponse response = given(requestSpec)
                .contentType(JSON)
                .body(data)
                .when()
                .post("/Account/v1/login")
                .then()
                .spec(BaseSpec.logAndStatusSpecs16(200))
                .extract().as(LoginBodyResponse.class);

        Assertions.assertNotNull(response.getUserId());
        Assertions.assertNotNull(response.getToken());

        System.out.println("Успешный логин, userId = " + response.getUserId() + ", token = " + response.getToken());

        return response;

    }

    //register
}