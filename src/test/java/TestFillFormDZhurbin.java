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
        $(byText("Male")).click();
        $("#userNumber").setValue("1877667623");
        $("#dateOfBirthInput").sendKeys(Keys.CONTROL + "a");
        $("#dateOfBirthInput").sendKeys("11 Apr 2054" + Keys.ENTER);
        $("#subjectsContainer").click();
        $("#subjectsContainer input").setValue("Computer Science").pressEnter();
        $(byText("Reading")).click();
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

        $$(".modal-body tbody tr")
                .findBy(text("Student Name"))
                .$("td:nth-child(2)")
                .shouldHave(exactText("John Shepard"));

        $$(".modal-body tbody tr")
                .findBy(text("Student Email"))
                .$("td:nth-child(2)")
                .shouldHave(exactText("JohnS@normandy.com"));

        $$(".modal-body tbody tr")
                .findBy(text("Gender"))
                .$("td:nth-child(2)")
                .shouldHave(exactText("Male"));

        $$(".modal-body tbody tr")
                .findBy(text("Mobile"))
                .$("td:nth-child(2)")
                .shouldHave(exactText("1877667623"));

        $$(".modal-body tbody tr")
                .findBy(text("Date of Birth"))
                .$("td:nth-child(2)")
                .shouldHave(exactText("11 April,2054"));

        $$(".modal-body tbody tr")
                .findBy(text("Subjects"))
                .$("td:nth-child(2)")
                .shouldHave(exactText("Computer Science"));

        $$(".modal-body tbody tr")
                .findBy(text("Hobbies"))
                .$("td:nth-child(2)")
                .shouldHave(exactText("Reading"));

        $$(".modal-body tbody tr")
                .findBy(text("Picture"))
                .$("td:nth-child(2)")
                .shouldHave(exactText("John_Shepard_29.jpg"));

        $$(".modal-body tbody tr")
                .findBy(text("Address"))
                .$("td:nth-child(2)")
                .shouldHave(exactText("Uss Normandy"));

        $$(".modal-body tbody tr")
                .findBy(text("State and City"))
                .$("td:nth-child(2)")
                .shouldHave(exactText("NCR Noida"));
    }
}


