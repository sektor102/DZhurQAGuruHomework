import java.io.File;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DZhurbinTestDemo {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("John");
        $("#lastName").setValue("Shepard");
        $("#userEmail").setValue("JohnS@normandy.com");
        $("label[for='gender-radio-1']").click();
        $("#userNumber").setValue("1877667623");
        $("#dateOfBirthInput").sendKeys(Keys.CONTROL + "a"); // выделить всё
        $("#dateOfBirthInput").sendKeys("11 Apr 2054" + Keys.ENTER);
        $("#subjectsContainer").click();
        $("#subjectsContainer input").setValue("Computer Science").pressEnter();
        $("label[for='hobbies-checkbox-2']").click();
        File file = new File("C:/Users/dante/Desktop/Pic from idea/John_Shepard_29.jpg"); // путь к файлу
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").setValue("Uss Normandy");
        $("#lastName").setValue("Shepard");
        $("#userEmail").setValue("JohnS@normandy.com");
        $("#state").click();
        $("#state input").setValue("NCR").pressEnter();
        $("#state").shouldHave(text("NCR")); // быстрая проверка
        $("#city").click();
        $("#city input").setValue("Noida").pressEnter();
        $("#city").shouldHave(text("Noida"));
        $("#submit").click();

    }
}

