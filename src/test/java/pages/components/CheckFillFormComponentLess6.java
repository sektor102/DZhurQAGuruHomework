package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Condition.*;

public class CheckFillFormComponentLess6 {
    private final SelenideElement
            modalDialog = $(".modal-dialog"),
            formTable   = $(".table-responsive");

    public CheckFillFormComponentLess6 checkTable(String key, String value) {
        modalDialog.should(appear);
        formTable.$(byText(key)).parent().shouldHave(text(value));
        return null;
    }

    public CheckFillFormComponentLess6 modalDialogNotAppear() {
        modalDialog.shouldNot(appear);
        return null;
    }
}
