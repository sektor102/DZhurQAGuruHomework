package guru.qa.dZhurHomeWork.less16_Api_Book_Delete;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.AccountLoginApi;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.TakeBookIdApi;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.AddBookInAccountApi;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.DeleteBookApi;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.IsbnUserId;

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

        LoginBodyRequest loginRequestBody = new LoginBodyRequest("2Baikal", "2Baikal123&");

        LoginBodyResponse loginBodyResponse = loginApi.loginDemoQa(loginRequestBody);

        IsbnUserId isbn = bookApi.takeBook();

        addApi.addBookInAccount(userData);

        deleteApi.deleteBook(userData);
    }


}
