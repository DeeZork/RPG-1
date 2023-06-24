import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

class Human extends Fighter {

    private City place;
    private static String[] whoop = new String[]{"Так тебе!", "Получай!!", "Пошла раздача!!!"};

    public Human(String name, City place) {
        super(name, 1, 5, 100, new BackPack(1, 5, 5), whoop, 100, 5,
                new Arms(Arms.getNamesArm(0, 0), 1, 1, 0, 1, 0),
                new Arms(Arms.getNamesArm(0, 0), 1, 1, 0, 1, 0));
        this.place = place;
    }

    @Override
    public String toString() {
        return this.getName() + " Ур." + this.getStage() + ", Здоровье-" + this.getLive() + ", В правой-" + this.getRight().getName() +
                ", В левой-" + this.getLeft().getName();
    }

    public void setPlace(City place) {
        this.place = place;
    }

    public City getPlace() {
        return place;
    }

    @Override
    public void setFighters(List<Fighter> fighters) {
        super.setFighters(fighters);
    }

    public void writeGame() {
        try {
            FileOutputStream fos = new FileOutputStream(new File("RPGfile.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            fos.close();
            oos.close();
        } catch (Exception ex) {
            System.out.println("Возникла ошибка во время записи, проверьте данные.");
            throw new RuntimeException();

        }
    }

    public void dressed() {
        while (true) {
            System.out.println("Вот, что мы имеем:\n Здоровье +" + this.getLive() + "\n Монеты +" + this.getWalet());
            printHumanThings();
            int j = 3;
            System.out.println("\nЧто желаешь сделать?");
            System.out.println(
                    " 1) Взять оружие правой рукой\n" +
                            " 2) Взять оружие левой рукой\n" +
                            " 3) Выпить зелье");
            if (!this.getBackPack().getThings().isEmpty()) {
                System.out.println(" 4) Выбросить вещь");
                j++;
            }
            System.out.println(
                    " Q) Положить оружие в Заплечный мешок\n" +
                            " X) Выход");
            String choice = Checker.check(j);
            switch (choice) {
                case "X":
                    return;
                case "Q": {//Кладем оружие в мешок
                    int k = 0;
                    if (!this.getRight().getName().equals(Arms.getNamesArm(0, 0)))
                        System.out.println(" " + (++k) + ") " + this.getRight());
                    if (!this.getLeft().getName().equals(Arms.getNamesArm(0, 0)))
                        System.out.println(" " + (++k) + ") " + this.getLeft());
                    if (k == 0)
                        System.out.println("У тебя в руках ничего нет!");
                    else {
                        System.out.println(" X) Передумал");
                        System.out.println("Что убираем?");
                        String choiceHands = Checker.check(k);
                        if ((choiceHands.equals("X")||(choiceHands.equals("Q")))) break;
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
                case "3": {
                    Potion pot = (Potion) takeFromBackPack("Potion");
                    if (pot != null) {
                        this.setLive(this.getLive() + pot.getPower());
                        this.getBackPack().getThings().remove(pot);
                        if ((this.getLive() > (this.getStage() * 100))) this.setLive(this.getStage() * 100);
                    }
                    break;
                }
                default: {//выбрасываем
                    dispose();
                }
            }
        }
    }

    private int printHumanThings() {
        int i = 0;
        System.out.println("Все твои предметы:");
        System.out.println(" " + (++i) + ") " + "Правая рука - " + this.getRight());
        System.out.println(" " + (++i) + ") " + "Левая рука - " + this.getLeft());
        if (!this.getBackPack().getThings().isEmpty()) {
            for (Thing thing : this.getBackPack().getThings()) {
                System.out.println(" " + (++i) + ") " + thing);
            }
        }
        return i;
    }

    public void dispose() {
        while (true) {
            int check = printHumanThings();
            System.out.println(" Q) Выбросить деньги\n X) Ничего не выбрасываем");
            System.out.println("Что выбрасываем?");
            String choice = Checker.check(check);
            System.out.println("Уверен? (Q-да/X-нет)");
            if (Checker.check(0).equals("Q"))
                switch (choice) {
                    case "X":
                        return;
                    case "Q": {
                        this.setWalet(0);
                        break;
                    }
                    case "1": {
                        this.setRight(new Arms(Arms.getNamesArm(0, 0), 1, 1, 0, 1, 0));
                        break;
                    }
                    case "2": {
                        this.setLeft(new Arms(Arms.getNamesArm(0, 0), 1, 1, 0, 1, 0));
                        break;
                    }
                    default: {
                        this.getBackPack().getThings().remove(this.getBackPack().getThings().get(Integer.parseInt(choice) - 3));
                    }

                }
        }
    }

    private Thing takeFromBackPack(String classOfThing) {
        List<Thing> takeFromBackPack = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < this.getBackPack().getThings().size(); i++) {
            String clazzName = this.getBackPack().getThings().get(i).getClass().getSimpleName();
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
