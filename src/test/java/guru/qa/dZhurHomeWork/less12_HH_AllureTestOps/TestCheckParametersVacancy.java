package guru.qa.dZhurHomeWork.less12_HH_AllureTestOps;

import guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Helper.TestBaseLess12HHAllure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Helper.AllureHelperLess12.updateTestMeta;
import static io.qameta.allure.Allure.step;

public class TestCheckParametersVacancy extends TestBaseLess12HHAllure {
    @Tag("hh")
    @Tag("ui")
    @DisplayName("Проверяем параметры вакансии")
    @Test
    void TestParametersVacancy() {

        step("Открываем вакансию", () -> {
            open(urlWorkHH);
            updateTestMeta();
        });

        step("Проверяем зарплату в вакансии", () -> {
            $(byText("Уровень дохода не указан"))
                    .shouldBe(visible);
        });

        step("Проверяем опыт работы в вакансии", () -> {
            $("[data-qa='work-experience-text']")
                    .shouldHave(text("Опыт работы"));
        });

        step("Проверяем занятость в вакансии", () -> {
            $("[data-qa='common-employment-text']")
                    .shouldHave(text("Полная занятость"));
        });

        step("Проверяем график работы в вакансии", () -> {
            $("[data-qa='work-schedule-by-days-text']")
                    .shouldHave(text("График: 5/2"));
        });

        step("Проверяем рабочие часы в вакансии", () -> {
            $("[data-qa='working-hours-text']")
                    .shouldHave(text("Рабочие часы: 8"));
        });

        step("Проверяем формат работы в вакансии", () -> {
            $("[data-qa='work-formats-text']")
                    .shouldHave(text("Формат работы: на месте работодателя, удалённо или гибрид"));
        });

    }
}

