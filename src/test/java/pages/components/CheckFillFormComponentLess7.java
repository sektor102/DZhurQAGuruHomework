package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CheckFillFormComponentLess7 {
    private final SelenideElement
            modalDialog = $(".modal-dialog"),
            formTable   = $(".table-responsive");

    public CheckFillFormComponentLess7 checkTable(String key, String value) {
        modalDialog.should(appear);
        formTable.$(byText(key)).parent().shouldHave(text(value));
        return null;
    }

    public CheckFillFormComponentLess7 modalDialogNotAppear() {
        modalDialog.shouldNot(appear);
        return null;
    }
}
