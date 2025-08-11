import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestFillFormDZhurbin {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
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
        $("#uploadPicture").uploadFromClasspath("John_Shepard_29.jpg");
        $("#currentAddress").setValue("Uss Normandy");
        $("#lastName").setValue("Shepard");
        $("#userEmail").setValue("JohnS@normandy.com");
        $("#state").click();
        $("#state input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#city input").setValue("Noida").pressEnter();
        $("#submit").click();

        $(".modal-body").shouldBe(visible);

        $$(".modal-body tbody tr").findBy(text("Student Email")).shouldHave(text("JohnS@normandy.com"));
        $$(".modal-body tbody tr").findBy(text("Gender")).shouldHave(text("Male"));
        $$(".modal-body tbody tr").findBy(text("Mobile")).shouldHave(text("1877667623"));
        $$(".modal-body tbody tr").findBy(text("Date of Birth")).shouldHave(text("11 April,2054"));
        $$(".modal-body tbody tr").findBy(text("Subjects")).shouldHave(text("Computer Science"));
        $$(".modal-body tbody tr").findBy(text("Hobbies")).shouldHave(text("Reading"));
        $$(".modal-body tbody tr").findBy(text("Picture")).shouldHave(text("John_Shepard_29.jpg"));
        $$(".modal-body tbody tr").findBy(text("Address")).shouldHave(text("Uss Normandy"));
        $$(".modal-body tbody tr").findBy(text("State and City")).shouldHave(text("NCR Noida"));

    }
}


