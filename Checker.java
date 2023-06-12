import java.util.Scanner;

public class Checker {

    private static String inputHuman;                       //Слово игрока
    private static Scanner scan = new Scanner(System.in);

    private static String checkScan() {
        String checked;
        do {
            checked = scan.nextLine();                     //Игрок вводит слово
        } while (checked.equals(""));
        return checked;
    }

    public static String check(int num) {
        boolean check = true;
        do {
            inputHuman = checkScan();                     //Игрок вводит слово
            if (inputHuman.equals("X") || inputHuman.equals("x") || inputHuman.equals("Х") || inputHuman.equals("х")
                    || inputHuman.equals("ч") || inputHuman.equals("Ч")) {
                inputHuman = "X";
                check = false;
            } else if (inputHuman.equals("Q") || inputHuman.equals("q") || inputHuman.equals("Й") || inputHuman.equals("й")) {
                inputHuman = "Q";
                check = false;
            } else {
                try {
                    int k = Integer.parseInt(inputHuman);
                    if ((0 < k) && (k <= num)) check = false;
                    else throw new Exception();
                } catch (Exception e) {
                    System.out.println("Прости... Удалец! Не понять... Повтори поразборчивее!");
                }
            }
        } while (check);
        return inputHuman;
    }

    public static String check() {
        boolean check = true;
        do {
            inputHuman = checkScan();
            System.out.println("Прошу простить, не расслышал... повторите!");
            if (inputHuman.toUpperCase().equals(checkScan().toUpperCase()))
                check = false;
            else System.out.println("...я прошу прощения, еще разочек почетче пожалуйста!");
        }
        while (check);
        return inputHuman.replaceFirst(String.valueOf(inputHuman.charAt(0)), String.valueOf(inputHuman.charAt(0)).toUpperCase());

    }
}