package guru.qa.dZhurHomeWork.less14_Rest_Api_Spec.helper;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomAllureListener {

    private static final AllureRestAssured FILTER = new AllureRestAssured();

    private CustomAllureListener() {
        // приватный конструктор, чтобы нельзя было создать экземпляр
    }

    public static AllureRestAssured withAllure() {
        return FILTER;
    }
}
