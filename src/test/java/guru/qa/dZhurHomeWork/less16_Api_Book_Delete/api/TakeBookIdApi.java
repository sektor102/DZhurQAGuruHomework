package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.IsbnId;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class TakeBookIdApi {

    public IsbnId takeBook() {
        IsbnId response = given(BaseSpec.requestSpec)
                .contentType(JSON)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .spec(BaseSpec.logAndStatusSpecs16(200))
                .extract().as(IsbnId.class);



        System.out.println("Взяли книгу с ISBN: " + response.getIsbn());
        return response;
    }
}
