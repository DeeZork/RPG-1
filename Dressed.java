import java.util.Scanner;

public class Dressed extends Thread {
    private static Scanner scan = new Scanner(System.in);
    private Human human;

    public Dressed(Human human) {
        this.human = human;
    }

    @Override
    public void run() {
        while (true) {
            String test;
            do {
                test = scan.nextLine();
            } while ((test.equals("T") || test.equals("t") || test.equals("Е") || test.equals("е")));
            human.dressed();
        }
    }
}
