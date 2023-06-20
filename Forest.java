import java.util.*;

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
                System.out.println("Эко перетрухнул! Вот тебя колбасит что слов не разобрать...");
            if (i == Integer.parseInt(choice))
                battle(human);
        }
    }

    public void battle(Human human) {
        List<Fighter> fighters = new ArrayList<>();
        List<Fighter> baddies = new ArrayList<>();
        fighters.add(human);
        Random random = new Random();
        for (int i = 0; i < random.nextInt(human.getStage() * 3) + 2; i++)
            switch (random.nextInt(2)) {
                case 0: {
                    baddies.add(new Skeleton(this.getStage(), human));
                    break;
                }
                case 1:
                    baddies.add(new Goblin(this.getStage(), human));
            }
        System.out.println("Не прошло и пяти минут, а они уж тут как тут:");
        for (Fighter monster : baddies)
            System.out.println(" " + monster);
        System.out.println("\n "+human);
        System.out.println("Твои действия?\nQ) Вбой!\nX) Попытаться убежать");
        switch (Checker.check(0)) {
            case "X": {
                if (baddies.get(random.nextInt(baddies.size())).getSkill() < human.getSkill()) {
                    System.out.println("Ух! Удалось сбежать!!!");
                    return;
                }
                System.out.println("От Монстров не легко скрыться! В бой!!!");
            }
            case "Q": {
                human.setFighters(baddies);
                human.go();
                for (Fighter monster : baddies)
                    monster.go();
            }
        }
        if(human.getLive()>0) System.out.println("Наша взяла!");
    }
}
