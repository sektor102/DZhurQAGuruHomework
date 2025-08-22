package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {
    private String fullNameCheck, emailCheck, currentAddressCheck, permanentAddressCheck;


    public TextBoxPage openPage() {
        open("/text-box");
        return this;
    }

    public TextBoxPage fullName(String value){
        $("#userName").setValue(value);
        this.fullNameCheck = value;
        return this;
    }

    public TextBoxPage email(String value){
        $("#userEmail").setValue(value);
        this.emailCheck = value;
        return this;
    }

    public TextBoxPage currentAddress(String value){
        $("#currentAddress").setValue(value);
        this.currentAddressCheck = value;
        return this;
    }

    public TextBoxPage permanentAddress(String value){
        $("#permanentAddress").setValue(value);
        this.permanentAddressCheck = value;
        return this;
    }

    public TextBoxPage submit(){
        $("#submit").click();
        return this;
    }

    public void checkFill(){
        $("#output #name").shouldHave(text(fullNameCheck));
        $("#output #email").shouldHave(text(emailCheck));
        $("#output #currentAddress").shouldHave(text(currentAddressCheck));
        $("#output #permanentAddress").shouldHave(text(permanentAddressCheck));
    }

}
