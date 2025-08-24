package testSelenideHomeWork;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class TestFillFormPageObjectLess6 extends TestBaseFillFormLess6 {
    RegistrationPage registrationPage = new RegistrationPage();

    String first = "John";
    String last  = "Shepard";
    String email = "JohnS@normandy.com";
    String gender = "Male";
    String phone  = "1877667623";
    String day = "11", month = "April", year = "2054";
    String subject = "Computer Science";
    String hobby = "Reading";
    String picture = "John_Shepard_29.jpg";
    String address = "Uss Normandy";
    String state = "NCR", city = "Noida";


    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .firstName(first)
                .lastName(last)
                .email(email)
                .gender(gender)
                .numberPhone(phone)
                .dateOfBirth(day, month, year)
                .subjects(subject)
                .hobbies(hobby)
                .picture(picture)
                .address(address)
                .stateAndCity(state, city)
                .submit()
                .checkResult("Student Name", first + " " + last)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phone)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", picture)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);

    }

    @Test
    void fillPartialFormTest() {
        registrationPage.openPage()
                .firstName(first)
                .lastName(last)
                .gender(gender)
                .numberPhone(phone)
                .dateOfBirth(day, month, year)
                .submit()
                .checkResult("Student Name", first + " " + last)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phone)
                .checkResult("Date of Birth", day + " " + month + "," + year);
    }

    @Test
    void negativeFillFormTest() {
        registrationPage.openPage()
                .submit()
                .notFillForm()
                .checkNegative();

    }



}
