package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.api.AccountLoginApi;
import guru.qa.dZhurHomeWork.less16_Api_Book_Delete.models.LoginBodyRequest;
import org.openqa.selenium.Cookie;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckNoBookInUiProfile {

    static AccountLoginApi loginApi = new AccountLoginApi();

    static {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    public void checkProfileInUi() {
        LoginBodyRequest loginRequest = new LoginBodyRequest("2Baikal", "2Baikal123&");
        Map<String, String> cookies = loginApi.loginAndGetCookies(loginRequest);

        open("/favicon.ico");

        cookies.forEach((name, value) -> {
            if (name != null && value != null && !name.isEmpty() && !value.isEmpty()) {
                WebDriverRunner.getWebDriver().manage().addCookie(new Cookie(name, value));
            }
        });

        open("/profile");

        $(".rt-noData").shouldHave(text("No rows found"));
    }
}
