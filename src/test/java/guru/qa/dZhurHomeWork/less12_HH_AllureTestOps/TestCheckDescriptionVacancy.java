package guru.qa.dZhurHomeWork.less12_HH_AllureTestOps;

import guru.qa.dZhurHomeWork.less12_HH_AllureTestOps.helper.TestBaseLess12HHAllure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@DisplayName("Проверяем описание вакансии")
public class TestCheckDescriptionVacancy extends TestBaseLess12HHAllure {
    @Tag("hh")
    @Tag("ui")
    @Test
    void testDescriptionVacancy() {

        step("Открываем вакансию", () -> open(urlWorkHH));

        step("Проверяем описание вакансии", () -> {
            $("[data-qa='vacancy-description']")
                    .shouldHave(text("Sputnik8 —"))
                    .shouldHave(text("крупнейший сервис бронирования экскурсий по всему миру на русском языке"))
                    .shouldHave(text("Мы аккредитованная IT-компания и быстро растем — каждый год"))
                    .shouldHave(text("В нашей команде почти 200 талантов"));

        });

    }
}
