package guru.qa.dZhurHomeWork.less21_Diplom.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SeachProductPage {

    private final SelenideElement
    searchField = $("[name='search_string']"),
    buttonSearch = $("[type='submit']"),
    searchResult = $(".grey_border_block_center"),
    productResult = $(".catalog");


    @Step("Вводим Royal в строку поиска")
    public SeachProductPage searchProduct() {
        searchField.setValue("Royal");
        return this;
    }

    @Step("Нажимаем кнопку поиска")
    public SeachProductPage pressButtonSearch() {
        buttonSearch.click();
        return this;
    }

    @Step("Клик по 1-му найденному результату")
    public SeachProductPage seachProductPage() {
        searchResult.$$("a")
                .first()
                .click();
        return this;
    }

    @Step
    public SeachProductPage productResultCheck() {
        productResult.shouldHave(text("Корм для кошек с расстройствами пищеварительной системы."));
        return this;
    }
}