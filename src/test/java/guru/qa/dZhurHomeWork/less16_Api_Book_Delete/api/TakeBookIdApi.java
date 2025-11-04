package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.IsbnUserId;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class TakeBookIdApi {

    public IsbnUserId takeBook(IsbnUserId data) {
        Response response = given(BaseSpec.requestSpec)
                .contentType(JSON)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .spec(BaseSpec.logAndStatusSpecs16(200))
                .extract()
                .response();

        String isbn = response.path("books[0].isbn");
        data.setIsbn(isbn);

        System.out.println("Взяли книгу с ISBN: " + isbn);
        return data;
    }
}
