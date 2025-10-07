package guru.qa.dZhurHomeWork.less12_10_JenkinsParametr;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.dZhurHomeWork.less12_10_JenkinsParametr.Attach.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class TestFillFormDemoQaJenkinsAllure {
    public static final String repository = "/automation-practice-form";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.browser = System.getProperty("browser");
        Configuration.browserVersion = System.getProperty("browserVersion");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        String urlSelenide = System.getProperty("urlSelenide");
        Configuration.remote = "https://user1:1234@"+urlSelenide+"/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    @Tag("Baikal2")
    void fillFormTestJenkinsAllure() {
        step("Открываем страницу формы для заполнения " + repository, () -> {
            open(repository);
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });

        step("Заполняем форму DemoQA", () -> {
            $("#firstName").setValue("John");
            $("#lastName").setValue("Shepard");
            $("#userEmail").setValue("JohnS@normandy.com");
            $("#genterWrapper").$(byText("Male")).click();
            $("#userNumber").setValue("1877667623");
            $("#dateOfBirthInput").sendKeys(Keys.CONTROL + "a");
            $("#dateOfBirthInput").sendKeys("11 Apr 2054" + Keys.ENTER);
            $("#subjectsContainer").click();
            $("#subjectsContainer input").setValue("Computer Science").pressEnter();
            $("#hobbiesWrapper").$(byText("Reading")).click();
            $("#uploadPicture").uploadFromClasspath("less6_7/John_Shepard_29.jpg");
            $("#currentAddress").setValue("Uss Normandy");
            $("#lastName").setValue("Shepard");
            $("#userEmail").setValue("JohnS@normandy.com");
            $("#state").click();
            $("#state input").setValue("NCR").pressEnter();
            $("#city").click();
            $("#city input").setValue("Noida").pressEnter();
        });

        step("Подтверждаем заполнение", () -> {
            $("#submit").click();
        });

        step("Проверяем корректность заполнения", () -> {
            $(".modal-body").shouldBe(visible);
            $(".table-responsive").$(byText("Student Name")).closest("tr").shouldHave(text("John Shepard"));
            $(".table-responsive").$(byText("Student Email")).closest("tr").shouldHave(text("JohnS@normandy.com"));
            $(".table-responsive").$(byText("Gender")).closest("tr").shouldHave(text("Male"));
            $(".table-responsive").$(byText("Mobile")).closest("tr").shouldHave(text("1877667623"));
            $(".table-responsive").$(byText("Date of Birth")).closest("tr").shouldHave(text("11 April,2054"));
            $(".table-responsive").$(byText("Subjects")).closest("tr").shouldHave(text("Computer Science"));
            $(".table-responsive").$(byText("Hobbies")).closest("tr").shouldHave(text("Reading"));
            $(".table-responsive").$(byText("Picture")).closest("tr").shouldHave(text("John_Shepard_29.jpg"));
            $(".table-responsive").$(byText("Address")).closest("tr").shouldHave(text("Uss Normandy"));
            $(".table-responsive").$(byText("State and City")).closest("tr").shouldHave(text("NCR Noida"));
        });

    }
}


