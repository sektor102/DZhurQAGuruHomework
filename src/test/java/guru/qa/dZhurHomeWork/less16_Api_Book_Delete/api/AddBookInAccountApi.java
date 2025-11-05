package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.AddBookRequest;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.IsbnId;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.LoginBodyResponse;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec;

import java.util.List;

import static guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec.requestSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


public class AddBookInAccountApi {
    public void addBookInAccount(LoginBodyResponse data, IsbnId isbn) {
        AddBookRequest requestBook = new AddBookRequest();
        requestBook.setUserId(data.getUserId());
        requestBook.setCollectionOfIsbns(List.of(isbn));
        given(requestSpec)
                .contentType(JSON)
                .header("Authorization", "Bearer " + data.getToken())
                .body(requestBook)

                .when()
                .post("/BookStore/v1/Books")

                .then()
                .spec(BaseSpec.logAndStatusSpecs16(201));
    }
}


