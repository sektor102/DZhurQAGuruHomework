package guru.qa.dZhurHomeWork.less7;

import guru.qa.dZhurHomeWork.less6.TestBaseFillFormLess6;
import org.junit.jupiter.api.Test;
import pages.RegistrationPageLess7;

import static utils.RandomGenerationLess7.*;


public class TestFillFormPageObjectRandomLess7 extends TestBaseFillFormLess6 {
    RegistrationPageLess7 RegistrationPageLess7 = new RegistrationPageLess7();

    String first = getFirstName();
    String last  = getLastName();
    String email = getEmail();
    String gender = getRandomGender();
    String phone  = getRandomPhone();
    String day = getDay(), month = getMonth(), year = getYear();
    String subject = getRandomSubject();
    String hobby = getRandomHobbies();
    String picture = getPicture();
    String address = getAddress();
    String state = getState(), city = getCity(state);


    @Test
    void fillFormTest() {
        RegistrationPageLess7.openPage()
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
        RegistrationPageLess7.openPage()
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
        RegistrationPageLess7.openPage()
                .submit()
                .notFillForm()
                .checkNegative();

    }



}
