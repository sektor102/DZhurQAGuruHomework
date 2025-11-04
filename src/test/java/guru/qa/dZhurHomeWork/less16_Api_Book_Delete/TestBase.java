package guru.qa.dZhurHomeWork.less16_Api_Book_Delete;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://demoqa.com";
    }
}
