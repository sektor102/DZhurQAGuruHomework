package guru.qa.dZhurHomeWork.less16_Api_Book_Delete;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.AccountLoginApi;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.TakeBookIdApi;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.AddBookInAccountApi;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.DeleteBookApi;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.IsbnId;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.LoginBodyRequest;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.LoginBodyResponse;
import org.junit.jupiter.api.Test;

public class TestBookDelete {
    AccountLoginApi loginApi = new AccountLoginApi();
    TakeBookIdApi bookApi = new TakeBookIdApi();
    AddBookInAccountApi addApi = new AddBookInAccountApi();
    DeleteBookApi deleteApi = new DeleteBookApi();

    @Test
    void deleteBookTest() {
        // TODO*: вызов "ручки" которая удаляет все книги из профиля.

        LoginBodyRequest loginRequestBody = new LoginBodyRequest("2Baikal", "2Baikal123&");

        LoginBodyResponse loginBodyResponse = loginApi.loginDemoQa(loginRequestBody);

        IsbnId isbn = bookApi.takeBook();

        addApi.addBookInAccount(loginBodyResponse, isbn);

        // TODO: доделать метод delete по "новой" схеме
        deleteApi.deleteBook(userData);

        // TODO: что ты открываешь браузер и убеждаешься что книг там нет. (открытие https://demoqa.com/profile
        //  и убедиться что там отображается "No Rows Found"
    }
}
