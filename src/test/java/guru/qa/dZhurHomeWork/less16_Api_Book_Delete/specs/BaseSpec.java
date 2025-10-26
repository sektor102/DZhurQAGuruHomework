package guru.qa.dZhurHomeWork.less16_Api_Book_Delete.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static guru.qa.dZhurHomeWork.less16_Api_Book_Delete.helpers.Allure.withAllure;

public class BaseSpec {

    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addFilter(withAllure())
            .log(LogDetail.ALL)
            .build();
}

