package pages;

import pages.components.CalendarComponent;
import pages.components.CheckFillFormComponent;


import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    CheckFillFormComponent checkFillFormComponent = new CheckFillFormComponent();
    private String firstName, lastName, email, gender, phone, dayB, monthB, yearB, subject, hobby, picture, address, state, city;

    private String getDobUi() {
        return dayB + " " + monthB + "," + yearB;
    }

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

    return this;

    }

    public RegistrationPage firstName(String value){
        $("#firstName").setValue(value);
        this.firstName = value;
        return this;
    }

    public RegistrationPage lastName(String value){
        $("#lastName").setValue(value);
        this.lastName = value;
        return this;
    }

    public RegistrationPage email(String value){
        $("#userEmail").setValue(value);
        this.email = value;
        return this;
    }

    public RegistrationPage gender(String value){
        $("#genterWrapper").$(byText(value)).click();
        this.gender = value;
        return this;
    }

    public RegistrationPage numberPhone(String value){
        $("#userNumber").setValue(value);
        this.phone = value;
        return this;
    }

    public RegistrationPage dateOfBirth(String day, String month, String year){
        calendarComponent.setDate(day, month, year);
        this.dayB = day;
        this.monthB = month;
        this.yearB = year;
        return this;
    }

    public RegistrationPage subjects(String value){
        $("#subjectsContainer input").setValue(value).pressEnter();
        this.subject  = value;
        return this;
    }

    public RegistrationPage hobbies(String value){
        $("#hobbiesWrapper").$(byText(value)).click();
        this.hobby = value;
        return this;
    }

    public RegistrationPage picture(String value){
        $("#uploadPicture").uploadFromClasspath(value);
        this.picture = value;
        return this;
    }

    public RegistrationPage address(String value){
        $("#currentAddress").setValue(value);
        this.address = value;
        return this;
    }

    public RegistrationPage stateAndCity(String state, String city){
        $("#state").click();
        $("#state input").setValue(state).pressEnter();
        $("#city").click();
        $("#city input").setValue(city).pressEnter();
        this.state = state;
        this.city  = city;
        return this;
    }

    public RegistrationPage submit(){
        $("#submit").click();

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

    public void checkResultPartial(){
        checkFillFormComponent.partialCheck(firstName, lastName, gender, phone, getDobUi());
    }

    public void checkResultFull(){
        checkFillFormComponent.fullCheck(firstName, lastName, email, gender, phone, getDobUi(), subject, hobby, picture, address, state, city);
    }


    public void checkNegative(){
        checkFillFormComponent.negativeCheck();
    }

}




