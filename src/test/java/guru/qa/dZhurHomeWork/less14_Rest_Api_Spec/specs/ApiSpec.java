package guru.qa.dZhurHomeWork.less14_Rest_Api_Spec.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static guru.qa.dZhurHomeWork.less14_Rest_Api_Spec.helper.CustomAllureListener.withAllure;

public class ApiSpec {

    public static RequestSpecification loginRequestSpec = new RequestSpecBuilder()
            .setBaseUri("http://176.120.67.49:8000")
            .setBasePath("/users")
            .setContentType(ContentType.JSON)
            .addFilter(withAllure())
            .log(LogDetail.ALL)
            .build();

}

