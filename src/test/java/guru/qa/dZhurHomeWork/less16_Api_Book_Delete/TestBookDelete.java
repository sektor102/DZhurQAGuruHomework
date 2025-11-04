package guru.qa.dZhurHomeWork.less16_Api_Book_Delete;

import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.AccountLoginApi;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.TakeBookIdApi;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.AddBookInAccountApi;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.DeleteBookApi;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.IsbnUserId;

import org.junit.jupiter.api.Test;

public class TestBookDelete {

    @Test
    void deleteBookTest() {
        AccountLoginApi loginApi = new AccountLoginApi();
        IsbnUserId userData = new IsbnUserId();
        userData.setUserName("2Baikal");
        userData.setPassword("2Baikal123&");

        userData = loginApi.loginDemoQa(userData);

        TakeBookIdApi bookApi = new TakeBookIdApi();
        userData = bookApi.takeBook(userData);

        AddBookInAccountApi addApi = new AddBookInAccountApi();
        addApi.addBookInAccount(userData);

        DeleteBookApi deleteApi = new DeleteBookApi();
        deleteApi.deleteBook(userData);
    }


}
