import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Human extends Fighter {
    private int experience;
    private City place;
    private static String[] whoop = new String[]{"Так тебе!", "Получай!!", "Пошла раздача!!!"};

    public Human(String name, City place) {
        super(name, 1, 5, 1000, new BackPack(1, 5, 5), whoop, 200, 10,
                new Arms(Arms.getNamesArm(0, 5), 1, 50, 0, 20, 5),
                new Arms(Arms.getNamesArm(0, 4), 1, 20, 0, 50, 8));
        this.experience = 0;
        this.place = place;
    }
    @Override
    public String toString() {
        return this.getName()+" Ур."+this.getStage()+", Сила-"+this.getPower()+", В правой-"+this.getRight().getName()+
                ", В левой-"+this.getLeft().getName();
    }

    public void setPlace(City place) {
        this.place = place;
    }

    @Override
    public void setFighters(List<Fighter> fighters) {
        super.setFighters(fighters);
    }

    public void dressed() {
        while (true) {
            System.out.println("Вот, что мы имеем:\n Здоровье +" + this.getLive() + "\n Монеты +" + this.getWalet());
            System.out.println("Предметы в Заплечном мешке:");
            if (this.getBackPack().getThings().size() > 0) {
                for (Thing thing : this.getBackPack().getThings()) {
                    System.out.println(" " + thing);
                }
            }
            System.out.println("Правая рука - " + this.getRight().getName() + " Атака +" + this.getRight().getPower() + " Защита +" + this.getRight().getProtection());
            System.out.println("Левая рука - " + this.getLeft().getName() + " Атака +" + this.getLeft().getPower() + " Защита +" + this.getLeft().getProtection());
            System.out.println(
                    " 1) Взять оружие правой рукой\n" +
                            " 2) Взять оружие левой рукой\n" +
                            " 3) Выпить зелье\n" +
                            " Q) Положить оружие в Заплечный мешок\n" +
                            " X) Выход");
            String choice = Checker.check(3);
            switch (choice) {
                case "X":
                    return;
                case "Q": {//Кладем оружие в мешок
                    int k = 0;
                    if (!this.getRight().getName().equals(Arms.getNamesArm(0, 0)))
                        System.out.println(" " + (++k) + ") " + this.getRight());
                    if (!this.getLeft().getName().equals(Arms.getNamesArm(0, 0)))
                        System.out.println(" " + (++k) + ") " + this.getLeft());
                    if (k == 0) System.out.println("У тебя в руках ничего нет!");
                    else {
                        System.out.println("Что убираем?");
                        String choiceHands = Checker.check(k);
                        if ((choiceHands.equals("1")) && (!this.getRight().getName().equals(Arms.getNamesArm(0, 0)))) {
                            if (this.getBackPack().getFullThingsWeigt() + this.getRight().getWeight() > this.getBackPack().getPower()) {
                                System.out.println("Твой мешок не выдержит такой нагрузки");
                                break;
                            } else {
                                this.getBackPack().getThings().add(this.getRight());
                                this.setRight(new Arms(Arms.getNamesArm(0, 0), 1, 1, 0, 1, 0));
                            }
                        } else {
                            if (this.getBackPack().getFullThingsWeigt() + this.getLeft().getWeight() > this.getBackPack().getPower()) {
                                System.out.println("Твой мешок не выдержит такой нагрузки");
                                break;
                            } else {
                                this.getBackPack().getThings().add(this.getLeft());
                                this.setLeft(new Arms(Arms.getNamesArm(0, 0), 1, 1, 0, 1, 0));
                            }
                        }
                    }
                    break;
                }
                case "1": {
                    Arms arm = (Arms) takeFromBackPack("Arms");
                    if (arm != null) {
                        if (!this.getRight().getName().equals(Arms.getNamesArm(0, 0)))
                            this.getBackPack().getThings().add(this.getRight());
                        this.setRight(arm);
                        this.getBackPack().getThings().remove(arm);
                    }
                    break;
                }
                case "2": {
                    Arms arm = (Arms) takeFromBackPack("Arms");
                    if (arm != null) {
                        if (!this.getLeft().getName().equals(Arms.getNamesArm(0, 0)))
                            this.getBackPack().getThings().add(this.getLeft());
                        this.setLeft(arm);
                        this.getBackPack().getThings().remove(arm);
                    }
                    break;
                }
                default: {
                    Potion pot = (Potion) takeFromBackPack("Potion");
                    if (pot != null) {
                        this.setLive(this.getLive() + pot.getPower());
                        this.getBackPack().getThings().remove(pot);
                        if ((this.getLive() > (this.getStage() * 10))) this.setLive(this.getStage() * 10);
                    }
                }
            }
        }
    }

    private Thing takeFromBackPack(String classOfThing) {
        List<Thing> takeFromBackPack = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < this.getBackPack().getThings().size(); i++) {
            Class<?> clazz = this.getBackPack().getThings().get(i).getClass();
            String clazzName = clazz.getSimpleName();
            if (clazzName.equals(classOfThing)) {
                takeFromBackPack.add(this.getBackPack().getThings().get(i));
                System.out.println(" " + (++j) + ") " + takeFromBackPack.get(j - 1));
            }
        }
        if (j == 0) {
            System.out.println("В мешке нет ничего подходящего...");
            return null;
        }
        System.out.println(" X) Отмена");
        System.out.println("Что берем?");
        String choiceTake;
        do {
            choiceTake = Checker.check(j);
            if (choiceTake.equals("Q"))
                System.out.println("Что то ты заговариваешься... Можно поточнее, что именно берем?");
        } while (choiceTake.equals("Q"));
        if (choiceTake.equals("X")) return null;
        this.getBackPack().getThings().remove(choiceTake);
        return takeFromBackPack.get(Integer.parseInt(choiceTake) - 1);
    }

}
