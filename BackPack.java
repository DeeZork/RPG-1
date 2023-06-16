import java.util.ArrayList;
import java.util.List;

class BackPack extends Thing {
    private List<Thing> things;
    public static String name = "Заплечный мешок";

    public BackPack(int stage, int power, int price, List<Thing> things) {
        super(name, stage, power, price);
        this.things = things;
    }

    public BackPack(int stage, int power, int price) {
        super(name, stage, power, price);
        this.things = new ArrayList<>();
    }

    @Override
    public String toString() {
        return getName() +
                " Ур." + getStage() +
                " Грузоподъемность- " + getPower() + "кг" +
                " Цена- " + getPrice();
    }

    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }

    public int getFullThingsWeigt() {
        int fullThingsWeigt = 0;
        for (Thing thing : things) {
            Class<?> clazz = thing.getClass();
            if (clazz.getSimpleName().equals("Arms"))
                fullThingsWeigt += ((Arms) thing).getWeight();
        }
        return fullThingsWeigt;
    }
}
