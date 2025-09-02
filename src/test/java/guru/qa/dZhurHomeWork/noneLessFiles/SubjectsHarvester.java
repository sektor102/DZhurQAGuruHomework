package guru.qa.dZhurHomeWork.noneLessFiles;// заменяет предыдущий SubjectsHarvester
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.nio.file.*;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

public final class SubjectsHarvester {
    public static void main(String[] args) throws Exception {
        // на первый запуск лучше без headless, чтобы видеть экран
        Configuration.headless = false;
        Configuration.timeout  = 8000;

        open("https://demoqa.com/automation-practice-form");

        SelenideElement input = $("#subjectsInput").shouldBe(visible).scrollIntoView(true);

        Set<String> uniq = new LinkedHashSet<>();
        // достаточно нескольких "сидов", которые раскрывают список
        List<String> seeds = Arrays.asList("a", "c", "e", "i", "o", "u");

        for (String seed : seeds) {
            input.click();
            input.setValue(seed);

            // ждём, когда появятся ОПЦИИ: id начинается с react-select-, содержит -option-
            var options = $$("div[id^='react-select-'][id*='-option-']")
                    .shouldBe(sizeGreaterThan(0)); // важное ожидание

            options.forEach(o -> uniq.add(o.getText().trim()));

            // очистка поля + закрыть меню
            input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
            // не ждём listbox — его нет; ждём, что опции исчезнут
            $$("div[id^='react-select-'][id*='-option-']").shouldBe(size(0));
        }

        Path out = Paths.get("src/test/resources/subjects.csv");
        Files.createDirectories(out.getParent());
        Files.writeString(out, String.join("\n", uniq));

        System.out.println("Saved: " + out.toAbsolutePath());
        closeWebDriver();
    }
}
