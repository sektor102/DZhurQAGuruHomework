package Zhur.D;

public class HomeWorkJava1 {
    public static void main(String args[]) {
    int a = 15;
    int b = 5;
    double c = 5.5;
    double d = 0.0 / 0.0;

        // применить несколько арифметических операций ( + , -, * , /) над двумя примитивами типа int
        System.out.println(a + b);
        System.out.println(a - b);
        System.out.println(a * b);
        System.out.println(a / b);

        //применить несколько арифметических операций над int и double в одном выражении
        System.out.println(a * c);
        System.out.println(a + c);

        //применить несколько логических операций ( < , >, >=, <= )
        System.out.println(a > b);
        System.out.println(b >= c);

        //прочитать про диапазоны типов данных для вещественных / чисел с плавающей точкой (какие максимальные и минимальные значения есть, как их получить) и переполнение
        System.out.println(Float.MIN_VALUE);
        System.out.println(Float.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        System.out.println(Double.MAX_VALUE);
        System.out.println(Float.MAX_VALUE * 2);
        System.out.println(d);

        //получить переполнение при арифметической операции
        System.out.println(Integer.MAX_VALUE + 2);
        System.out.println(Integer.MIN_VALUE - 2);

    }

}






//0) применить несколько арифметических операций ( + , -, * , /) над двумя примитивами типа int

//1) применить несколько арифметических операций над int и double в одном выражении

//2) применить несколько логических операций ( < , >, >=, <= )

//3) прочитать про диапазоны типов данных для вещественных / чисел с плавающей точкой (какие максимальные и минимальные значения есть, как их получить) и переполнение

//4) получить переполнение при арифметической операции