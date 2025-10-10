package guru.qa.dZhurHomeWork.less12_HH_AllureTestOps;

import guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Helper.TestBaseLess12HHAllure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Helper.AllureHelperLess12.updateTestMeta;
import static io.qameta.allure.Allure.step;

public class TestCheckButtonReply extends TestBaseLess12HHAllure {
    @Tag("hh")
    @Tag("ui")
    @DisplayName("Проверяем что есть кнопка откликнуться")
    @Test
    void TestButtonReply() {

        step("Открываем вакансию", () -> {
            open(urlWorkHH);
            updateTestMeta();
        });

        step("Проверяем наличие кнопки откликнуться", () -> {
            $("[data-qa='vacancy-response-link-top']").should(appear);
            $("[data-qa='vacancy-response-link-top']")
                    .shouldBe(visible);
        });


        step("Проверяем текст в кнопке откликнуться", () -> {
            $("[data-qa='vacancy-response-link-top']")
                    .shouldHave(text("Откликнуться"));
        });

        step("Проверяем что в кнопке есть ссылка", () -> {
            $("[data-qa='vacancy-response-link-top']")
                    .shouldHave(attributeMatching("href", ".*applicant/vacancy_response.*"));

        });

    }



}
