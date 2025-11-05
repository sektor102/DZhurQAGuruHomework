package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api;


import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.IsbnId;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec;

import static guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec.requestSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


public class DeleteBookApi {
    public IsbnId deleteBook(IsbnId data) {
        given(requestSpec)
                .contentType(JSON)
                .header("Authorization", "Bearer " + data.getToken())
                .body("{\"userId\": \"" + data.getUserId() + "\", \"isbn\": \"" + data.getIsbn() + "\"}")
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(BaseSpec.logAndStatusSpecs16(204));

        System.out.println("Книга " + data.getIsbn() + " удалена у пользователя " + data.getUserId());
        return data;
    }

}
