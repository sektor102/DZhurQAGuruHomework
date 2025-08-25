package homeWorkCodeAutoTest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestHoverGithubLess5 {
    @BeforeAll
    static void setupEnvironment () {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void hoverToSolutionsAndClickEnterprizeTest(){
        open("");
        $(".HeaderMenu-nav").$(byText("Solutions")).hover();
        $("a[href*='enterprise']").click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered")).shouldHave(text("developer platform"));
    }
}


