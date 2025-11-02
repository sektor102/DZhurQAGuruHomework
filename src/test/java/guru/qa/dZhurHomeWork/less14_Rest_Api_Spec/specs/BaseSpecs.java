package guru.qa.dZhurHomeWork.less14_Rest_Api_Spec.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static guru.qa.dZhurHomeWork.less14_Rest_Api_Spec.helper.CustomAllureListener.withAllure;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class BaseSpecs {

    public static RequestSpecification loginRequestSpec = new RequestSpecBuilder()
            .setBaseUri("http://176.120.67.49:8000")
            .setBasePath("/users")
            .setContentType(ContentType.JSON)
            .addFilter(withAllure())
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification logAndStatusSpecs(int expectStatusCode) {
        return new ResponseSpecBuilder()
            .expectStatusCode(expectStatusCode)
            .log(STATUS)
            .log(BODY)
            .build();
    }
}

