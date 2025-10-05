package guru.qa.dZhurHomeWork.less10_8_Allure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class TestCheckIssueLambda {
    public static final String repository = "/qa-guru/allure-notifications";
    public static final int issue = 327;

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
        SelenideLogger.addListener("allure", new AllureSelenide());

    }

    @Test
    public void testIssueSearchLambda() {

        step("Открываем страницу репозитория " + repository, () -> {
            open(repository);
        });

        step("Открываем Issue репозитория " + repository, () -> {
            $("#issues-tab").click();
        });

        step("Проверяем Issue с номером " + issue, () -> {
            $("[data-listview-component=items-list]")
                    .should(appear)
                    .shouldHave(text("#327"));
        });

    }
}
