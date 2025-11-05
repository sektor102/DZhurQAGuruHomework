package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.AddBookModel;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.IsbnUserId;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.LoginBodyRequest;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.LoginBodyResponse;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec;

import static guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec.requestSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


public class AddBookInAccountApi {
    public void addBookInAccount(LoginBodyResponse data, IsbnUserId isbn) {
        AddBookModel requestBook = new AddBookModel();
        requestBook.setIsbn(new AddBookModel.CollectionOfIsbns());
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


