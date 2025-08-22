package testSelenideHomeWork;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class TestFillFormLess6 extends TestBase {
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
    }