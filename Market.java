import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Market extends Unit {
    private List<Thing> stock;

    public Market(String name, int stage, int power) {
        super(name, stage, power);
//        stage - Уровень магазина определяет максимальный уровень вещей в нем
//        power - определяет количество вещей в продаже
        this.stock = new ArrayList<>();
        for (int i = 0; i < power; i++) {
            switch ((int) Math.floor(Math.random() * 3)) {
                case 0: {
                    int stageBP = (int) Math.floor(Math.random() * stage) + 1;// уровень
                    int bpp = (int) Math.round(Math.random() * 5 * stage) + 10;// грузоподъемность рюкзака
                    stock.add(new BackPack(stageBP, bpp, stage * bpp * 2));
                    break;
                }
                case 1: {
                    int typA = (int) Math.floor(Math.random() * 2);
                    int nameA = (int) Math.ceil(Math.random() * 6);// имя,всего 7 видов оружия/защиты
                    int stageA = (int) Math.floor(Math.random() * stage) + 1;// уровень
                    int powerA;
                    int protectA;
                    if (typA == 0) {
                        powerA = (int) Math.round(Math.random() * stage * nameA * 10) + 1;// сила атаки
                        protectA = (int) Math.round(Math.random() * stage * nameA * 3) + 1;// защита
                    } else {
                        powerA = (int) Math.round(Math.random() * stage * nameA * 3) + 1;// сила атаки
                        protectA = (int) Math.round(Math.random() * stage * nameA * 10) + 1;// защита
                    }
                    int weigtA = (int) Math.round(Math.random() * stage * nameA * (typA + 1) * 1.5) + 1;//вес
                    int priceA = (int) Math.round(Math.random() * stage * (powerA + protectA - weigtA + 1)) + 1;// цена
                    stock.add(new Arms(Arms.getNamesArm(typA, nameA), stageA, powerA, priceA, protectA, weigtA));
                    break;
                }
                case 2: {
                    int stageP = (int) Math.floor(Math.random() * stage) + 1;// уровень
                    int powerP = (int) Math.floor(Math.random() * 3) + 1;// уровень
                    int priceP = (int) Math.floor(Math.random() * stage * powerP * 10) + 1;// уровень
                    stock.add(new Potion(stageP, powerP, priceP));
                }
            }
        }
//        stock.sort((a, b) -> a.getClass().compareTo(b.getClass())); Разобраться с сортировкой объектов по классу
    }

    @Override
    public String toString() {
        return getName() + " товары " + getStage() + " уровня";
    }

    private boolean buy(Human human, Thing thing) {
        if (thing.getPrice() > human.getWalet())
            System.out.println("Поднакопи сперва монет, тогда и приходи!");
        else if (thing.getName().equals(BackPack.name)) {
            int offer = (int) (Math.random() * human.getBackPack().getPrice());
            System.out.println("За твой мешок дам " + offer + " по рукам? (Q -Да/X -нет)");
            if (Checker.check(0).equals("Q")) {
                BackPack forChange = new BackPack(100, 1000, 0, human.getBackPack().getThings());
                human.setWalet(human.getWalet() - thing.getPrice() + offer);
                human.getBackPack().setThings(new ArrayList<>());
                stock.add(human.getBackPack());
                human.setBackPack((BackPack) thing);
                human.getBackPack().setThings(forChange.getThings());
                return true;
            }
        } else if (Arrays.asList(Potion.potionsName).contains(thing.getName())) {
            human.setWalet(human.getWalet() - thing.getPrice());
            human.getBackPack().getThings().add(thing);
            return true;

        } else {
            if ((human.getBackPack().getFullThingsWeigt() + ((Arms) thing).getWeight()) <= human.getBackPack().getPower()) {
                human.setWalet(human.getWalet() - thing.getPrice());
                human.getBackPack().getThings().add(thing);
                return true;
            } else if (human.getRight().equals(Arms.getNamesArm(0, 0))) {
                human.setWalet(human.getWalet() - thing.getPrice());
                human.setRight((Arms) thing);
                return true;
            } else if (human.getLeft().equals(Arms.getNamesArm(0, 0))) {
                human.setWalet(human.getWalet() - thing.getPrice());
                human.setLeft((Arms) thing);
                return true;
            } else System.out.println("Ты и так полон под завязку! Может что продать пожелаешь? Предложи свой товар!");
        }
        return false;
    }

    private boolean sale(Human human) {
        System.out.println("Это значит твои товары... Ну так что продаешь?");
        while (true) {
            int num = 1;
            if (human.getBackPack().getThings().size() > 0) {
                num = 0;
                for (int i = 0; i < human.getBackPack().getThings().size(); i++)
                    System.out.println(i + 1 + ") " + human.getBackPack().getThings().get(i));
            }
            if (!(human.getRight().getName().equals(Arms.getNamesArm(0, 0))))
                System.out.println(human.getBackPack().getThings().size() + num++ + ") " + human.getRight());
            if (!(human.getLeft().getName().equals(Arms.getNamesArm(0, 0))))
                System.out.println(human.getBackPack().getThings().size() + num + ") " + human.getLeft());
            System.out.println("X) Ничего не продаю");
            String choice = Checker.check(human.getBackPack().getThings().size() + num);
            switch (choice) {
                case "X":
                    return false;
                default: {
                    if (Integer.parseInt(choice) <= human.getBackPack().getThings().size()) {//продажа содержимого рюкзака
                        int offer = (int) (Math.random() * human.getBackPack().getThings().get(Integer.parseInt(choice) - 1).getPrice());
                        System.out.println("За твой товар плачу " + offer + " по рукам? (Q -Да/X -нет)");
                        if (Checker.check(0).equals("Q")) {
                            human.setWalet(human.getWalet() + offer);
                            stock.add(human.getBackPack().getThings().get(Integer.parseInt(choice) - 1));
                            human.getBackPack().getThings().remove(human.getBackPack().getThings().get(Integer.parseInt(choice) - 1));
                            return true;
                        }
                    } else if ((!human.getRight().getName().equals(Arms.getNamesArm(0, 0))) &&
                            (Integer.parseInt(choice) == (human.getBackPack().getThings().size() + num))) {
                        int offer = (int) (Math.random() * human.getRight().getPrice());
                        System.out.println("За твой товар плачу " + offer + " по рукам? (Q -Да/X -нет)");
                        if (Checker.check(0).equals("Q")) {
                            human.setWalet(human.getWalet() + offer);
                            stock.add(human.getRight());
                            human.setRight(new Arms(Arms.getNamesArm(0, 0), 1, 1, 0, 1, 0));
                            return true;
                        }
                    } else {
                        int offer = (int) (Math.random() * human.getLeft().getPrice());
                        System.out.println("За твой товар плачу " + offer + " по рукам? (Q -Да/X -нет)");
                        if (Checker.check(0).equals("Q")) {
                            human.setWalet(human.getWalet() + offer);
                            stock.add(human.getRight());
                            human.setLeft(new Arms(Arms.getNamesArm(0, 0), 1, 1, 0, 1, 0));
                            return true;
                        }
                    }
                }
            }
        }
    }

    public void shopping(Human human) {
        System.out.println(this.getName());
        while (true) {
            System.out.println("Товары в продаже:");
            for (int i = 0; i < this.stock.size(); i++)
                System.out.println(i + 1 + ") " + this.stock.get(i));
            System.out.println("Q) Предложи свой товар, готов дать хорошую цену!\nX) Выход");
            String choice = Checker.check(this.stock.size());
            switch (choice) {
                case "X":
                    return;
                case "Q": {//Продаем
                    if (sale(human)) {
                        System.out.println("Эх! Переплатил я тебе сильно, ну да ладно!\nЧто то еще?");
                    }
                    break;
                }
                default: {//Покупаем
                    if (buy(human, stock.get(Integer.parseInt(choice) - 1))) {
                        stock.remove(Integer.parseInt(choice) - 1);
                        System.out.println("Приятно с тобой иметь дела, Удалец!\nЧто то еще?");
                    }
                }
            }
        }

    }


}
