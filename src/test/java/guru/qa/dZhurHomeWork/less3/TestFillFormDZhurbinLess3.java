package guru.qa.dZhurHomeWork.less3;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestFillFormDZhurbinLess3 {

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
        $("#uploadPicture").uploadFromClasspath("less6_7/John_Shepard_29.jpg");
        $("#currentAddress").setValue("Uss Normandy");
        $("#lastName").setValue("Shepard");
        $("#userEmail").setValue("JohnS@normandy.com");
        $("#state").click();
        $("#state input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#city input").setValue("Noida").pressEnter();
        $("#submit").click();

        $(".modal-body").shouldBe(visible);
        $(".table-responsive").$(byText("Student Name")).closest("tr").shouldHave(text("John Shepard"));
        $(".table-responsive").$(byText("Student Email")).closest("tr").shouldHave(text("JohnS@normandy.com"));
        $(".table-responsive").$(byText("Gender")).closest("tr").shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).closest("tr").shouldHave(text("1877667623"));
        $(".table-responsive").$(byText("Date of Birth")).closest("tr").shouldHave(text("11 April,2054"));
        $(".table-responsive").$(byText("Subjects")).closest("tr").shouldHave(text("Computer Science"));
        $(".table-responsive").$(byText("Hobbies")).closest("tr").shouldHave(text("Reading"));
        $(".table-responsive").$(byText("Picture")).closest("tr").shouldHave(text("less6_7/John_Shepard_29.jpg"));
        $(".table-responsive").$(byText("Address")).closest("tr").shouldHave(text("Uss Normandy"));
        $(".table-responsive").$(byText("State and City")).closest("tr").shouldHave(text("NCR Noida"));


    }
}


