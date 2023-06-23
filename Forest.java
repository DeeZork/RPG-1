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

    private void goingTryToCity(int cityStage, Human human) {
        City city = new City(cityStage);
        city.goToCity(human);
    }

    public void goToForest(Human human) {
        Random random = new Random();
        int attempt = 0;
        while (true) {
            System.out.println(this.getName() + "\n" + human.getName() + "! Куда направимся?");
            int i = 1;
            for (int j = 0; j < 3; j++) {
                int k = this.getStage() - 2 + j;
                if (!((k < 0) || (k > 9)))
                    System.out.println(" " + (i++) + ") " + City.region.get(k));
            }
            System.out.println(" X) Охота на Монстров\n Q) Заплечный мешок");
            switch (Checker.check(i)) {
                case "X": {
                    battle(human);
                    break;
                }
                case "Q": {
                    human.dressed();
                    break;
                }
                case "1": {
                    if (human.getPlace().getStage() == 1) {
                        goingTryToCity(1, human);
                    } else {
                        goingTryToCity(human.getPlace().getStage() - 1, human);
                    }
                }
                case "2": {
                    if (human.getPlace().getStage() == 10) {
                        goingTryToCity(10, human);
                    } else if (human.getPlace().getStage() == 1) {
                        goingTryToCity(human.getPlace().getStage() + 1, human);
                    } else {
                        goingTryToCity(human.getPlace().getStage(), human);
                    }
                }
                case "3": {
                    if (human.getPlace().getStage() == 10) {
                        goingTryToCity(10, human);
                    } else {
                        goingTryToCity(human.getPlace().getStage() + 1, human);
                    }
                }
            }
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
        System.out.println("\n " + human);
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
                for (Fighter monster : baddies)
                    monster.go();
                human.fight();
                if (human.getLive() > 0) {
                    System.out.println("\nНаша взяла! Опыт стал +" + human.getSkill() + " Здоровье: " + human.getLive() + "\n");
                    if ((human.getSkill() / human.getStage()) > 100 && human.getStage() <= 10) {
                        human.setStage(human.getStage() + 1);// Увеличение уровня
                        System.out.println("Поздравляю! " + human.getName() + " достиг Уровня " + human.getStage());
                    }
                }
//                try {
//                    Thread.sleep(2000);//}
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                for (Fighter monster : baddies)
//                    monster.thread.interrupt();
                if (human.getLive() <= 0) {
                    human = null;
                    Main.startGame();
                }
            }
        }
    }
}
