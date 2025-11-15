package guru.qa.dZhurHomeWork.less16_Api_Book_Delete;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.*;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.IsbnId;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.LoginBodyRequest;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.LoginBodyResponse;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.ui.CheckNoBookInUiProfile;
import org.junit.jupiter.api.Test;

public class TestBookDelete {
    AccountLoginApi loginApi = new AccountLoginApi();
    TakeBookIdApi bookApi = new TakeBookIdApi();
    AddBookInAccountApi addApi = new AddBookInAccountApi();
    DeleteBookApi deleteApi = new DeleteBookApi();
    DeleteAllBooks deleteAllBook = new DeleteAllBooks();

    @Test
    void deleteBookTest() {
        LoginBodyRequest loginRequestBody = new LoginBodyRequest("2Baikal", "2Baikal123&");

        LoginBodyResponse loginBodyResponse = loginApi.loginDemoQa(loginRequestBody);

        deleteAllBook.deleteAll(loginBodyResponse);

        IsbnId isbn = bookApi.takeBook();

        addApi.addBookInAccount(loginBodyResponse, isbn);

        deleteApi.deleteBook(loginBodyResponse, isbn);

        CheckNoBookInUiProfile uiCheck = new CheckNoBookInUiProfile();
        uiCheck.checkProfileInUi();

    }
}
