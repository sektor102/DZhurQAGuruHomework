package utils;

import com.github.javafaker.Faker;

import java.util.*;

public class RandomGenerationLess7 {
    private static final Faker faker = new Faker(new Locale("en-US"));
    private static String day;
    private static String month;
    private static String year;
    private static final String[] PICTURES = {
            "Witcher 3 Wild Hunt, The - avatar 01.jpg",
            "Witcher 3 Wild Hunt, The - avatar 02.jpg",
            "Witcher 3 Wild Hunt, The - avatar 03.jpg",
            "Witcher 3 Wild Hunt, The - avatar 04.jpg",
            "Witcher 3 Wild Hunt, The - avatar 05.jpg",
            "Witcher 3 Wild Hunt, The - avatar 06.jpg",
            "Witcher 3 Wild Hunt, The - avatar 07.jpg",
            "Witcher 3 Wild Hunt, The - avatar 08.jpg",
            "Witcher 3 Wild Hunt, The - avatar 09.jpg",
            "Witcher 3 Wild Hunt, The - avatar 10.jpg"
    };

    public static void main(String[] args) {
        System.out.println(getFirstName());
        System.out.println(getLastName());
        System.out.println(getEmail());
        System.out.println(getDay() + getMonth() + getYear());
        System.out.println(getRandomHobbies());
        System.out.println(getRandomPhone());
        System.out.println(getRandomGender());
        System.out.println(getRandomSubject());
        System.out.println(getState());
        System.out.println(getPicture());
    }

    public static String getFirstName(){
        return faker.name().firstName();
    }

    public static String getLastName(){
        return faker.name().lastName();
    }

    public static String getEmail(){
        return faker.internet().emailAddress();
    }

    public static void generateBirthday(int minYear, int maxYear) {
        int y = faker.number().numberBetween(minYear, maxYear + 1);
        int m = faker.number().numberBetween(1, 13);
        int d = faker.number().numberBetween(1, 29);

        year  = String.valueOf(y);
        month = java.time.Month.of(m)
                .getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH); // "March"
        day   = String.valueOf(d);
    }

    public static String getDay()   { if (day == null)   generateBirthday(1970, 2015); return day; }
    public static String getMonth() { if (month == null) generateBirthday(1970, 2015); return month; }
    public static String getYear()  { if (year == null)  generateBirthday(1970, 2015); return year; }


    public static String getRandomPhone() {
        return faker.number().digits(10);
    }


    public static String getRandomGender() {
        return faker.options().option("Male","Female","Other");
    }


    public static String getRandomHobbies() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public static String getRandomSubject() {
        return faker.options().option("Maths","Accounting","Arts","Social Studies","Physics","Chemistry",
                "Computer Science","Commerce","Economics","Civics","English","Hindi","Biology","History");
    }

    public static String getPicture() {
        return faker.options().option(PICTURES);
    }

    public static String getAddress(){
        return faker.address().streetAddress();
    }

    public static String getState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"); }
    public static String getCity (String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };

    }
}