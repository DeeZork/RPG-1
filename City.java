import java.util.ArrayList;
import java.util.List;

public class City extends Unit {
    private static String[] cityNames = new String[]{"Тьму Таракань", "Грязюкин", "Вазюнь", "Заболотин", "Круглячий", "Дальний",
            "Полянин", "Ближнин", "Стольнин"};

    private List<Market> market;

    public City(int stage) {
        super(cityNames[stage - 1], stage, (int) Math.floor(Math.random() * stage * 5) + 1);
//        наполнить город магазинами
        this.market = new ArrayList<>();
        for (int i = 0; i < getPower(); i++) {
            market.add(new Market("Магазин №" + (i + 1), (int) Math.floor(Math.random() * stage) + 1, (int) Math.floor(Math.random() * stage * getPower()) + 1));
        }
    }

    public void goToCity(Human human) {
        boolean flag=true;
        do {
            System.out.println("Город " + this.getName() + "\n" + human.getName() + "! Куда направимся?");
            for (int i = 0; i < this.getPower(); i++)
                System.out.println(i + 1 + ") " + this.market.get(i));
            System.out.println("X" + ") ТЕМНЫЙ ЛЕС");// ВЫВОДИТЬ НАЗВАНИЕ ЛЕСА

            String choice = Checker.check(this.getPower());
            switch (choice) {
                case "Q":{
                    flag=false;
                    break;
                }
//            case "X":{ goToForest(human);
//                    break;}
                default:
                    this.market.get(Integer.parseInt(choice)-1).shoping(human);
            }
        }while (flag);
    }
}
