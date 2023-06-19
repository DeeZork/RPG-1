import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Forest extends Unit {

    public Forest(City city, int stage) {// stage - максимальный уровень монстров в лесу
        super(city.getName() + "СКИЙ ЛЕС", stage, stage + 2); // power -  максимальное количество монстров в битве
        if (stage < 10)
            City.region.add(new City(stage + 1));
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public void goToForest(Human human) {
        while (true) {
            System.out.println(this.getName() + "\n" + human.getName() + "! Куда направимся?");
            int i = 1;
            for (int j = 0; j < 3; j++) {
                int k = this.getStage() - 2 + j;
                if (!((k < 0) || (k > 9)))
                    System.out.println(" " + (i++) + ") " + City.region.get(k));
            }
            System.out.println(" " + i + ") Охота на Монстров");
            String choice = Checker.check(i);
            if ((choice.equals("X")) || (choice.equals("Q")))
                System.out.println("Эко перетрухнул! Вот тебя колбасит, что слов не разобрать...");
            if (choice.equals(i))
                battle(human);
        }
    }


    public void battle(Human human) {
        List<Fighter> fighters = new ArrayList<>();
        List<Fighter> baddies = new ArrayList<>();
        fighters.add(human);
        Random random=new Random();
        for (int i = 0; i < random.nextInt(human.getStage()*3) + 1; i++)
            switch (random.nextInt(2)) {
                case 0: {
                    baddies.add(new Skeleton(this.getStage(),human));
                    (Skeleton) baddies.get(i).start();
                    break;
                }
                case 1: {
                    baddies.add(new Goblin(this.getStage(),human));
                    baddies.get(i).fight(fighters);
                }
            }
        human.fight(baddies);
    }
}
