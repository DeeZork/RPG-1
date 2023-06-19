import java.util.ArrayList;
import java.util.List;

public class City extends Unit {
    public static List<City> region=new ArrayList<>();
    private static String[] cityNames = new String[]{"ТЬМУТАРАКАНЬ", "ГРЯЗЮКИН", "ВАЗЮНЬ", "ЗАБОЛОТИН", "КРУГЛЯЧИЙ", "ДАЛЬНИЙ",
            "ПОЛЯНИН", "ЖЕЛУДЕВ", "БЛИЖНИЙ", "СТОЛЬНИН"};

    private List<Market> market;
    private Forest forest;

    public City(int stage) {
        super(cityNames[stage - 1], stage, (int) Math.floor(Math.random() * stage * 5) + 1);
//        наполняем город магазинами
        this.market = new ArrayList<>();
        for (int i = 0; i < getPower(); i++) {
            market.add(new Market("Магазин №" + (i + 1), (int) Math.floor(Math.random() * stage) + 1, (int) Math.floor(Math.random() * stage * getPower()) + 1));
        }
        region.add(this);
        this.forest = new Forest(this, stage);//Лес вокруг города
    }

    @Override
    public String toString() {
        return "ГОРОД "+this.getName();
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
            choice = Checker.check(3);
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
                case "X":
                    System.exit(0);
            }
        }
    }

    public void goToCity(Human human) {
        human.setPlace(this);
        while (true) {
            System.out.println(this + "\n" + human.getName() + "! Куда направимся?");
            for (int i = 0; i < this.getPower(); i++)
                System.out.println(" " + (i + 1) + ") " + this.market.get(i));
            System.out.println(" Q) Постоялый двор\n X) "+forest);
            String choice = Checker.check(this.getPower());
            switch (choice) {
                case "Q":
                    hostel(human);
                case "X":
                    forest.goToForest(human);
                default:
                    this.market.get(Integer.parseInt(choice) - 1).shopping(human);
            }
        }
    }
}
