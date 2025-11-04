package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.helpers;
import io.qameta.allure.restassured.AllureRestAssured;

public class Allure {
    private static final AllureRestAssured FILTER = new AllureRestAssured();

    public static AllureRestAssured withCustomTemplates() {
        FILTER.setRequestTemplate("less16_Api_Book/tpl/request.ftl");
        FILTER.setResponseTemplate("less16_Api_Book/tpl/response.ftl");
        return FILTER;
    }
}
