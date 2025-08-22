package testSelenideHomeWork;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TestTextBoxLess6 extends TestBaseFillFormLess6 {
    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void fillTextBoxTest() {
        textBoxPage.openPage()
                .fullName("John Shepard")
                .email("JohnS@normandy.com")
                .currentAddress("Uss Normandy")
                .permanentAddress("Shepard was born in space on the ship Drifter")
                .submit()
                .checkFill();

    }

}
