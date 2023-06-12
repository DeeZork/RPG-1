import java.util.ArrayList;
import java.util.List;

public class City extends Unit {
    private static String[] cityNames = new String[]{"Тьму Таракань", "Грязюкин", "Вазюнь", "Заболотин", "Круглячий", "Дальний",
            "Полянин", "Ближнин", "Стольнин"};

    private List<Market> market;

    public City(int stage) {
        super(cityNames[stage - 1], stage, (int) Math.floor(Math.random() * stage * 5) + 1);
//        наполняем город магазинами
        this.market = new ArrayList<>();
        for (int i = 0; i < getPower(); i++) {
            market.add(new Market("Магазин №" + (i + 1), (int) Math.floor(Math.random() * stage) + 1, (int) Math.floor(Math.random() * stage * getPower()) + 1));
        }
    }
    private void hostel(Human human) {
        String choice;
        while (true) {
            System.out.println("1) Новая игра\n" +
                    "2) Загрузить игру\n" +
                    "3) Сохранить игру\n" +
                    "Q) Отправиться в город\n" +
                    "X) Выход\n" +
                    "Чего изволите?\n");
            choice = Checker.check(2);
            switch (choice) {
                case "1": {
                    Main.newGame();
                    break;
                }
                case "2": {//реализовать загрузку состояния игры из файла
                    break;
                }
                case "3": {//реализовать загрузку состояния игры из файла
                    break;
                }
                case "Q":
                    goToCity(human);
                case "X": System.exit(0);
            }
        }
    }

    public void goToCity(Human human) {
        human.setPlace(this);
        boolean flag = true;
        do {
            System.out.println("Город " + this.getName() + "\n" + human.getName() + "! Куда направимся?");
            for (int i = 0; i < this.getPower(); i++)
                System.out.println(i + 1 + ") " + this.market.get(i));
            System.out.println("Q) Постоялый двор\nX) ТЕМНЫЙ ЛЕС");// ВЫВОДИТЬ НАЗВАНИЕ ЛЕСА

            String choice = Checker.check(this.getPower());
            switch (choice) {
                case "Q":
                    hostel(human);
//            case "X":{ goToForest(human);
//                    break;}
                default:
                    this.market.get(Integer.parseInt(choice) - 1).shoping(human);
            }
        } while (flag);
    }
}
