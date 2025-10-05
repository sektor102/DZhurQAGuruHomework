package guru.qa.dZhurHomeWork.less10_8_Allure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.dZhurHomeWork.less10_8_Allure.baseTest10_8.WebStepHomeWork;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestCheckIssueStep {

    public static final String baseUrl = "https://github.com";
    public static final String repository = "/qa-guru/allure-notifications";
    public static final int issue = 327;

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    public void testStepCheckIssue() {
        WebStepHomeWork steps = new WebStepHomeWork(baseUrl, repository, issue);

        steps.openRepoPage();
        steps.clickIssueButton();
        steps.checkIssueNumber();
    }
}
