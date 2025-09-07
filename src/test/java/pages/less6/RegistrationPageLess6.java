package pages.less6;

import com.codeborne.selenide.SelenideElement;
import pages.components.less6.CalendarComponentLess6;
import pages.components.less6.CheckFillFormComponentLess6;


import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationPageLess6 {
    CalendarComponentLess6 calendarComponent = new CalendarComponentLess6();
    CheckFillFormComponentLess6 checkFillFormComponent = new CheckFillFormComponentLess6();

    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            numberPhoneInput = $("#userNumber"),
            subjectsInput = $("#subjectsContainer input"),
            hobbiesInput = $("#hobbiesWrapper"),
            pictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateContainer = $("#state"),
            stateInput = $("#state input"),
            cityContainer = $("#city"),
            cityInput = $("#city input"),
            submitClick = $("#submit");

    public RegistrationPageLess6 openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        return this;

    }

    public RegistrationPageLess6 firstName(String value){
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPageLess6 lastName(String value){
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPageLess6 email(String value){
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPageLess6 gender(String value){
        genderInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPageLess6 numberPhone(String value){
        numberPhoneInput.setValue(value);
        return this;
    }

    public RegistrationPageLess6 dateOfBirth(String day, String month, String year){
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPageLess6 subjects(String value){
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPageLess6 hobbies(String value){
        hobbiesInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPageLess6 picture(String value){
        pictureInput.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPageLess6 address(String value){
        addressInput.setValue(value);
        return this;
    }

    public RegistrationPageLess6 stateAndCity(String state, String city){
        stateContainer.click();
        stateInput.setValue(state).pressEnter();
        cityContainer.click();
        cityInput.setValue(city).pressEnter();
        return this;
    }

    public RegistrationPageLess6 submit(){
        submitClick.click();

        return this;
    }

    public RegistrationPageLess6 notFillForm() {
        $("#userForm").shouldHave(cssClass("was-validated"));
        $("#firstName:invalid").should(exist);
        $("#lastName:invalid").should(exist);
        $("#userNumber:invalid").should(exist);
        $$("#userForm input[name='gender']:invalid").shouldHave(size(3));

        return this;
    }

    public void checkNegative(){
        checkFillFormComponent.modalDialogNotAppear();
    }

    public RegistrationPageLess6 checkResult(String key, String value) {
        checkFillFormComponent.checkTable(key, value);

        return this;
    }



}