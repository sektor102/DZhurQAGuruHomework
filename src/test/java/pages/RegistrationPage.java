package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.CheckFillFormComponent;


import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    CheckFillFormComponent checkFillFormComponent = new CheckFillFormComponent();

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

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        return this;

    }

    public RegistrationPage firstName(String value){
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage lastName(String value){
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage email(String value){
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPage gender(String value){
        genderInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage numberPhone(String value){
        numberPhoneInput.setValue(value);
        return this;
    }

    public RegistrationPage dateOfBirth(String day, String month, String year){
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage subjects(String value){
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage hobbies(String value){
        hobbiesInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage picture(String value){
        pictureInput.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage address(String value){
        addressInput.setValue(value);
        return this;
    }

    public RegistrationPage stateAndCity(String state, String city){
        stateContainer.click();
        stateInput.setValue(state).pressEnter();
        cityContainer.click();
        cityInput.setValue(city).pressEnter();
        return this;
    }

    public RegistrationPage submit(){
        submitClick.click();

        return this;
    }

    public RegistrationPage notFillForm() {
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

    public RegistrationPage checkResult(String key, String value) {
        checkFillFormComponent.checkTable(key, value);

        return this;
    }



}




