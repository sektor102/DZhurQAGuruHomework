package guru.qa.dZhurHomeWork.less12_HH_AllureTestOps;

import guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.Helper.TestBaseLess12HHAllure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class TestCityVacancy extends TestBaseLess12HHAllure {
    @Tag("hh")
    @Tag("ui")
    @DisplayName("Проверяем город вакансии")
    @Test
    void TestCityCheckVacancy() {

        step("Открываем вакансию", () -> open(urlWorkHH));

        step("Проверяем город вакансии", () -> {
            $("[data-qa='vacancy-view-raw-address']")
                    .shouldHave(text("Санкт-Петербург"));
        });

    }
}
