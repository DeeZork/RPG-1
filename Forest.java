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
        Random random = new Random();
        if (random.nextInt(100) > 65) {
            City city = new City(cityStage);
            city.goToCity(human);
        } else {
            System.out.println("А вот и засада...! Леса то не спокойные.");
            battle(human);
        }
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
                        City city = new City(1);
                        city.goToCity(human);
                    } else {
                        goingTryToCity(human.getPlace().getStage() - 1, human);
                    }
                }
                case "2": {
                    if (human.getPlace().getStage() == 10) {
                        City city = new City(10);
                        city.goToCity(human);
                    } else if (human.getPlace().getStage() == 1) {
                        goingTryToCity(human.getPlace().getStage() + 1, human);
                    } else {
                        City city = new City(human.getPlace().getStage());
                        city.goToCity(human);
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
        boolean flag = true;
        do {
            for (Fighter monster : baddies)
                System.out.println(" " + monster);
            System.out.println("\n " + human);
            System.out.println("Твои действия?\n1) Заглянуть в Заплечный мешок\nQ) Вбой!\nX) Попытаться убежать");
            switch (Checker.check(1)) {
                case "1": {
                    human.dressed();
                    break;
                }
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
                        flag = false;
                        if ((human.getSkill() / human.getStage()) > 100 && human.getStage() <= 10) {
                            human.setStage(human.getStage() + 1);// Увеличение уровня
                            System.out.println("Поздравляю! " + human.getName() + " достиг Уровня " + human.getStage());
                        }
                    } else {
                        human = null;
                        Main.startGame();
                    }
                    int monstersMoney = 0;
                    List<Thing> trophies = new ArrayList<>();
                    for (Fighter monster : baddies) {
                        monstersMoney += monster.getWalet();
                        if (!monster.getRight().getName().equals(Arms.getNamesArm(0, 0)))
                            trophies.add(monster.getRight());
                        if (!monster.getLeft().getName().equals(Arms.getNamesArm(0, 0)))
                            trophies.add(monster.getLeft());
                        for (int i = 0; i < monster.getBackPack().getThings().size(); i++)
                            trophies.add(monster.getBackPack().getThings().get(i));
                    }
                    trophies.sort((a, b) -> a.getClass().getSimpleName().compareTo(b.getClass().getSimpleName())); //Сортировка объектов по классу
                    getTrophies(human, monstersMoney, trophies);//Соберем трофеи
                }
            }
        } while (flag);
    }

    private void getTrophies(Human human, int monstersMoney, List<Thing> trophies) {
        while (true) {
            System.out.println("Твой мешок загружен на "+human.getBackPack().getFullThingsWeigt()+"кг из "+
                    human.getBackPack().getPower()+" кг возможных");
            System.out.println("Трофеи:");
            for (int i = 0; i < trophies.size(); i++)
                System.out.println(" " + (i + 1) + ") " + trophies.get(i));
            if (monstersMoney > 0)
                System.out.println(" " + (trophies.size() + 1) + ") Монеты -" + monstersMoney);
            System.out.println(" Q) Выбросить вещи из мешка\n X) Ухожу\nЧто берем?");
            String choice = Checker.check(trophies.size() + 1);
            switch (choice) {
                case "X":
                    return;
                case "Q": {// Выбросить вещи из мешка
                    human.dispose();
                    break;
                }
                default: {
                    if ((trophies.size() + 1) == Integer.parseInt(choice)) {
                        human.setWalet(human.getWalet() + monstersMoney);
                        monstersMoney = 0;
                        break;
                    } else {
                        Thing thingChoice = trophies.get(Integer.parseInt(choice)-1);
                        if (Arrays.asList(Potion.potionsName).contains(thingChoice.getName())) {
                            human.getBackPack().getThings().add(thingChoice);
                            trophies.remove(thingChoice);
                            break;
                        } else {
                            if ((human.getBackPack().getFullThingsWeigt() + ((Arms) thingChoice).getWeight()) <= human.getBackPack().getPower()) {
                                human.getBackPack().getThings().add(thingChoice);
                                trophies.remove(thingChoice);
                                break;
                            } else System.out.println("Мешок полон! Может что выбросишь?");
                        }
                    }
                }
            }
        }
    }
}
