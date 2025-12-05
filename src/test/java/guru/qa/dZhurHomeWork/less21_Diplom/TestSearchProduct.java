package guru.qa.dZhurHomeWork.less21_Diplom;

import guru.qa.dZhurHomeWork.less21_Diplom.pages.MainPagePages;
import guru.qa.dZhurHomeWork.less21_Diplom.pages.SeachProductPage;

import org.junit.jupiter.api.Test;

public class TestSearchProduct extends TestBaseLess21{
    MainPagePages MainPagePages = new MainPagePages();
    SeachProductPage SeachProductPage = new SeachProductPage();

    @Test
    void UiSearchProduct() {
        MainPagePages.openPage();

        SeachProductPage.searchProduct();
        SeachProductPage.pressButtonSearch();
        SeachProductPage.seachProductPage();
        SeachProductPage.productResultCheck();
    }
}
