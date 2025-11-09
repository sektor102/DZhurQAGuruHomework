package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.AddBookRequest;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.LoginBodyResponse;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec;


import static guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec.requestSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class DeleteAllBooks {
    public void deleteAll(LoginBodyResponse data) {
        AddBookRequest requestBook = new AddBookRequest();
        requestBook.setUserId(data.getUserId());
        given(requestSpec)
                .contentType(JSON)
                .header("Authorization", "Bearer " + data.getToken())
                .body(requestBook)

                .when()
                .delete("/BookStore/v1/Books?UserId=" + data.getUserId())

                .then()
                .spec(BaseSpec.logAndStatusSpecs16(204));
    }
}
