package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.helpers;
import io.qameta.allure.restassured.AllureRestAssured;

public class Allure {

    private static final AllureRestAssured FILTER = new AllureRestAssured();

    private Allure() {
    }

    public static AllureRestAssured withAllure() {
        return FILTER;
    }
}
