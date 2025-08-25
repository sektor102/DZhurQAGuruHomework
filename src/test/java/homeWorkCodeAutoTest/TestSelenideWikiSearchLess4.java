package homeWorkCodeAutoTest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class TestSelenideWikiSearchLess4 {
    @BeforeAll
    static void setupEnvironment () {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void searchGithubJUnit5SearchTest() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-body").$("a[href*='SoftAssertions']").shouldBe(visible).click();
        $("#wiki-body").shouldHave(text(
                """
                        @ExtendWith({SoftAssertsExtension.class})
                        class Tests {
                          @Test
                          void test() {
                            Configuration.assertionMode = SOFT;
                            open("page.html");
                        
                            $("#first").should(visible).click();
                            $("#second").should(visible).click();
                          }
                        }"""
        ));

    }

}
