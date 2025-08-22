package testSelenideHomeWork;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class TestFillFormPageObjectLess6 extends TestBaseFillFormLess6 {
    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .firstName("John")
                .lastName("Shepard")
                .email("JohnS@normandy.com")
                .gender("Male")
                .numberPhone("1877667623")
                .dateOfBirth("11", "April", "2054")
                .subjects("Computer Science")
                .hobbies("Reading")
                .picture("John_Shepard_29.jpg")
                .address("Uss Normandy")
                .stateAndCity("NCR", "Noida")
                .submit()
                .checkResultFull();

    }

    @Test
    void fillPartialFormTest() {
        registrationPage.openPage()
                .firstName("John")
                .lastName("Shepard")
                .gender("Male")
                .numberPhone("1877667623")
                .dateOfBirth("11", "April", "2054")
                .submit()
                .checkResultPartial();
    }

    @Test
    void negativeFillFormTest() {
        registrationPage.openPage()
                .submit()
                .notFillForm()
                .checkNegative();

    }



}
