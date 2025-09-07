package pages.less7;

import com.codeborne.selenide.SelenideElement;
import pages.components.less6.CalendarComponentLess6;
import pages.components.less7.CheckFillFormComponentLess7;


import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationPageLess7 {
    CalendarComponentLess6 calendarComponentLess6 = new CalendarComponentLess6();
    CheckFillFormComponentLess7 checkFillFormComponentLess7 = new CheckFillFormComponentLess7();

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

    public RegistrationPageLess7 openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        return this;

    }

    public RegistrationPageLess7 firstName(String value){
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPageLess7 lastName(String value){
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPageLess7 email(String value){
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationPageLess7 gender(String value){
        genderInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPageLess7 numberPhone(String value){
        numberPhoneInput.setValue(value);
        return this;
    }

    public RegistrationPageLess7 dateOfBirth(String day, String month, String year) {
        calendarComponentLess6.setDate(day, month, year);
        return this;
    }

    public RegistrationPageLess7 subjects(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }


    public RegistrationPageLess7 hobbies(String value) {
        hobbiesInput.$(byText(value)).click();
        return this;
    }


    public RegistrationPageLess7 picture(String classpathPath) {
        pictureInput.uploadFromClasspath(classpathPath);
        return this;
    }

    public RegistrationPageLess7 address(String value){
        addressInput.setValue(value);
        return this;
    }

    public RegistrationPageLess7 stateAndCity(String state, String city){
        stateContainer.click();
        stateInput.setValue(state).pressEnter();
        cityContainer.click();
        cityInput.setValue(city).pressEnter();
        return this;
    }

    public RegistrationPageLess7 submit(){
        submitClick.click();

        return this;
    }

    public RegistrationPageLess7 notFillForm() {
        $("#userForm").shouldHave(cssClass("was-validated"));
        $("#firstName:invalid").should(exist);
        $("#lastName:invalid").should(exist);
        $("#userNumber:invalid").should(exist);
        $$("#userForm input[name='gender']:invalid").shouldHave(size(3));

        return this;
    }

    public void checkNegative(){
        checkFillFormComponentLess7.modalDialogNotAppear();
    }

    public RegistrationPageLess7 checkResult(String key, String value) {
        if (value == null || value.isBlank()) return this;
        checkFillFormComponentLess7.checkTable(key, value);
        return this;
    }




}




