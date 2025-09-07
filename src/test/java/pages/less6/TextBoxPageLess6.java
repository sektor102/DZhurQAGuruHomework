package pages.less6;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPageLess6 {
    private String fullNameCheck, emailCheck, currentAddressCheck, permanentAddressCheck;

    private final SelenideElement
            fullNameInput = $("#userName"),
            emailInput = $("#userEmail"),
            addressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitClick = $("#submit"),
            outputName = $("#output #name"),
            outputEmail = $("#output #email"),
            outputcurrentAddress = $("#output #email"),
            outputpermanentAddress = $("#output #permanentAddress");



    public TextBoxPageLess6 openPage() {
        open("/text-box");
        return this;
    }

    public TextBoxPageLess6 fullName(String value){
        fullNameInput.setValue(value);
        this.fullNameCheck = value;
        return this;
    }

    public TextBoxPageLess6 email(String value){
        emailInput.setValue(value);
        this.emailCheck = value;
        return this;
    }

    public TextBoxPageLess6 currentAddress(String value){
        addressInput.setValue(value);
        this.currentAddressCheck = value;
        return this;
    }

    public TextBoxPageLess6 permanentAddress(String value){
        permanentAddressInput.setValue(value);
        this.permanentAddressCheck = value;
        return this;
    }

    public TextBoxPageLess6 submit(){
        submitClick.click();
        return this;
    }

    public void checkFill(){
        outputName.shouldHave(text(fullNameCheck));
        outputEmail.shouldHave(text(emailCheck));
        outputcurrentAddress.shouldHave(text(currentAddressCheck));
        outputpermanentAddress.shouldHave(text(permanentAddressCheck));
    }

}
