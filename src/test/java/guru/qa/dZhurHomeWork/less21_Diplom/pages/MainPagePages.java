package guru.qa.dZhurHomeWork.less21_Diplom.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class MainPagePages {

    private final SelenideElement
            logoMainPage = $(".logo"),
            headerPhoneMainPage = $(".header_phone"),
            navbarMainPage = $("#navbar"),
            cartMainPage = $(".cart");

    public MainPagePages openPage() {
        open("");
        return this;
    }

    public MainPagePages checkLogo() {
        logoMainPage.shouldBe(visible);
        return this;
    }

    public MainPagePages checkPhone() {
        headerPhoneMainPage.$(byText("Заказать доставку тел. 777 - 321"));
        return this;
    }

    public MainPagePages checkDogCatalog() {
        navbarMainPage.$("a[href='/main/sobaki/']").shouldHave(text("СОБАКИ"));
        return this;
    }

    public MainPagePages checkBucket() {
        cartMainPage.$("a[href='/emarket/cart/']").shouldHave(text("Корзина"));
        return this;
    }

    public void checkBucketEmpty() {
        cartMainPage.shouldHave(text("Ваша корзина пуста"));
    }



}