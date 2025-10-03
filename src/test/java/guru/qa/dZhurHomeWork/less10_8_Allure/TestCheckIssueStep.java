package guru.qa.dZhurHomeWork.less10_8_Allure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestCheckIssueStep {

    public static final String repository = "/qa-guru/allure-notifications";
    public static final int issue = 327;

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
        SelenideLogger.addListener("allure", new AllureSelenide());

    }

    @Step("Открываем страницу репозитория" + repository)
    public void openRepoPage() {
        open(repository);
    }

    @Step("Жмем по кнопке Issue")
    public void clickIssueButton() {
        $("#issues-tab").click();
    }

    @Step("Проверяем что есть " + issue + " вопрос")
    public void checkIssueNumber() {
        $("[data-listview-component=items-list]")
                .should(appear)
                .shouldHave(text("#327"));
    }

    @Test
    public void testStepCheckIssue() {
        openRepoPage();
        clickIssueButton();
        checkIssueNumber();
    }
}
