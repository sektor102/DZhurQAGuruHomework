package tests;

import com.codeborne.selenide.SelenideElement;
import java.io.File;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DZTestDemoQA37 {

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
            $("#gender-radio-1").click();
            $("#userNumber").setValue("+187766762");
            $("#dateOfBirthInput").setValue("11 Apr 2154");
            $("#hobbies-checkbox-1").click();
            File file = new File("C:/Users/dante/Desktop/Pic from idea/John_Shepard_29.jpg"); // путь к файлу
            $("#uploadPicture").uploadFile(file);
            $("#currentAddress").setValue("Uss Normandy");
            $("#lastName").setValue("Shepard");
            $("#userEmail").setValue("JohnS@normandy.com");
            $("#state").click();
            $("input[id^='react-select']").setValue("NCR").pressEnter();
            $("#city").click();
            $("input[id^='react-select']").setValue("Delhi").pressEnter();

        }
    }
}
