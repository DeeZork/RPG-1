import java.io.*;

public class Main {

    public static void newGame() {
        System.out.println("\n\n\n\nДавным-давно это было! " +
                "\nЛеса захватла Нечисть Поганая! И сладить с ней никто был не в силах. " +
                "\nВ ту пору местными городами правил злобный князь, а звали его - Вырубка. " +
                "\nИ любил этот Вырубка девиц молоденьких похищать, в неволе у себя держать да поганить." +
                "\nИ так случилось, что красавицу Тьму Тараканьскую тоже похитил..." +
                "\nА чтобы спасти ее, надо добраться до славного города Стольнина, пройти десятью лесами темными, нечестью кишащими, да через десять городов славных!" +
                "\nИщу я богатыря могучего, который согласиться отправиться в те времена давние да в те места лютые, от Вырубки окоянного да от Нечести поганой нас избавить " +
                "\nВставай же с печи молодой удалец! Нам помоги, да красавицу выручи!!!" +
                "\nА звать то тебя как?\n");
        City city = new City(1);
        Human human = new Human(Checker.check(), city);
        System.out.println("Удачи тебе, " + human.getName() + "- богатырь и скатертью дорога!\n");
        city.goToCity(human);
    }

    public static void loadGame() {
        try {
            FileInputStream fis = new FileInputStream("RPGfile.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Human human = (Human) ois.readObject();
            fis.close();
            ois.close();
            City city=new City(human.getPlace().getStage());
            city.goToCity(human);
        } catch (Exception ex) {
            System.out.println("Возникла ошибка во время чтения, проверьте данные.");
            throw new RuntimeException();
        }
    }


    public static void startGame() {
        String choice;
        do {
            System.out.println("\n1) Новая игра\n" +
                    "2) Загрузить игру\n" +
                    "X) Выход\n" +
                    "Чего изволите?\n");
            choice = Checker.check(2);
            switch (choice) {
                case "1":
                    newGame();
                case "2": {//реализовать загрузку состояния игры из файла
                    loadGame();
                    break;
                }
                case "Q":
                    System.out.println("Прости... Удалец, не разобрал...");
            }
        }
        while (!choice.equals("X"));
        System.exit(0);

    }

    public static void main(String[] args) {
        startGame();
    }
}
