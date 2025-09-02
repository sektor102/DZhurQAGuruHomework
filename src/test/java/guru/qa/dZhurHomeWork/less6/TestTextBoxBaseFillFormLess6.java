package guru.qa.dZhurHomeWork.less6;

import org.junit.jupiter.api.Test;
import pages.TextBoxPageLess6;

public class TestTextBoxBaseFillFormLess6 extends TestBaseFillFormLess6 {
    TextBoxPageLess6 textBoxPageLess6 = new TextBoxPageLess6();

    @Test
    void fillTextBoxTest() {
        textBoxPageLess6.openPage()
                .fullName("John Shepard")
                .email("JohnS@normandy.com")
                .currentAddress("Uss Normandy")
                .permanentAddress("Shepard was born in space on the ship Drifter")
                .submit()
                .checkFill();

    }

}
