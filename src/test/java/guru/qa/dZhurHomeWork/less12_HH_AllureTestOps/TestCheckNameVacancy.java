package guru.qa.dZhurHomeWork.less12_HH_AllureTestOps;

import guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Helper.TestBaseLess12HHAllure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@DisplayName("Проверяем что есть кнопка откликнуться")
public class TestCheckNameVacancy extends TestBaseLess12HHAllure {
    @Tag("hh")
    @Tag("ui")
    @Test
    void TestNameVacancy() {

        step("Открываем вакансию", () -> open(urlWorkHH));

        step("Проверяем название вакансии", () -> {
            $("[data-qa='vacancy-title']")
                    .shouldHave(text("Manual QA Engineer (Middle2 - Senior)"));
        });


        step("Проверяем текст в кнопке откликнуться", () -> {
            $("[data-qa='vacancy-response-link-top']")
                    .shouldHave(text("Откликнуться"));
        });

        step("Проверяем что в кнопке есть ссылка", () -> {
            $("[data-qa='vacancy-response-link-top']").
                    shouldHave(attributeMatching("href", ".*applicant/vacancy_response.*"));

        });

    }
}
