package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.IsbnId;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.LoginBodyResponse;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import java.util.Map;

public class DeleteBookApi {

    public void deleteBook(LoginBodyResponse data, IsbnId isbn) {

        var body = Map.of(
                "isbn", isbn.getIsbn(),
                "userId", data.getUserId()
        );

        given(BaseSpec.requestSpec)
                .contentType(JSON)
                .header("Authorization", "Bearer " + data.getToken())
                .body(body)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(BaseSpec.logAndStatusSpecs16(204));

        System.out.println("Удалили книгу " + isbn.getIsbn());
    }
}
