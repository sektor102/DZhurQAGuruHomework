package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.IsbnUserId;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.parsing.Parser;

import static guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec.requestSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class AccountLoginApi {
    public IsbnUserId loginDemoQa(IsbnUserId data) {
        RestAssured.defaultParser = Parser.JSON;
        Response response = given(requestSpec)
                .contentType(JSON)
                .body("{\"userName\": \"" + data.getUserName() + "\", " +
                        "\"password\": \"" + data.getPassword() + "\"}")
                .when()
                .post("/Account/v1/login")
                .then()
                .spec(BaseSpec.logAndStatusSpecs16(200))
                .body("userId", notNullValue())
                .body("token", notNullValue())
                .extract()
                .response();

        data.setUserId(response.path("userId"));
        data.setToken(response.path("token"));

        System.out.println("Успешный логин, userId = " + data.getUserId() + ", token = " + data.getToken());
        return data;
}




}