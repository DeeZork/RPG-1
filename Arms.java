import java.util.Random;

class Arms extends Thing {
    private int protection;
    private int weight;
    private static Random random = new Random();
    public final static String[][] namesArm = new String[][]{{"Кулак", "Палка", "Нож", "Кинжал", "Короткий Меч", "Средний Меч", "Длинный Меч", "Булава"},
            {"Кулак", "Маленький деревянный щит", "Средний деревянный щит", "Большой деревянный щит",
                    "Маленький железный щит", "Средний железный щит", "Большой железный щит", "Заговоренный щит"}};


    public int getProtection() {
        return protection;
    }

    public int getWeight() {
        return weight;
    }

    public static String getNamesArm(int i, int j) {
        return namesArm[i][j];  // от 0 до 7
    }

    public Arms(String name, int stage, int power, int price, int protection, int weight) {
        super(name, stage, power, price);
        this.protection = protection;
        this.weight = weight;
    }

    public static int[] gen(int stage) {
        int[] gen = new int[7];
        gen[0] = random.nextInt(2);// имя тип
        gen[1] = random.nextInt(7) + 1;// имя,всего 8 видов оружия/защиты
        gen[2] = random.nextInt(stage) + 1;// уровень
        if (gen[0] == 0) {
            gen[3] = random.nextInt(stage * gen[1] * 10 + 1) + 1;// сила атаки
            gen[4] = random.nextInt(stage * gen[1] * 3 + 1) + 1;// защита
        } else {
            gen[3] = random.nextInt(stage * gen[1] * 3 + 1) + 1;// сила атаки
            gen[4] = random.nextInt(stage * gen[1] * 10 + 1) + 1;// защита
        }
        gen[5] = random.nextInt((int) (stage * gen[1] * (gen[0] + 1) * 1.5) + 1) + 1;//вес
        gen[6] = random.nextInt(stage * (Math.round((gen[3] + gen[4]) / gen[5]) + 1)) + 1;// цена
        return gen;
    }

    public Arms(int[] genArms) {
        super(getNamesArm(genArms[0], genArms[1]), genArms[2], genArms[3], genArms[6]);
        this.protection = genArms[4];
        this.weight = genArms[5];
    }

    @Override
    public String toString() {
        if (getName().equals(getNamesArm(0, 0)))
            return " пусто";
        else
            return getName() +
                    " Ур." + getStage() +
                    " Вес- " + getWeight() + "кг" +
                    " Атака +" + getPower() +
                    " Защита +" + getProtection() +
                    " Цена- " + getPrice();
    }
}
