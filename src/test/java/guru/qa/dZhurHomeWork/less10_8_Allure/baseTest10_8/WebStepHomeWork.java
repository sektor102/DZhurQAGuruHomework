package guru.qa.dZhurHomeWork.less10_8_Allure.baseTest10_8;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static guru.qa.dZhurHomeWork.less10_8_Allure.TestCheckIssueStep.*;

public class WebStepHomeWork {

    private final String baseUrl;
    private final String repository;
    private final int issue;

    public WebStepHomeWork(String baseUrl, String repository, int issue) {
        this.baseUrl = baseUrl;
        this.repository = repository;
        this.issue = issue;
    }


    @Step("Открываем страницу репозитория {repository}")
    public void openRepoPage() {
        open(baseUrl + repository);
    }

    @Step("Жмем по кнопке Issue")
    public void clickIssueButton() {
        $("#issues-tab").click();
    }

    @Step("Проверяем что есть #{issue} вопрос")
    public void checkIssueNumber() {
        $("[data-listview-component=items-list]")
                .should(appear)
                .shouldHave(text("#" + issue));
    }
}
