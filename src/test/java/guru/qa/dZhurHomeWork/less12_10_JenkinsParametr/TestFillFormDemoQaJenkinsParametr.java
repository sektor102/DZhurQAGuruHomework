package guru.qa.dZhurHomeWork.less12_10_JenkinsParametr;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.dZhurHomeWork.less12_10_JenkinsParametr.Attach.Attach;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
@Tag("Baikal2")
@DisplayName("Тест формы DemoQA")
public class TestFillFormDemoQaJenkinsParametr {

    public static final String repository = "/automation-practice-form";
    File file = new File("src/test/resources/less6_7/John_Shepard_29.jpg");


    @BeforeAll
    static void beforeAll() {
        String combo = System.getProperty("browserAndVersion");
        String[] parts = combo.split("_");
        browserSize = System.getProperty("browserSize");
        browser = parts[0];
        browserVersion = parts[1];
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


        Allure.parameter("Browser", Configuration.browser + " " + Configuration.browserVersion);
        Allure.parameter("Resolution", Configuration.browserSize);
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
    @Test
    @DisplayName("DemoQA test")
    void TestFillForm() {

        step("Открываем страницу формы " + repository, () -> {
            open(repository);
            $("body").shouldBe(visible);
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });

        String uniqueId = UUID.randomUUID().toString();
        String testName = String.format("DemoQA [%s_%s %s]", browser, browserVersion, browserSize);

        Allure.getLifecycle().updateTestCase(tc -> {
            tc.setUuid(uniqueId);
            tc.setName(testName);
            tc.setFullName(testName);
            tc.setHistoryId(browser + "_" + browserVersion + "_" + browserSize);
        });

        Allure.label("Browser", browser + "_" + browserVersion);
        Allure.label("Resolution", browserSize);

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
            executeJavaScript("arguments[0].click();", $("#hobbiesWrapper").$(byText("Reading")));
            if (!browser.equals("firefox")) {
                $("#uploadPicture").uploadFromClasspath("less6_7/John_Shepard_29.jpg");
            } else {
                io.qameta.allure.Allure.step("⚠️ Пропускаем upload — Firefox конченный браузер");
            }
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
            if (!browser.equals("firefox")) {
                $(".table-responsive").$(byText("Picture")).closest("tr").shouldHave(text("John_Shepard_29.jpg"));
            } else {
                io.qameta.allure.Allure.step("Пропускаем Проверку картинки — еще раз: Firefox конченный браузер");
            }

            $(".table-responsive").$(byText("Address")).closest("tr").shouldHave(text("Uss Normandy"));
            $(".table-responsive").$(byText("State and City")).closest("tr").shouldHave(text("NCR Noida"));
        });

    }
}


