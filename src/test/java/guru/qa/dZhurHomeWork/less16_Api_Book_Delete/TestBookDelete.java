package guru.qa.dZhurHomeWork.less16_Api_Book_Delete;


import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.IsbnUserId;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs.BaseSpec.requestSpec;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


@Tag("ApiJenkinsBook")
public class TestBookDelete extends TestBase {



    @Test
    void deleteBookInProfiler() {
        loginDemoQa();
        takeIdBook();
        addBookInAccount();

        IsbnUserId deleteBook = new IsbnUserId();
        deleteBook.setIsbn(isbn);
        deleteBook.setUserId(userId);

        step("Удаляем книгу из профиля", () -> {
        given(requestSpec)
                .log().all()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .body(deleteBook)
                .when()
                .delete("/BookStore/v1/Book")

                .then()
                .log().status()
                .spec(BaseSpec.logAndStatusSpecs16(204));
        });
        step("Проверяем, что книга удалена", () ->
        System.out.println("Книга " + isbn + " удалена для пользователя " + userId)
        );
    }

}
