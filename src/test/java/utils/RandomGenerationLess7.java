package utils;

import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerationLess7 {
    private static String day;
    private static String month;
    private static String year;

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
    }

    public static String getFirstName(){
        Faker faker = new Faker(new Locale("en-US"));
        return faker.name().firstName();
    }

    public static String getLastName(){
        Faker faker = new Faker(new Locale("en-US"));
        return faker.name().lastName();
    }

    public static String getEmail(){
        Faker faker = new Faker(new Locale("en-US"));
        return faker.internet().emailAddress();
    }

    public static void generateBirthday() {
        Faker faker = new Faker(new Locale("en-US"));
        Date date = faker.date().birthday();
        LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        day = String.format("%02d", localDate.getDayOfMonth());
        month = localDate.format(DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH)); // April, October...
        year = String.valueOf(localDate.getYear());
    }

    public static String getDay() {
        if (day == null) generateBirthday();
        return day;
    }

    public static String getMonth() {
        if (month == null) generateBirthday();
        return month;
    }

    public static String getYear() {
        if (year == null) generateBirthday();
        return year;
    }


    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomPhone() {
        return String.format("%s%s%s%s", getRandomInt(1, 9), getRandomInt(111, 999),
                getRandomInt(111, 999), getRandomInt(111, 999));
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};

        return getRandomItemFromArray(genders);
    }

    public static String getRandomItemFromArray(String[] array) {
        int index = getRandomInt(0, array.length - 1);

        return array[index];
    }

    public static String getRandomHobbies() {
        String[] hobbies = {"Sports", "Reading", "Music"};

        List<String> list = new ArrayList<>(Arrays.asList(hobbies));
        Collections.shuffle(list);

        int count = new Random().nextInt(hobbies.length + 1);

        return String.join(", ", list.subList(0, count));

    }

    private static final String[] SUBJECTS = {
            "Maths","Accounting","Arts","Social Studies","Physics","Chemistry",
            "Computer Science","Commerce","Economics","Civics","English","Hindi","Biology","History"
    };

    public static String getRandomSubject() {
        return getRandomSubject(0, SUBJECTS.length);
    }

    public static String getRandomSubject(int count) {
        int n = Math.max(0, Math.min(count, SUBJECTS.length));
        List<String> list = new ArrayList<>(Arrays.asList(SUBJECTS));
        Collections.shuffle(list);
        return String.join(", ", list.subList(0, n));
    }

    public static String getRandomSubject(int min, int max) {
        int hi = Math.min(max, SUBJECTS.length);
        int lo = Math.max(0, Math.min(min, hi));
        int n = lo + new java.util.Random().nextInt(hi - lo + 1);
        return getRandomSubject(n);
    }

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

    public static String getPicture() {
        return PICTURES[new Random().nextInt(PICTURES.length)];
    }


    public static String getAddress(){
        Faker faker = new Faker(new Locale("en-US"));
        return faker.address().streetAddress();
    }

    public static String getState() {
        String[] state = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

        return getRandomItemFromArray(state);

    }

    public static String getCity(String state) {
        return switch (state) {
            case "NCR" -> getRandomItemFromArray(new String[]{"Delhi", "Gurgaon", "Noida"});
            case "Uttar Pradesh" -> getRandomItemFromArray(new String[]{"Agra", "Lucknow", "Merrut"});
            case "Haryana" -> getRandomItemFromArray(new String[]{"Karnal", "Panipat"});
            case "Rajasthan" -> getRandomItemFromArray(new String[]{"Jaipur", "Jaiselmer"});
            default -> throw new IllegalArgumentException("Unknown state: " + state);
        };

    }
}