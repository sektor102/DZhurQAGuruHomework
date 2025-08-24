package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Condition.*;

public class CheckFillFormComponent {
    private final SelenideElement
            modalDialog = $(".modal-dialog"),
            formTable   = $(".table-responsive");

    public CheckFillFormComponent checkTable(String key, String value) {
        modalDialog.should(appear);
        formTable.$(byText(key)).parent().shouldHave(text(value));
        return null;
    }

    public CheckFillFormComponent modalDialogNotAppear() {
        modalDialog.shouldNot(appear);
        return null;
    }
}
