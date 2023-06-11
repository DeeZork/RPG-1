import java.util.Scanner;

public class Main {
    public static void startMenu() {
        String choice;
        do {
            System.out.println("1) Новая игра \n" +
                    "2) Загрузить игру \n" +
                    "3) Сохранить игру \n" +
                    "X) Выход\n" +
                    "Чего изволите?\n");
            choice = Checker.check(3);
            switch (choice) {
                case "1": {
                    newGame();
                    break;
                }
                case "2": {
                    break;
                }
                case "3": {
                    break;
                }
                case "Q":
                    System.out.println("Прости... Удалец! Не понять... Повтори поразборчивее!");
            }
        }
        while (!choice.equals("X"));
        System.exit(0);
    }

    public static void newGame() {
        System.out.println("\n\n\n\n\n\n\nДавным-давно это было! " +
                "\nЛеса захватла Нечисть Поганая! И сладить с ней никто был не в силах. " +
                "\nВ ту пору местными городами правил злобный князь, а звали его - Вырубка. " +
                "\nИ любил этот Вырубка девиц молоденьких похищать, да в неволе у себя держать " +
                "\nи так случилось, что красавицу Тьму Тараканьскую тоже похитил..." +
                "\nА чтобы спасти ее, надо добраться до славного города Стольнина, пройти лесами темными, нечестью кишащими, да через города славные!" +
                "\nВставай с печи молодой удалец! Пора красавицу выручать!!!" +
                "\nА звать то тебя как?\n");
        Human human = new Human(Checker.check());
        System.out.println("Удачи тебе, "+human.getName()+"!\n");
        City city = new City(1);
        city.goToCity(human);
    }

    public static void main(String[] args) {
        startMenu();
    }
}
