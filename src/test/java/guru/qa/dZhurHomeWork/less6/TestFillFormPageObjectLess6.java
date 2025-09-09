package guru.qa.dZhurHomeWork.less6;

import org.junit.jupiter.api.Test;
import pages.RegistrationPageLess6;

public class TestFillFormPageObjectLess6 extends TestBaseFillFormLess6 {
    RegistrationPageLess6 registrationPageLess6 = new RegistrationPageLess6();

    String first = "John";
    String last  = "Shepard";
    String email = "JohnS@normandy.com";
    String gender = "Male";
    String phone  = "1877667623";
    String day = "11", month = "April", year = "2054";
    String subject = "Computer Science";
    String hobby = "Reading";
    String picture = "less6_7/John_Shepard_29.jpg";
    String address = "Uss Normandy";
    String state = "NCR", city = "Noida";


    @Test
    void fillFormTest() {
        registrationPageLess6.openPage()
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
        registrationPageLess6.openPage()
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
        registrationPageLess6.openPage()
                .submit()
                .notFillForm()
                .checkNegative();

    }



}
