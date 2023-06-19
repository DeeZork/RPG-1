import java.util.Random;

class Potion extends Thing {
    public static String[] potionsName = new String[]{"Пузырек лечебного бальзама", "Бутылка лечебного бальзама", "Фляга лечебного бальзама"};
    private static Random random = new Random();

    public static int[] gen(int stage) {
        int[] gen = new int[3];
        gen[0] = random.nextInt(stage) + 1;// уровень
        gen[1] = random.nextInt(3) + 1;// вылечивание
        gen[2] = random.nextInt(stage * gen[1] * 10) + 1;// цена
        return gen;
    }

    public Potion(int[] gen) {
        super(potionsName[gen[1]-1], gen[0], gen[1] * gen[0] * 3, gen[2]);
    }

    @Override
    public String toString() {
        return getName() +
                " Ур." + getStage() +
                " Действие +" + getPower() +
                " Цена- " + getPrice();
    }
}
